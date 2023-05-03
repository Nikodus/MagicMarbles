package Version1.magicmarbles.model;

/**
 * The exception class for exceptions with the magic marble game
 */
public class MMException extends Exception {

	private static final long serialVersionUID = 8614515858833371347L;


	public MMException() {
		super("Invalid Move");
	}

	public MMException(String message) {
		super(message);
	}
// TODO MMException implementation
}
