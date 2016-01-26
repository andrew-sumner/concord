package spec;

import org.concordion.api.AfterSpecification;
import org.concordion.api.BeforeSpecification;
import org.concordion.api.FailFast;
import org.concordion.api.extension.Extension;
import org.concordion.ext.LoggingFormatterExtension;
import org.concordion.ext.loggingFormatter.LogbackHelper;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RunWith(ConcordionRunner.class)
@FailFast
public abstract class BaseFixture {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
//    private static TestData testData = new TestData();
    
    @Extension public final LoggingFormatterExtension loggingFormatter = new LoggingFormatterExtension();
//    @Extension public final SpecificationProcessingExtension specificationProcessing = new SpecificationProcessingExtension(this);
	
//    public TestData testData() {
//    	return testData;
//    }
	
    static {
    	LogbackHelper.logInternalStatus();
	}
    
	public Logger getLogger() {
		return logger;
	}
	
	
    @BeforeSpecification
    final public void startUp() {
    	LogbackHelper.startTestLogging(this);
    	
    	// This is the name that can be given to the RunSingleTest job in Jenkins
    	String testName = this.getClass().getName().replace(BaseFixture.class.getPackage().getName() + ".", "");
    	
    	logger.info("Initialising the acceptance test class {} on thread {}", testName, Thread.currentThread().getName());
    }

    @AfterSpecification
    final public void tearDown() throws Exception {
    	logger.info("Tearing down the acceptance test class {} on thread {}", this.getClass().getSimpleName(), Thread.currentThread().getName());
    	
    	LogbackHelper.stopTestLogging();
	}
}


