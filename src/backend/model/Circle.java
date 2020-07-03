package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.*;

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

    @Override
    public boolean pointBelongs(Point eventPoint) {
        return Math.sqrt(Math.pow(getCenterPoint().getX() - eventPoint.getX(), 2) +
                Math.pow(getCenterPoint().getY() - eventPoint.getY(), 2)) < getRadius();
    }

}
