package puzzle_cube_solver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    public static final int WIN_X = 600;
    public static final int WIN_Y = 400;
    @Override
    public void start(Stage stage) throws Exception {
        VBox vbox = new VBox();
        Canvas canvas = new Canvas(WIN_X, WIN_Y);
        vbox.getChildren().addAll(canvas);

        Scene main = new Scene(vbox, WIN_X, WIN_Y);

        stage.setTitle("Puzzle Solver");
        stage.setResizable(false);
        stage.setScene(main);
        stage.show();

        new CanvasRenderer(canvas).start();
    }

    public static void run() {
        launch();
    }
}
