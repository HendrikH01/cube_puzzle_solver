package puzzle_cube_solver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vbox = new VBox();
        Canvas canvas = new Canvas();
        vbox.getChildren().add(canvas);

        Scene main = new Scene(vbox, 600, 400);

        stage.setTitle("Puzzle Solver");
        stage.setResizable(false);
        stage.setScene(main);
        stage.show();


        GraphicsContext ctx = canvas.getGraphicsContext2D();
        ctx.strokeText("Hello World", 250, 200);
    }

    public static void run() {
        launch();
    }
}
