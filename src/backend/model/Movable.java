package backend.model;

/**
 * Representa todos los elementos que pueden moverse en el espacio bidimensional
 */
@FunctionalInterface
public interface Movable {

    /** Mueve el elemento en la direccion vectorial que representa el punto (x,y) en relacion al origen.
     *  NO mueve el objeto a la posicion (x,y)
     */
    void move(double x, double y);
}
