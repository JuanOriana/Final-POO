package frontend;

import backend.CanvasState;
import backend.buttons.*;
import backend.model.figures.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;
	Color SELECTED_COLOR = Color.RED;
	Color fillColor = Color.YELLOW;

	// Botones Barra Izquierda
	ToggleButton selectionButton = new ToggleButton("Seleccionar");
	GeneratorToggleButton rectangleButton = new RectangleToggleButton("Rectángulo");
	GeneratorToggleButton circleButton = new CircleToggleButton("Círculo");
	GeneratorToggleButton ellipseButton = new EllipseToggleButton("Elipse");
	GeneratorToggleButton squareButton = new SquareToggleButton("Cuadrado");
	GeneratorToggleButton lineButton = new LineToggleButton("Linea");
	Button removeButton = new Button("Borrar");
	Button backButton = new Button("Al Fondo");
	Button frontButton = new Button("Al Frente");

	// Sliders y color-pickers
	Slider lineSlider = new Slider(1,50,1);
	ColorPicker lineColorPicker = new ColorPicker(lineColor);
	ColorPicker fillColorPicker = new ColorPicker(fillColor);

	// Labels
	Label lineLabel = new Label("Borde");
	Label fillLabel = new Label("Relleno");

	// Dibujar una figura
	Point startPoint;

	// StatusBar
	StatusPane statusPane;

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;

		ButtonBase[] allButtons = {selectionButton,circleButton,squareButton,ellipseButton,lineButton,removeButton,backButton,frontButton};
		ToggleButton[] toolsArr = {selectionButton};
		GeneratorToggleButton[] generatorButtonsArr = {rectangleButton,circleButton,squareButton,ellipseButton,lineButton};
		Button[] regularButtonArr = {removeButton,backButton,frontButton};
		ToggleGroup tools = new ToggleGroup();

		// Seteo de la visualizacion de los botones
		for (ButtonBase button : allButtons){
			button.setMinWidth(90);
			button.setCursor(Cursor.HAND);
		}
		for (ToggleButton tool : toolsArr) {
			tool.setStyle("-fx-base: #ff5544; -fx-text-fill: #ffffff;");
			tool.setToggleGroup(tools);
		}
		for (GeneratorToggleButton gen : generatorButtonsArr){
			gen.setStyle("-fx-base: #5544ff; -fx-text-fill: #ffffff;");
			gen.setToggleGroup(tools);
		}
		for (Button button : regularButtonArr) {
			button.setStyle("-fx-base: #ffaa00;");
		}

		// Seteo visualizacion de las herramientas de linea
		Control[] lineTools = {lineLabel,lineSlider, lineColorPicker};
		lineSlider.setShowTickMarks(true);
		lineSlider.setShowTickLabels(true);

		// Seteo visualizacion de las herramientas de fill
		Control[] fillTools = {fillLabel,fillColorPicker};

		// Composicion de la VBox
		VBox toolBox = new VBox(10);
		toolBox.getChildren().addAll(allButtons);
		toolBox.getChildren().addAll(lineTools);
		toolBox.getChildren().addAll(fillTools);
		toolBox.setPadding(new Insets(5));
		toolBox.setStyle("-fx-background-color: #777");
		toolBox.setPrefWidth(100);
		gc.setLineWidth(1);

		fillColorPicker.setOnAction(event -> {
			for (Figure figure : canvasState.selectedFigures())
				figure.setFillColor(fillColorPicker.getValue());
			redrawCanvas();
		});

		lineColorPicker.setOnAction(event -> {
			for(Figure figure : canvasState.selectedFigures())
				figure.setLineColor(lineColorPicker.getValue());
			redrawCanvas();
		});

		lineSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			for (Figure figure : canvasState.selectedFigures())
				figure.setLineWidth((Double) newValue);
			redrawCanvas();
		});

		removeButton.setOnAction(event -> {
			if(canvasState.areSelections()){
				canvasState.removeSelected();
				redrawCanvas();
			}
		});

		backButton.setOnAction(event -> {
			if(canvasState.areSelections()){
				canvasState.moveBackSelected();
				redrawCanvas();
			}
		});

		frontButton.setOnAction(event -> {
			if(canvasState.areSelections()){
				canvasState.moveFrontSelected();
				redrawCanvas();
			}
		});

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());

			if(startPoint == null || startPoint.equals(endPoint)) {
				return ;
			}

			for (GeneratorToggleButton generator : generatorButtonsArr){
				Figure newFigure;
				if (generator.isSelected()){
					newFigure = generator.generate(startPoint,endPoint,lineColorPicker.getValue(),fillColorPicker.getValue(),lineSlider.getValue());
					canvasState.addFigure(newFigure);
					startPoint = null;
					redrawCanvas();
					return;
				}
			}
			/* Solo se permite la seleccion multiple en caso de que no haya elementos seleccionados,
			  ante otro caso, es necesario deseleccionar con un click antes. */
			if(selectionButton.isSelected() && !canvasState.areSelections()){
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

		canvas.setOnMouseClicked(event -> {
			/* isStillSincePress evita que esta accion se ejecute aunque el click no haya sido corto
			   (evita conflictos con el arrastrado). */
			if(selectionButton.isSelected() && event.isStillSincePress()) {
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

		canvas.setOnMouseMoved(event -> {
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

		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				if(canvasState.areSelections())
					canvasState.moveSelected(startPoint,eventPoint);
				redrawCanvas();
			}
		});
		setLeft(toolBox);
		setRight(canvas);
	}

	private void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
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
