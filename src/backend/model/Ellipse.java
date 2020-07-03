package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Figure {
    protected final Point centerPoint;
    protected final double aRadius, bRadius;
    protected String name;

    public Ellipse(Point centerPoint, double radiusA, double radiusB, Color lineColor, Color fillColor, double lineWidth) {
        super(lineColor,fillColor,lineWidth);
        this.centerPoint = centerPoint;
        this.aRadius = radiusA;
        this.bRadius = radiusB;
        name = "Elipse";
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public Double getARadius() {
        return aRadius;
    }

    public Double getBRadius() {
        return bRadius;
    }

    //Pulir, usando la del rectangulo
    @Override
    public boolean pointBelongs(Point eventPoint) {
        return Math.sqrt(Math.pow(getCenterPoint().getX() - eventPoint.getX(), 2)) < getARadius() &&
                Math.sqrt(Math.pow(getCenterPoint().getY() - eventPoint.getY(), 2)) < getBRadius();
    }

    @Override
    public void draw(GraphicsContext gc, boolean selected) {
        setForDrawing(gc,selected);
        gc.fillOval(getCenterPoint().getX() - getARadius(), getCenterPoint().getY() - getBRadius(), getARadius()*2, getBRadius()*2);
        gc.strokeOval(getCenterPoint().getX() - getARadius(), getCenterPoint().getY() - getBRadius(), getARadius()*2, getBRadius()*2);
    }

    @Override
    public void move(double x, double y) {
        centerPoint.move(x,y);
    }

    @Override
    public String toString() {
        return String.format("%s [Centro: %s, A: %.2f, B: %.2f]",name,getCenterPoint(), getARadius(),getBRadius());
    }
}
