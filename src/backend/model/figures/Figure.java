package backend.model.figures;

import backend.model.Drawable;
import backend.model.Movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Interfaz que representa una figura geometrica en el espacio bidimensional
 */
public abstract class Figure implements Movable, Drawable {
    private final Color SELECTED_COLOR = Color.RED;
    protected Color lineColor,fillColor;
    protected double lineWidth;
    protected String name;

    /**
     * Devuelve true si el punto es parte de la figura
     */
    public abstract boolean pointBelongs(Point eventPoint);

    /**
     * Dados dos puntos que dibujan un area rectangular (superior izquierdo e inferior derecho)
     * devuelve true si al figura esta completamente incluida en ese area
     */
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

    /**
     * Dada el area rectangular determinada por dos puntos en el espacio,
     * retorna la esquina superior izquierda del area en un sistema de coordenadas
     * donde la x aumenta hacia la derecha y la y hacia abajo
     */
    public static Point getTopLeft(Point point1, Point point2){
        return new Point(Math.min(point1.getX(),point2.getX()),Math.min(point1.getY(),point2.getY()));
    }
    /**
     * Dada el area rectangular determinada por dos puntos en el espacio,
     * retorna la esquina inferior derecha del area en un sistema de coordenadas
     * donde la x aumenta hacia la derecha y la y hacia abajo
     */
    public static Point getBottomRight(Point point1, Point point2){
        return new Point(Math.max(point1.getX(),point2.getX()),Math.max(point1.getY(),point2.getY()));
    }
}
