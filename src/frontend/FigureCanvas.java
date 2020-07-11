package frontend;

import backend.CanvasState;
import frontend.buttons.GeneratorToggleButton;
import backend.model.figures.Figure;
import backend.model.figures.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FigureCanvas extends Canvas {

    //Backend
    private final CanvasState canvasState;

    //ToolBox
    private final ToolBox toolBox;

    //StatusBar
    private final StatusPane statusPane;

    //Utiles para graficado
    private final static Color SELECTED_COLOR = Color.RED;
    private GraphicsContext gc;

    // Dibujar una figura
    private Point startPoint;


    public FigureCanvas(CanvasState canvasState, ToolBox toolBox, StatusPane statusPane){
        super(800,600);
        this.canvasState= canvasState;
        this.toolBox = toolBox;
        this.statusPane = statusPane;
        gc = getGraphicsContext2D();

        toolBox.setFillAction(event -> {
            for (Figure figure : canvasState.selectedFigures())
                figure.setFillColor(toolBox.getFillColor());
            redrawCanvas();
        });

        toolBox.setLineAction(event -> {
            for(Figure figure : canvasState.selectedFigures())
                figure.setLineColor(toolBox.getLineColor());
            redrawCanvas();
        });

        toolBox.getLineSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            for (Figure figure : canvasState.selectedFigures())
                figure.setLineWidth((Double) newValue);
            redrawCanvas();
        });

        toolBox.setOnRemoveAction(event -> {
            if(canvasState.areSelections()){
                canvasState.removeSelected();
                redrawCanvas();
            }
        });

        toolBox.setOnBackAction(event -> {
            if(canvasState.areSelections()){
                canvasState.moveBackSelected();
                redrawCanvas();
            }
        });

        toolBox.setOnFrontAction(event -> {
            if(canvasState.areSelections()){
                canvasState.moveFrontSelected();
                redrawCanvas();
            }
        });

        setOnMousePressed(event -> {
            startPoint = new Point(event.getX(), event.getY());
        });

        setOnMouseReleased(event -> {
            Point endPoint = new Point(event.getX(), event.getY());

            if(startPoint == null || startPoint.equals(endPoint)) {
                return ;
            }

            for (GeneratorToggleButton generator : toolBox.getGenerators()){
                Figure newFigure;
                if (generator.isSelected()){
                    newFigure = generator.generate(startPoint,endPoint,toolBox.getLineColor(),
                            toolBox.getFillColor(),toolBox.getLineWidth());
                    canvasState.addFigure(newFigure);
                    startPoint = null;
                    redrawCanvas();
                    return;
                }
            }

			/* Solo se permite la seleccion multiple en caso de que no haya elementos seleccionados,
			  ante otro caso, es necesario deseleccionar con un click antes. */
            if(toolBox.onSelected() && !canvasState.areSelections()){
                canvasState.selectByArea(startPoint,endPoint);
                if (!canvasState.areSelections()){
                    statusPane.updateStatus("Ninguna figura encontrada");
                }
                else{
                    StringBuilder label = new StringBuilder("Se seleccionó: ");
                    for (Figure figure : canvasState.selectedFigures()){
                        label.append(figure.toString());
                    }
                    statusPane.updateStatus(label.toString());
                }
                startPoint = null;
                redrawCanvas();
            }
        });

        setOnMouseClicked(event -> {
			/* isStillSincePress evita que esta accion se ejecute aunque el click no haya sido corto
			   (evita conflictos con el arrastrado). */
            if(toolBox.onSelected() && event.isStillSincePress()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                canvasState.emptySelections();
                Figure toSelect = canvasState.lastFigureOnPoint(eventPoint);
                if (toSelect != null) {
                    canvasState.selectFigure(toSelect);
                    statusPane.updateStatus("Se selecciono: " + toSelect.toString());
                } else {
                    statusPane.updateStatus("Ninguna figura encontrada");
                }
                redrawCanvas();
            }
        });

        setOnMouseMoved(event -> {
            Point eventPoint = new Point(event.getX(), event.getY());
            boolean found = false;
            StringBuilder label = new StringBuilder();
            for(Figure figure : canvasState.figures()) {
                if(figure.pointBelongs(eventPoint)) {
                    found = true;
                    label.append(figure.toString());
                }
            }
            if(found) {
                statusPane.updateStatus(label.toString());
            } else {
                statusPane.updateStatus(eventPoint.toString());
            }
        });

        setOnMouseDragged(event -> {
            if(toolBox.onSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                if(canvasState.areSelections())
                    canvasState.moveSelected(startPoint,eventPoint);
                redrawCanvas();
            }
        });


    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, getWidth(), getHeight());
        for(Figure figure : canvasState.figures()) {
            if(canvasState.isSelected(figure)){
                Color originalColor = figure.getLineColor();
                figure.setLineColor(SELECTED_COLOR);
                figure.draw(gc);
                figure.setLineColor(originalColor);
            }
            else
                figure.draw(gc);
        }
    }

}
