package backend.model.figures;

import backend.model.Movable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Point))
            return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    public double xDistanceTo(Point otherPoint){
        return Math.abs(getX() - otherPoint.getX());
    }



    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
