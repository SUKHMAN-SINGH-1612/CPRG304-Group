package exceptions;

public class DuplicateKeyException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 682267963980463371L;

	public DuplicateKeyException()
	{
		super();
	}

	/**
	 * @param message error message specific to cause of error.
	 */
	public DuplicateKeyException( String message )
	{
		super( message );
	}
}
