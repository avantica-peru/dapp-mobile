package net.avantica.xinef.dapp.data.exception;

/**
 * Exception throw by the application when a there is a network connection exception.
 */
public class JsonException extends Exception {

  public JsonException() {
    super();
  }

  public JsonException(final String message) {
    super(message);
  }

  public JsonException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public JsonException(final Throwable cause) {
    super(cause);
  }
}
