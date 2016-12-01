package net.avantica.xinef.dapp.data.exception;


import net.avantica.xinef.dapp.domain.exception.ErrorBundle;

/**
 * Wrapper around Exceptions used to manage errors in the repository.
 */
public class RepositoryErrorBundle implements ErrorBundle {

  private final Exception exception;

  public RepositoryErrorBundle(Exception exception) {
    this.exception = exception;
  }

  @Override
  public Exception getException() {
    return exception;
  }

  @Override
  public String getErrorMessage() {
    String message = "";
    if (this.exception != null) {
      message = this.exception.getMessage();
    }
    return message;
  }
}
