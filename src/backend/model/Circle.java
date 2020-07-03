package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Figure {

    protected final Point centerPoint;
    protected final double radius;

    public Circle(Point centerPoint, double radius, Color lineColor, Color fillColor, double lineWidth) {
        super(lineColor, fillColor, lineWidth);
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean pointBelongs(Point eventPoint) {
        return Math.sqrt(Math.pow(getCenterPoint().getX() - eventPoint.getX(), 2) +
                Math.pow(getCenterPoint().getY() - eventPoint.getY(), 2)) < getRadius();
    }

    @Override
    public void move(double x, double y) {
        centerPoint.move(x,y);
    }

    @Override
    public void draw(GraphicsContext gc, boolean selected) {
        setForDrawing(gc,selected);
        double diameter = getRadius() * 2;
        gc.fillOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), diameter, diameter);
        gc.strokeOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), diameter, diameter);
    }
}
