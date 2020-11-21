package uet.oop.bomberman.exception;

public class LevelLoaderException extends BombermanException {
    public LevelLoaderException() {

    }

    public LevelLoaderException(String message) {
        super(message);
    }

    public LevelLoaderException(Throwable cause) {
        super(cause);
    }

    public LevelLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
