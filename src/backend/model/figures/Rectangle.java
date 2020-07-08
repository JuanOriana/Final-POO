package backend.model.figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight, Color lineColor, Color fillColor, double lineWidth) {
        super(lineColor,fillColor,lineWidth);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        name = "Rectangulo";

    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("%s [ %s , %s ]", name, getTopLeft(), getBottomRight());
    }

    @Override
    public boolean pointBelongs(Point eventPoint) {
        return eventPoint.getX() > getTopLeft().getX() && eventPoint.getX() < getBottomRight().getX() &&
                eventPoint.getY() > getTopLeft().getY() && eventPoint.getY() < getBottomRight().getY();
    }

    @Override
    public boolean isWithinArea(Point areaTopLeft, Point areaBottomRight) {
        return getTopLeft().getY()>=areaTopLeft.getY() && getTopLeft().getX()>=areaTopLeft.getX()
                && getBottomRight().getY()<= areaBottomRight.getY() && getBottomRight().getX() <= areaBottomRight.getX();
    }

    @Override
    public void move(double x, double y) {
        topLeft.move(x,y);
        bottomRight.move(x,y);
    }

    @Override
    public void draw(GraphicsContext gc, boolean selected) {
        setForDrawing(gc,selected);
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }
}
