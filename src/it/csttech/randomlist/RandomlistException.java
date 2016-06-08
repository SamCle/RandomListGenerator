package it.csttech.randomlist;

public class RandomlistException extends Exception {

  static final long serialVersionUID = 1L;
  public RandomlistException() {
      super();
  }

  public RandomlistException(String message) {
      super(message);
  }

  public RandomlistException(String message, Throwable cause) {
      super(message, cause);
  }

  public RandomlistException(Throwable cause) {
      super(cause);
  }

}
