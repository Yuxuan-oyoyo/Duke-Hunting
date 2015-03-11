package exception;

/**
 * This is the IllegalInputException
 * The exception will be thrown when an illegal input is obtained
 * @author G4-T03
 */
public class IllegalInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an IllegalInputException
	 * @param msg The message the exception will pass
	 */
	public IllegalInputException(String msg) {
        super(msg);
    }
}