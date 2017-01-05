package net.avantica.xinef.dapp.data.exception;

/**
 * Exception throw by the application when a there is a network connection exception.
 */
public class ReverseGeocodingNotFoundException extends Exception {

  public ReverseGeocodingNotFoundException() {
    super();
  }

  public ReverseGeocodingNotFoundException(final String message) {
    super(message);
  }

  public ReverseGeocodingNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ReverseGeocodingNotFoundException(final Throwable cause) {
    super(cause);
  }
}
