package frontend;

import backend.CanvasState;
import backend.model.figures.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashSet;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;
	Color fillColor = Color.YELLOW;

	// Botones Barra Izquierda
	ToggleButton selectionButton = new ToggleButton("Seleccionar");
	ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	ToggleButton circleButton = new ToggleButton("Círculo");
	ToggleButton ellipseButton = new ToggleButton("Elipse");
	ToggleButton squareButton = new ToggleButton("Cuadrado");
	ToggleButton lineButton = new ToggleButton("Linea");
	ToggleButton removeButton = new ToggleButton("Borrar");
	ToggleButton backButton = new ToggleButton("Al Fondo");
	ToggleButton frontButton = new ToggleButton("Al Frente");

	// Sliders y color-pickers
	Slider lineSlider = new Slider(1,50,1);
	ColorPicker lineColorPicker = new ColorPicker(lineColor);
	ColorPicker fillColorPicker = new ColorPicker(fillColor);

	// Labels
	Label lineLabel = new Label("Borde");
	Label fillLabel = new Label("Relleno");

	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	HashSet<Figure> selectedFigures = new HashSet<>();

	// StatusBar
	StatusPane statusPane;


	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;


		ToggleButton[] buttonsArr = {selectionButton, rectangleButton, circleButton,ellipseButton,squareButton, lineButton,removeButton, backButton, frontButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : buttonsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}

		Control[] lineTools = {lineLabel,lineSlider, lineColorPicker};
		lineSlider.setShowTickMarks(true);
		lineSlider.setShowTickLabels(true);

		Control[] fillTools = {fillLabel,fillColorPicker};

		VBox toolBox = new VBox(10);
		toolBox.getChildren().addAll(buttonsArr);
		toolBox.getChildren().addAll(lineTools);
		toolBox.getChildren().addAll(fillTools);
		toolBox.setPadding(new Insets(5));
		toolBox.setStyle("-fx-background-color: #999");
		toolBox.setPrefWidth(100);
		gc.setLineWidth(1);

		fillColorPicker.setOnAction(event -> {
			if (areSelections()){
				for (Figure figure : selectedFigures)
					figure.setFillColor(fillColorPicker.getValue());
				redrawCanvas();
			}
		});

		lineColorPicker.setOnAction(event -> {
			if (areSelections()){
				for(Figure figure : selectedFigures)
					figure.setLineColor(lineColorPicker.getValue());
				redrawCanvas();
			}
		});

		lineSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (areSelections()) {
				for (Figure figure : selectedFigures)
					figure.setLineWidth((Double) newValue);
				redrawCanvas();
			}
		});

		removeButton.setOnAction(event -> {
			if(areSelections()){
				canvasState.removeFigure(selectedFigures);
				redrawCanvas();
			}
		});

		backButton.setOnAction(event -> {
			if(areSelections()){
				canvasState.moveBack(selectedFigures);
				redrawCanvas();
			}
		});

		frontButton.setOnAction(event -> {
			if(areSelections()){
				canvasState.moveFront(selectedFigures);
				redrawCanvas();
			}
		});

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			Point upperLeft = Figure.getUpperLeft(startPoint,endPoint);
			Point bottomRight = Figure.getBottomRight(startPoint,endPoint);
			Figure newFigure;

			if(rectangleButton.isSelected()) {
				newFigure = new Rectangle(upperLeft, bottomRight, lineColorPicker.getValue(), fillColorPicker.getValue(), lineSlider.getValue());
			}
			else if(circleButton.isSelected()) {
				double circleRadius = Math.abs(upperLeft.getX() - bottomRight.getX());
				newFigure = new Circle(startPoint, circleRadius, lineColorPicker.getValue(), fillColorPicker.getValue(),lineSlider.getValue());
			}

			else if(ellipseButton.isSelected()){
				double aRadius = Math.abs(upperLeft.getX() - bottomRight.getX())/2;
				double bRadius = Math.abs(upperLeft.getY() - bottomRight.getY())/2;
				newFigure = new Ellipse(new Point(upperLeft.getX() + aRadius, upperLeft.getY() + bRadius), aRadius,bRadius,
						lineColorPicker.getValue(), fillColorPicker.getValue(), lineSlider.getValue());
			}

			else if(squareButton.isSelected()){
				newFigure = new Square(upperLeft,bottomRight,lineColorPicker.getValue(), fillColorPicker.getValue(), lineSlider.getValue());
			}

			else if(lineButton.isSelected()){
				newFigure = new Line(startPoint,endPoint,lineColorPicker.getValue(), fillColorPicker.getValue(), lineSlider.getValue());
			}

			else if(selectionButton.isSelected()){
				selectedFigures = new HashSet<>();
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState.figures()){
					if(figure.isWithinArea(upperLeft,bottomRight) || figure.pointBelongs(startPoint)) {
						found = true;
						selectedFigures.add(figure);
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
				return;
			}
			else {
				return ;
			}
			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures()) {
				if(figureBelongs(figure, eventPoint)) {
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
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				if(areSelections())
					for (Figure figure : selectedFigures)
						figure.move(diffX,diffY);
				redrawCanvas();
			}
		});
		setLeft(toolBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures()) {
			boolean isSelected = selectedFigures.contains(figure);
			figure.draw(gc, isSelected);
		}
	}

	boolean figureBelongs(Figure figure, Point eventPoint) {
		return figure.pointBelongs(eventPoint);
	}

	boolean areSelections(){
		return !selectedFigures.isEmpty();
	}

}
