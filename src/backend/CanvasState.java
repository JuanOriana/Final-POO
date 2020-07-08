package backend;

import backend.model.figures.Figure;
import backend.model.figures.Point;

import java.util.*;

public class CanvasState {

    private final static double MOVE_SPEED =100.0;
    private final LinkedList<Figure> figureList = new LinkedList<>();
    HashSet<Figure> selectedFigures = new HashSet<>();

    /**
     * Anade una figura al canvas
     * @param figure
     */
    public void addFigure(Figure figure) {
        figureList.add(figure);
    }

    /**
     * Selecciona una figura, de estar en el canvas.
     * @param figure
     */
    public void selectFigure(Figure figure){
        if (figureList.contains(figure)){
            selectedFigures.add(figure);
        }
    }

    /** Vacia las selecciones */
    public void emptySelections(){
        selectedFigures = new HashSet<>();
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(figureList);
    }

    public Collection<Figure> selectedFigures() {
        return new HashSet<>(selectedFigures);
    }

    /** Elimina todas las figuras seleccionadas */
    public void removeSelected(){
        figureList.removeAll(selectedFigures);
        emptySelections();
    }
    /** Retorna true si una figura determinada esta seleccionada */
    public boolean isSelected(Figure figure){
        return selectedFigures.contains(figure);
    }

    /** Retorna true si existe alguna figura seleccionada */
    public boolean areSelections(){
        return !selectedFigures.isEmpty();
    }

    // Esta implementacion es necesaria para conservar el order de la lista (y no depender del orden del set) y
    // tambien para poder borrar elementos durante la iteracion.

    /** Remueve los seleccionados de la lista de figuras (MANTIENIENDOLOS SELECCIONADOS)
     *  Y los devuelve en una lista en el orden de las figuras originales
     */
    private List<Figure> removeAndRetrieveSelected(){
        LinkedList<Figure> selected = new LinkedList<>();
        Iterator<Figure> itr = figureList.iterator();
        while (itr.hasNext()) {
            Figure curr = itr.next();
            if (selectedFigures.contains(curr)) {
                itr.remove();
                selected.add(curr);
            }
        }
        return selected;
    }
    /** Mueve todas las figuras seleccionaas al principio de la lista */
    public void moveBackSelected () {
        List<Figure> append = removeAndRetrieveSelected();
        figureList.addAll(0,append);
    }

    /** Mueve todas las figuras seleccionadas al final de la lista */
    public void moveFrontSelected() {
        List<Figure> append =removeAndRetrieveSelected();
        figureList.addAll(append);
    }

    /** Selecciona todas las figuras en un area determinada por dos puntos */
    public void selectByArea(Point start, Point end){
        Point topLeft = Figure.getTopLeft(start,end);
        Point bottomRight = Figure.getBottomRight(start,end);
        for (Figure figure : figures()){
            if(figure.isWithinArea(topLeft,bottomRight)) {
                selectFigure(figure);
            }
        }
    }

    /** Retorna el ultimo elemento anadido que contiene cierto punto (o null en su defecto) */
    public Figure lastFigureOnPoint(Point point){
        for(int i = figureList.size()-1 ; i>=0 ;i-- ){
            Figure figure = figureList.get(i);
            if (figure.pointBelongs(point))
                return figure;
        }
        return null;
    }

    /**
     * Mueve todas las figuras seleccionadas en relacion al vector generado por dos puntos
     * @param start
     * @param end
     */
    public void moveSelected(Point start, Point end){
        double diffX = (end.getX() - start.getX()) / MOVE_SPEED;
        double diffY = (end.getY() - start.getY()) / MOVE_SPEED;
        for (Figure figure : selectedFigures())
            figure.move(diffX,diffY);

    }


}
