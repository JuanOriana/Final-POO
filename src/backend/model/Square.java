package backend.model;

import javafx.scene.paint.Color;

public class Square extends Rectangle {
    //TODO: retocar el constructor
    public Square(Point p1, Point p2, Color lineColor, Color fillColor, double lineWidth){
        super(p1,new Point(p1.getX() + Math.abs(p1.getX() - p2.getX()), p1.getY() + Math.abs(p1.getX() - p2.getX()))
                ,lineColor, fillColor, lineWidth);
        name = "Cuadrado";
    }
}
