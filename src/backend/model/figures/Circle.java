package backend.model.figures;

import javafx.scene.paint.Color;

public class Circle extends Ellipse {
    public Circle(Point centerPoint, double radius,Color  lineColor, Color fillColor, double lineWidth) {
        super(centerPoint,radius,radius, lineColor, fillColor, lineWidth);
        if (radius == 0.0)
            throw new IllegalFigureException();
        name = "Circulo";
    }

    @Override
    public String toString() {
        return String.format("%s [Centro: %s, Radio: %.2f]",name,centerPoint,getRadius());
    }


    public double getRadius() {
        return getBRadius();
    }


}
