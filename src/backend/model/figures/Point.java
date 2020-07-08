package backend.model.figures;

import backend.model.Movable;

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

    public double distanceTo(Point otherPoint){
        return Math.sqrt(Math.pow(getX() - otherPoint.getX(),2) +
                Math.pow(getY() - otherPoint.getY(),2));
    }

    public double xDistanceTo(Point otherPoint){
        return Math.abs(getX() - otherPoint.getX());
    }


}
