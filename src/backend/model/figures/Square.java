package backend.model.figures;

import javafx.scene.paint.Color;

public class Square extends Rectangle {
    public Square(Point p1, double side, Color lineColor, Color fillColor, double lineWidth){
        super(p1,new Point(p1.getX() + side, p1.getY() + side), lineColor, fillColor, lineWidth);
        name = "Cuadrado";
    }
}
