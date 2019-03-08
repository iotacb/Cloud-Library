package xyz.iotacb.cloud.core.exceptions;

public class CloudCreateException extends Exception {
	
	/**
	 * Is used when a error occures at a declaration
	 */
	
	private static final long serialVersionUID = 1L;

	public CloudCreateException(final String info) {
		super(info);
	}

}
