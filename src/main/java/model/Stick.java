package model;

import javafx.scene.shape.Line;

public class Stick extends Line {
    private final int X1, Y1, X2, Y2;

    public Stick(int X1, int Y1, int X2, int Y2) {
        super(X1, Y1, X2, Y2);
        this.X1 = X1;
        this.Y1 = Y1;
        this.X2 = X2;
        this.Y2 = Y2;
    }

}
