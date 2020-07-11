package frontend;

import backend.CanvasState;
import javafx.scene.layout.BorderPane;

public class PaintPane extends BorderPane {

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		ToolBox toolBox = new ToolBox();
		FigureCanvas canvas = new FigureCanvas(canvasState,toolBox,statusPane);
		setLeft(toolBox);
		setRight(canvas);

	}
}
