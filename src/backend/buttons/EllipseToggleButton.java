package backend.buttons;

import backend.model.figures.Ellipse;
import backend.model.figures.Figure;
import backend.model.figures.Point;
import backend.model.figures.Rectangle;
import javafx.scene.paint.Color;

public class EllipseToggleButton extends GeneratorToggleButton {

    public EllipseToggleButton(String text) {
        super(text);
    }

    @Override
    public Figure generate(Point start, Point end, Color lineColor, Color fillColor, double lineWidth) {

        Point topLeft = Figure.getTopLeft(start,end);
        Point bottomRight = Figure.getBottomRight(start,end);
        double aRadius = Math.abs(topLeft.getX() - bottomRight.getX())/2;
        double bRadius = Math.abs(topLeft.getY() - bottomRight.getY())/2;
        return new Ellipse(new Point(topLeft.getX() + aRadius, bottomRight.getY() - bRadius), aRadius,bRadius,
                    lineColor,fillColor,lineWidth);
    }
}
