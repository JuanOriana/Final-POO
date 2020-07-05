package backend.buttons;

import backend.model.figures.Figure;
import backend.model.figures.Point;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;


public abstract class GeneratorToggleButton extends ToggleButton {

    public GeneratorToggleButton(String text) {
        super(text);
    }

    public abstract Figure generate (Point start, Point end, Color lineColor, Color fillColor, double lineWidth);
}
