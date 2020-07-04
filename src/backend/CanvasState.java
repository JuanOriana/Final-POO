package backend;

import backend.model.figures.Figure;

import java.util.*;

public class CanvasState {

    private final LinkedList<Figure> list = new LinkedList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

    public void remove(Collection<Figure> selectedFigures) {
        for (Figure figure : selectedFigures) {
            list.remove(figure);
        }
    }

    public void moveBack (Collection<Figure> selectedFigures) {
        remove(selectedFigures);
        for (Figure figure : selectedFigures) {
            list.addFirst(figure);
        }
    }

    public void moveFront (Collection<Figure> selectedFigures) {
        remove(selectedFigures);
        for (Figure figure : selectedFigures) {
            list.addLast(figure);
        }
    }

}
