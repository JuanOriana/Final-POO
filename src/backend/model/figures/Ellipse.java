package backend.model.figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Figure {
    protected final Point centerPoint;
    protected final double aRadius, bRadius;

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

    @Override
    public boolean pointBelongs(Point eventPoint) {
        if (getARadius()==0 || getBRadius()==0)
            return false;
        return 1 >= Math.pow(eventPoint.getX()-centerPoint.getX(),2)/Math.pow(getARadius(),2) +
                    Math.pow(eventPoint.getY()-centerPoint.getY(),2)/Math.pow(getBRadius(),2);
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
        return String.format("%s [Centro: %s, Radio-A: %.2f, Radio-B: %.2f]",name,getCenterPoint(), getARadius(),getBRadius());
    }

    @Override
    public boolean isWithinArea(Point areaTopLeft, Point areaBottomRight) {
        Point topLeft = new Point(centerPoint.getX()-aRadius,centerPoint.getY()-bRadius);
        Point bottomRight = new Point(centerPoint.getX()+aRadius,centerPoint.getY()+bRadius);
        return topLeft.getY()>=areaTopLeft.getY() && topLeft.getX()>=areaTopLeft.getX()
                && bottomRight.getY()<= areaBottomRight.getY() && bottomRight.getX() <= areaBottomRight.getX();
    }
}
