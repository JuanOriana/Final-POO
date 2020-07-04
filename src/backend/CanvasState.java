package backend;

import backend.model.figures.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class CanvasState {

    private final LinkedList<Figure> figureList = new LinkedList<>();
    HashSet<Figure> selectedFigures = new HashSet<>();

    public void addFigure(Figure figure) {
        figureList.add(figure);
    }

    public void selectFigure(Figure figure){
        if (figureList.contains(figure)){
            selectedFigures.add(figure);
        }
    }

    public void emptySelections(){
        selectedFigures = new HashSet<>();
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(figureList);
    }

    public Collection<Figure> selectedFigures() {
        return new HashSet<>(selectedFigures);
    }

    public void removeFigures(Collection<Figure> figures){
        figureList.removeAll(figures);
    }

    public void moveBack (Collection<Figure> selectedFigures) {
        for (Figure figure : selectedFigures) {
            figureList.remove(figure);
            figureList.addFirst(figure);
        }
    }

    public void moveFront (Collection<Figure> selectedFigures) {
        for (Figure figure : selectedFigures) {
            figureList.remove(figure);
            figureList.addLast(figure);
        }
    }

    public boolean isSelected(Figure figure){
        return selectedFigures.contains(figure);
    }


    public boolean areSelections(){
        return !selectedFigures.isEmpty();
    }

}
