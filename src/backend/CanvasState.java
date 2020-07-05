package backend;

import backend.model.figures.Figure;
import backend.model.figures.Point;

import java.util.*;

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

    public void removeSelected(){
        figureList.removeAll(selectedFigures);
        emptySelections();
    }

    public boolean isSelected(Figure figure){
        return selectedFigures.contains(figure);
    }


    public boolean areSelections(){
        return !selectedFigures.isEmpty();
    }

    // Esta implementacion es necesaria para conservar el order de la lista (y no depender del orden del set) y
    // tambien para poder borrar elementos durante la iteracion.
    public void moveBackSelected () {
        LinkedList<Figure> append = new LinkedList<>();
        Iterator<Figure> itr = figureList.iterator();
        while (itr.hasNext()) {
            Figure curr = itr.next();
            if (selectedFigures.contains(curr)) {
                itr.remove();
                append.add(curr);
            }
        }
        figureList.addAll(0,append);
    }

    public void moveFrontSelected() {
        LinkedList<Figure> append = new LinkedList<>();
        Iterator<Figure> itr = figureList.iterator();
        while (itr.hasNext()) {
            Figure curr = itr.next();
            if (selectedFigures.contains(curr)) {
                itr.remove();
                append.add(curr);
            }
        }
        figureList.addAll(append);
    }

    // Selecciona figuras en un area
    public void selectByArea(Point start, Point end){
        Point topLeft = Figure.getTopLeft(start,end);
        Point bottomRight = Figure.getBottomRight(start,end);
        for (Figure figure : figures()){
            if(figure.isWithinArea(topLeft,bottomRight)) {
                selectFigure(figure);
            }
        }
    }

    // Retorna el ultimo elemento anadido que contiene cierto punto (o null en su defecto)
    public Figure lastFigureOnPoint(Point point){
        for(int i = figureList.size()-1 ; i>=0 ;i-- ){
            Figure figure = figureList.get(i);
            if (figure.pointBelongs(point))
                return figure;
        }
        return null;
    }


}
