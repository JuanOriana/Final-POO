package backend.buttons;

import backend.model.figures.Circle;
import backend.model.figures.Figure;
import backend.model.figures.Point;
import javafx.scene.paint.Color;

public class CircleToggleButton extends GeneratorToggleButton {

    public CircleToggleButton(String text) {
        super(text);
    }

    @Override
    public Figure generate(Point start, Point end, Color lineColor, Color fillColor, double lineWidth) {
        Point topLeft = Figure.getTopLeft(start,end);
        Point bottomRight = Figure.getBottomRight(start,end);
        double circleRadius = topLeft.distanceTo(bottomRight);
        return new Circle(start, circleRadius, lineColor,fillColor,lineWidth);
    }
}
