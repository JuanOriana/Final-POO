package backend.model;

import javafx.scene.canvas.GraphicsContext;

/**
 *  Interfaz que representa todos los elementos que saben representarse en un
 *  GraphicContent
 */
public interface Drawable {

    void draw(GraphicsContext gc);
}
