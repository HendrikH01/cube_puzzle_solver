package puzzle_cube_solver;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CanvasRenderer extends AnimationTimer {

    private final Canvas canvas;

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
        ctx.setFill(Color.WHITE);
        ctx.fillText("Hello World", 250, 200);
        ctx.restore();
    }
}
