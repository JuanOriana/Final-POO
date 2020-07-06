package backend.model.figures;

import backend.model.Movable;
import javafx.scene.canvas.Canvas;

public class Point implements Movable {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    @Override
    public void move(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public double distanceTo(Point ohterPoint){
        return Math.sqrt(Math.pow(getX() - ohterPoint.getX(),2) +
                Math.pow(getY() - ohterPoint.getY(),2));
    }
    //En principio solo se usa para chequear que no se pase de los limites cuando printea el statusPanel
    public boolean isWithinCanvas(Canvas canvas){
        return getX() >= 0 && getX() <= canvas.getWidth()
                && getY() >=0 && getY() <= canvas.getHeight();
    }
}
