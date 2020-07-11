package frontend;

import frontend.buttons.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

@SuppressWarnings("FieldCanBeLocal")
public class ToolBox extends VBox {

    // Constantes de inicializacion
    private final static Color LINE_COLOR = Color.BLACK;
    private final static Color FILL_COLOR = Color.YELLOW;

    // Botones
    private ToggleButton selectionButton = new ToggleButton("Seleccionar");
    private Button removeButton = new Button("Borrar");
    private Button backButton = new Button("Al Fondo");
    private Button frontButton = new Button("Al Frente");
    private ButtonBase[] buttons;
    private GeneratorToggleButton[] generators;
    private Button[] regularButtons;

    // Sliders y color-pickers
    private Slider lineSlider = new Slider(1,50,1);
    private ColorPicker lineColorPicker = new ColorPicker(LINE_COLOR);
    private ColorPicker fillColorPicker = new ColorPicker(FILL_COLOR);

    public ToolBox() {

        //Generadores
        GeneratorToggleButton rectangleButton = new RectangleToggleButton("Rectángulo");
        GeneratorToggleButton circleButton = new CircleToggleButton("Círculo");
        GeneratorToggleButton ellipseButton = new EllipseToggleButton("Elipse");
        GeneratorToggleButton squareButton = new SquareToggleButton("Cuadrado");
        GeneratorToggleButton lineButton = new LineToggleButton("Linea");

        buttons = new ButtonBase[]{selectionButton, rectangleButton, circleButton, squareButton, ellipseButton,
                lineButton, removeButton, backButton, frontButton};
        generators = new GeneratorToggleButton[]{rectangleButton, circleButton, squareButton, ellipseButton, lineButton};
        regularButtons = new Button[]{removeButton, backButton, frontButton};

        ToggleGroup tools = new ToggleGroup();

        // Seteo de la visualizacion de los botones
        for (ButtonBase button : buttons){
            button.setMinWidth(90);
            button.setCursor(Cursor.HAND);
        }

        for (GeneratorToggleButton gen : generators){
            gen.setStyle("-fx-base: #5544ff; -fx-text-fill: #ffffff;");
            gen.setToggleGroup(tools);
        }

        for (Button button : regularButtons) {
            button.setStyle("-fx-base: #ffaa00;");
        }

        selectionButton.setStyle("-fx-base: #ff5544; -fx-text-fill: #ffffff;");
        selectionButton.setToggleGroup(tools);

        // Seteo visualizacion de las herramientas de linea
        Label lineLabel = new Label("Borde");
        Control[] lineTools = {lineLabel,lineSlider, lineColorPicker};
        lineSlider.setShowTickMarks(true);
        lineSlider.setShowTickLabels(true);

        // Seteo visualizacion de las herramientas de fill
        Label fillLabel = new Label("Relleno");
        Control[] fillTools = {fillLabel,fillColorPicker};

        // Composicion de la VBox
        setSpacing(10);
        getChildren().addAll(buttons);
        getChildren().addAll(lineTools);
        getChildren().addAll(fillTools);
        setPadding(new Insets(5));
        setStyle("-fx-background-color: #777");
        setPrefWidth(100);
    }

    public void setOnBackAction(EventHandler<ActionEvent> action){
        backButton.setOnAction(action);
    }

    public void setOnFrontAction(EventHandler<ActionEvent> action){
        frontButton.setOnAction(action);
    }

    public void setOnRemoveAction(EventHandler<ActionEvent> action){
        removeButton.setOnAction(action);
    }

    public void setFillAction(EventHandler<ActionEvent> action){
        fillColorPicker.setOnAction(action);
    }

    public void setLineAction(EventHandler<ActionEvent> action){
        lineColorPicker.setOnAction(action);
    }

    public GeneratorToggleButton[] getGenerators() {
        return generators;
    }

    public Slider getLineSlider() {
        return lineSlider;
    }

    public Color getLineColor() {
        return lineColorPicker.getValue();
    }

    public Color getFillColor() {
        return fillColorPicker.getValue();
    }

    public double getLineWidth(){
        return lineSlider.getValue();
    }

    public boolean onSelected(){
        return selectionButton.isSelected();
    }
}
