package puzzle_cube_solver;

import static puzzle_cube_solver.MathHelper.isBitOne;
import static puzzle_cube_solver.Main.PIECE_SIZE;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;

import java.util.List;

public class CanvasRenderer extends AnimationTimer {
    private final Canvas canvas;
    private final Piece[] testPieces = new Piece[] {
            new Piece((byte) 0b10100, (byte) 0b00100, (byte) 0b01010, (byte) 0b00011),
            new Piece((byte) 0b01010, (byte) 0b01010, (byte) 0b01010, (byte) 0b00100),
            new Piece((byte) 0b00100, (byte) 0b01100, (byte) 0b01010, (byte) 0b00100),
            new Piece((byte) 0b10100, (byte) 0b00100, (byte) 0b00100, (byte) 0b00111),
            new Piece((byte) 0b11011, (byte) 0b10100, (byte) 0b01011, (byte) 0b11011),
            new Piece((byte) 0b10101, (byte) 0b11010, (byte) 0b00011, (byte) 0b11011)
    };

    public CanvasRenderer(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void handle(long t) {
        GraphicsContext ctx = this.canvas.getGraphicsContext2D();

        //clear
        ctx.save();
        ctx.setFill(Color.GRAY);
        ctx.setFont(new Font("Verdana", 16));
        ctx.fillRect(0, 0, App.WIN_X, App.WIN_Y);

        /*
        for(int i = 0; i < testPieces.length; i++) {
            for(int j = 0; j < 4; j++)
                this.drawPiece(ctx, 30 + i * 80, 30 + j * 80, 10, testPieces[i], j);
        } */

        List<CubeSolver.Connection> list = CubeSolver.findConnections(testPieces[0], testPieces[5]);

        int x = 0;
        for(CubeSolver.Connection c : list) {
            this.drawPiece(ctx, 30 + x * 80, 100, 10, c.p1(), c.r1());
            this.drawPiece(ctx, 30 + x * 80, 60, 10, c.p2(), c.r2() + 2);
            x += 1;
        }

        ctx.restore();
    }

    /**
     * Draws a piece at (posX, posY). You can set the size of each square that makes up a piece in pixels.
     * @param ctx
     * @param posX
     * @param posY
     * @param size
     * @param piece
     * @param rotation the number of clockwise 90 degree rotations
     */
    private void drawPiece(GraphicsContext ctx, int posX, int posY, int size, Piece piece, int rotation) {
        ctx.save();
        ctx.translate(posX, posY);
        ctx.setFill(Color.RED); //default color
        ctx.setLineWidth(3);
        ctx.setStroke(Color.BLACK);

        int x = 0, y = 0;
        Piece rotatedPiece = piece.rotate(rotation);
        byte[] sides = rotatedPiece.getSides();
        ctx.beginPath();

        //skipNext set to true if the first drawn line of a side is supposed to be skipped.
        //This is used to avoid drawing loose lines that don't connect to anything.
        boolean skipNext = !(isBitOne(rotatedPiece.west, 1) | isBitOne(rotatedPiece.west, 0));

        for(int i = 0; i < 4; i++) {
            //TODO: clean this up
            if(i == 1) {
                y = -size;
                x = (PIECE_SIZE - 1) * size;
            }

            if(i == 2) {
                x = PIECE_SIZE * size;
                y = (PIECE_SIZE - 2) * size;
            }

            if(i == 3) {
                x = size;
                y = (PIECE_SIZE - 1) * size;
            }

            ctx.save();
            //translate and rotate canavs to the desired position
            Affine a = new Affine();
            a.appendTranslation(x, y);
            a.appendRotation(90 * i);
            ctx.transform(a);

            //draw side
            drawSide(sides[i], ctx, size, skipNext);
            ctx.restore();

            skipNext = !(isBitOne(sides[i], 1) | isBitOne(sides[i], 0));
        }

        ctx.stroke();
        ctx.fill();
        //fill inner square
        ctx.fillRect(size, 0, (PIECE_SIZE - 2) * size, (PIECE_SIZE - 2) * size);
        ctx.restore();
    }

    /**
     * Draws one side of a piece.
     * @param side
     * @param ctx
     * @param size
     * @param skipFirst
     */
    private void drawSide(byte side, GraphicsContext ctx, int size, boolean skipFirst) {
        int x = 0, y = 0;

        if(skipFirst) {
            ctx.moveTo(size, 0);
        } else {
            ctx.moveTo(0, 0);
            y = isBitOne(side, PIECE_SIZE - 1) ? -size : 0;
            ctx.lineTo(x, y);
        }

        //loop over all squares that make up the side to be drawn
        for (int i = 1; i < PIECE_SIZE; i++) {
            x += size;

            ctx.lineTo(x, y);

            int y2 = isBitOne(side, PIECE_SIZE - i - 1) ? -size : 0;
            if (y2 != y) {
                y = y2;
                ctx.lineTo(x, y);
            }
        }

        if (y != 0) {
            ctx.lineTo(x, 0);
        }
    }
}
