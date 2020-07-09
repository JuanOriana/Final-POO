package backend.model.figures;

public class IllegalFigureException extends RuntimeException {
    private static final String MESSAGE = "Figure can not be created with the given parameters";

    public IllegalFigureException() {
        super(MESSAGE);
    }
}
