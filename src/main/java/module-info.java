module test {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;

    opens puzzle_cube_solver to javafx.graphics;
}