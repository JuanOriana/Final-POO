package frontend.buttons;

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
        double circleRadius = start.distanceTo(end);
        return new Circle(start, circleRadius, lineColor,fillColor,lineWidth);
    }
}
