package spec;

import org.concordion.api.AfterSpecification;
import org.concordion.api.AfterSuite;
import org.concordion.api.BeforeSpecification;
import org.concordion.api.BeforeSuite;
import org.concordion.api.FailFast;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RunWith(ConcordionRunner.class)
@FailFast
public abstract class BaseFixture {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
    public Logger getLogger() {
		return logger;
	}
	
	@BeforeSuite
	final public void beforeSuite() {
		System.out.println("@BeforeSuite");
	}
	
	@AfterSuite
	final public void afterSuite() {
		System.out.println("@AfterSuite");
	}
	
    @BeforeSpecification
    final public void beforeSpecification() {
    	// This is the name that can be given to the RunSingleTest job in Jenkins
    	String testName = this.getClass().getName().replace(BaseFixture.class.getPackage().getName() + ".", "");
    	
    	logger.info("Initialising the acceptance test class {} on thread {}", testName, Thread.currentThread().getName());
    }

    @AfterSpecification
    final public void afterSpecification() throws Exception {
    	logger.info("Tearing down the acceptance test class {} on thread {}", this.getClass().getSimpleName(), Thread.currentThread().getName());
	}
}


