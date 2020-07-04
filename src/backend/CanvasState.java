package backend;

import backend.model.figures.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class CanvasState {

    private final LinkedList<Figure> list = new LinkedList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

    public void removeFigure(Collection<Figure> figures){
        list.removeAll(figures);
    }

    public void moveBack (Collection<Figure> selectedFigures) {
        removeFigure(selectedFigures);
        for (Figure figure : selectedFigures) {
            list.addFirst(figure);
        }
    }

    public void moveFront (Collection<Figure> selectedFigures) {
        removeFigure(selectedFigures);
        for (Figure figure : selectedFigures) {
            list.addLast(figure);
        }
    }

}
