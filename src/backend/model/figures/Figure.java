package backend.model.figures;

import backend.model.Drawable;
import backend.model.Movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figure implements Movable, Drawable {
    private final Color SELECTED_COLOR = Color.RED;
    protected Color lineColor,fillColor;
    protected double lineWidth;

    public abstract boolean pointBelongs(Point eventPoint);

    public abstract boolean isWithinArea(Point areaTopLeft, Point areaBottomRight);

    public Figure(Color lineColor, Color fillColor, double lineWidth) {
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.lineWidth = lineWidth;
    }

    protected void setForDrawing(GraphicsContext gc, boolean selected){
        gc.setLineWidth(lineWidth);
        gc.setFill(fillColor);
        Color color = selected?SELECTED_COLOR:lineColor;
        gc.setStroke(color);

    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public static Point getUpperLeft(Point point1, Point point2){
        return new Point(Math.min(point1.getX(),point2.getX()),Math.min(point1.getY(),point2.getY()));
    }

    public static Point getBottomRight(Point point1, Point point2){
        return new Point(Math.max(point1.getX(),point2.getX()),Math.max(point1.getY(),point2.getY()));
    }
}
