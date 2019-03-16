package xyz.iotacb.cloud.core.exceptions;

public class CloudInitializeException extends Exception {
	
	/**
	 * Is used when a error occures at a initialization
	 */
	
	private static final long serialVersionUID = 1L;

	public CloudInitializeException(final String info) {
		super(info);
	}

}
