package frontend.buttons;

import backend.model.figures.Figure;
import backend.model.figures.Point;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

/**
 *  Tipo de ToggleButton que es capaz de generar figuras.
 */
public abstract class GeneratorToggleButton extends ToggleButton {

    public GeneratorToggleButton(String text) {
        super(text);
    }

    public abstract Figure generate (Point start, Point end, Color lineColor, Color fillColor, double lineWidth);
}
