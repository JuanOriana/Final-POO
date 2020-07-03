package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sun.font.GraphicComponent;

public abstract class Figure implements Movable, Drawable{
    private final Color SELECTED_COLOR = Color.RED;
    protected Color lineColor,fillColor;
    protected double lineWidth;

    public abstract boolean pointBelongs(Point eventPoint);

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
}
