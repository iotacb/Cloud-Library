package xyz.iotacb.cloud.core.exceptions;

public class CloudFalseArgumentException extends Exception {
	
	/**
	 * Is used when a cloud library project recieves a false run argument
	 */
	
	private static final long serialVersionUID = 1L;

	public CloudFalseArgumentException(final String info) {
		super(info);
	}

}
