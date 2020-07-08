package backend.buttons;

import backend.model.figures.Figure;
import backend.model.figures.Point;
import backend.model.figures.Square;
import javafx.scene.paint.Color;

public class SquareToggleButton extends GeneratorToggleButton {

    public SquareToggleButton(String text) {
        super(text);
    }

    @Override
    public Figure generate(Point start, Point end, Color lineColor, Color fillColor, double lineWidth) {
        Point topLeft = Figure.getTopLeft(start,end);
        Point areaBottomRight = Figure.getBottomRight(start,end);
        double side =  topLeft.xDistanceTo(areaBottomRight);
        return new Square(topLeft,side,lineColor,fillColor,lineWidth);
    }
}
