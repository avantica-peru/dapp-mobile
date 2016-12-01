package net.avantica.xinef.dapp.data.exception;

/**
 * Exception throw by the application when a public investment project search can't return a valid result.
 */
public class PublicInvestmentProjectNotFoundException extends Exception {

  public PublicInvestmentProjectNotFoundException() {
    super();
  }

  public PublicInvestmentProjectNotFoundException(final String message) {
    super(message);
  }

  public PublicInvestmentProjectNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public PublicInvestmentProjectNotFoundException(final Throwable cause) {
    super(cause);
  }
}
