package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Figure {
    private final Point start, end;

    public Line(Point start, Point end, Color lineColor, Color fillColor, double lineWidth) {
        super(lineColor,fillColor,lineWidth);
        this.start = start;
        this.end = end;

    }
    @Override
    public boolean pointBelongs(Point eventPoint) {
        return eventPoint==start || eventPoint==end;
    }

    @Override
    public void draw(GraphicsContext gc, boolean selected) {
        setForDrawing(gc, selected);
        gc.strokeLine(getStart().getX(),getStart().getY(),getEnd().getX(),getEnd().getY());
    }

    @Override
    public void move(double x, double y) {
        start.move(x,y);
        end.move(x,y);
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return String.format("%s [ %s , %s ]", "Linea", getStart(), getStart());
    }
}
