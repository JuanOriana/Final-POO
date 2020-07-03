package backend.model;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable {

    default void draw(GraphicsContext gc){
        draw(gc, false);
    }
    void draw(GraphicsContext gc, boolean selected);
}
