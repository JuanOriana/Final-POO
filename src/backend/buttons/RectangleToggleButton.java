package backend.buttons;

import backend.model.figures.Figure;
import backend.model.figures.Point;
import backend.model.figures.Rectangle;
import javafx.scene.paint.Color;


public class RectangleToggleButton extends GeneratorToggleButton{

    public RectangleToggleButton(String text) {
        super(text);
    }

    @Override
    public Figure generate(Point start, Point end, Color lineColor, Color fillColor, double lineWidth) {
        Point topLeft = Figure.getTopLeft(start,end);
        Point bottomRight = Figure.getBottomRight(start,end);
        return new Rectangle(topLeft,bottomRight,lineColor,fillColor,lineWidth);
    }
}
