package spec;

import org.concordion.api.extension.Extension;
import org.concordion.ext.LoggingFormatterExtension;
import org.concordion.ext.loggingFormatter.LogbackAdaptor;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import myextensions.SpecificationProcessingExtension;

@RunWith(ConcordionRunner.class)
public abstract class BaseWebFixture {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Extension public final LoggingFormatterExtension loggingFormatter = new LoggingFormatterExtension();
	@Extension public final SpecificationProcessingExtension specificationProcessing = new SpecificationProcessingExtension(this);
	
    static {
    	LogbackAdaptor.logInternalStatus();
	}
    
	public Logger getLogger() {
		return logger;
	}
	
	
    @myextensions.BeforeSpecification
    final public void startUp() {
    	LogbackAdaptor.startTestLogging(this);
    	
    	// This is the name that can be given to the RunSingleTest job in Jenkins
    	String testName = this.getClass().getName().replace(BaseWebFixture.class.getPackage().getName() + ".", "");
    	
    	logger.info("Initialising the acceptance test class {} on thread {}", testName, Thread.currentThread().getName());
    }

    @myextensions.AfterSpecification
    final public void tearDown() throws Exception {
    	logger.info("Tearing down the acceptance test class {} on thread {}", this.getClass().getSimpleName(), Thread.currentThread().getName());
    	
    	LogbackAdaptor.stopTestLogging();
	}
}
