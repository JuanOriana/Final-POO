package backend.model.figures;

import javafx.scene.paint.Color;

public class Circle extends Ellipse {
    public Circle(Point centerPoint, double radius,Color  lineColor, Color fillColor, double lineWidth) {
        super(centerPoint,radius,radius, lineColor, fillColor, lineWidth);
        name = "Circulo";
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getRadius());
    }


    public double getRadius() {
        return getBRadius();
    }


}
