package myextensions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.listener.ThrowableCaughtEvent;
import org.concordion.api.listener.ThrowableCaughtListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log exceptions as soon as they occur so they appear in the logs in the correct order
 * @author sumnera
 */
public class ExceptionLoggingExtension implements ConcordionExtension, ThrowableCaughtListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionLoggingExtension.class);
	private boolean logStackTrace = false;
	
	public ExceptionLoggingExtension() {
	}
	
	public ExceptionLoggingExtension(boolean logStackTrace) {
		this.logStackTrace = logStackTrace;
	}
	
    @Override
    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withThrowableListener(this);
    }
    
    @Override
	public void throwableCaught(ThrowableCaughtEvent event) {
    	String message = event.getThrowable().getMessage();

    	if (logStackTrace) {
    		message += "\n" + getStackTrace(event.getThrowable());
    	}
    	
    	// Indent multi-line errors to make it easier to scan the log
    	message = message.replace("\r\n", "\r\n\t");
    	message = message.replace("\n", "\n\t");
    	
		LOGGER.error(message);		
	}
    
    private String getStackTrace(final Throwable throwable) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream printStream = new PrintStream(baos);
		throwable.printStackTrace(printStream);
		String exceptionStr = "";
		try {
			exceptionStr = baos.toString("UTF-8");
		} catch (Exception ex) {
			exceptionStr = "Unavailable";
		}
		return exceptionStr;
	}
}