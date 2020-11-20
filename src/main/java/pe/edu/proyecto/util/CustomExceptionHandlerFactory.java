package pe.edu.proyecto.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {
	private ExceptionHandlerFactory parent;

	@SuppressWarnings("deprecation")
	public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		//ExceptionHandler result = new CustomExceptionHandler(parent.getExceptionHandler());
		ExceptionHandler result = new ViewExpiredExceptionExceptionHandler(parent.getExceptionHandler());
		return result;
	}
}
