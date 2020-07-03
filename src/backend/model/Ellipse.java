package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Figure {
    protected final Point centerPoint;
    protected final double radiusA, radiusB;

    public Ellipse(Point centerPoint, double radiusA, double radiusB, Color lineColor, Color fillColor, double lineWidth) {
        super(lineColor,fillColor,lineWidth);
        this.centerPoint = centerPoint;
        this.radiusA = radiusA;
        this.radiusB = radiusB;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadiusA() {
        return radiusA;
    }

    public double getRadiusB() {
        return radiusB;
    }

    //Pulir, usando la del rectangulo
    @Override
    public boolean pointBelongs(Point eventPoint) {
        return Math.sqrt(Math.pow(getCenterPoint().getX() - eventPoint.getX(), 2)) < getRadiusA() &&
                Math.sqrt(Math.pow(getCenterPoint().getY() - eventPoint.getY(), 2)) < getRadiusB();
    }

    @Override
    public void draw(GraphicsContext gc, boolean selected) {
        setForDrawing(gc,selected);
        gc.fillOval(getCenterPoint().getX() - getRadiusA(), getCenterPoint().getY() - getRadiusB(), getRadiusA()*2, getRadiusB()*2);
        gc.strokeOval(getCenterPoint().getX() - getRadiusA(), getCenterPoint().getY() - getRadiusB(), getRadiusA()*2, getRadiusB()*2);
    }



    @Override
    public void move(double x, double y) {
        centerPoint.move(x,y);
    }
}
