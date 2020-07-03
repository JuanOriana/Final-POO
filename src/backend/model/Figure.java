package backend.model;

public abstract class Figure implements Movable, Drawable{
    public abstract boolean pointBelongs(Point eventPoint);
}
