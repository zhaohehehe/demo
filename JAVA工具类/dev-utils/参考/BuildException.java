


import java.io.PrintStream;
import java.io.PrintWriter;

public class BuildException extends RuntimeException
{
  private Throwable cause;

  public BuildException()
  {
  }

  public BuildException(String message)
  {
    super(message);
  }

  public BuildException(String message, Throwable cause)
  {
    super(message);
    this.cause = cause;
  }


  public BuildException(Throwable cause)
  {
    super(cause.toString());
    this.cause = cause;
  }

  public Throwable getException()
  {
    return this.cause;
  }

  public Throwable getCause()
  {
    return getException();
  }

  public void printStackTrace()
  {
    printStackTrace(System.err);
  }

  public void printStackTrace(PrintStream ps)
  {
    synchronized (ps) {
      super.printStackTrace(ps);
      if (this.cause != null) {
        ps.println("--- Nested Exception ---");
        this.cause.printStackTrace(ps);
      }
    }
  }

  public void printStackTrace(PrintWriter pw)
  {
    synchronized (pw) {
      super.printStackTrace(pw);
      if (this.cause != null) {
        pw.println("--- Nested Exception ---");
        this.cause.printStackTrace(pw);
      }
    }
  }
}
