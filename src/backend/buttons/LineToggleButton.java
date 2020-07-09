package backend.buttons;

import backend.model.figures.Figure;
import backend.model.figures.Line;
import backend.model.figures.Point;
import javafx.scene.paint.Color;

public class LineToggleButton extends GeneratorToggleButton{

    public LineToggleButton(String text) {
        super(text);
    }

    @Override
    public Figure generate(Point start, Point end, Color lineColor, Color fillColor, double lineWidth) {
        return new Line(start,end,lineColor,lineWidth);
    }
}
