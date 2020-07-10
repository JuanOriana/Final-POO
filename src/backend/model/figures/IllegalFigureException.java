package backend.model.figures;

public class IllegalFigureException extends RuntimeException {
    private static final String MESSAGE = "La figura no puede ser creada con los parametros dados";

    public IllegalFigureException() {
        super(MESSAGE);
    }
}
