package uet.oop.bomberman.exception;

public class BombermanException extends Exception {
    public BombermanException() {
    }

    public BombermanException(String message) {
        super(message);
    }

    public BombermanException(Throwable cause) {
        super(cause);
    }

    public BombermanException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
