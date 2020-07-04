package backend;

import backend.model.figures.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CanvasState {

    private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

    public void removeFigure(Collection<Figure> figures){
        list.removeAll(figures);
    }
}
