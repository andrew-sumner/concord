package spec;

import org.concordion.api.FailFast;
import org.concordion.api.extension.Extension;
import org.concordion.ext.StoryboardExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(ConcordionRunner.class)
@FailFast
public abstract class BaseFixture {
	@Extension 
	public final StoryboardExtension storyboard = new StoryboardExtension().setScreenshotTaker(null);
	
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private WebDriver driver;
	
    public Logger getLogger() {
		return logger;
	}
	
    public WebDriver getBrowser() {
    	if (driver == null) {
    		driver = new FirefoxDriver();
    	}
    	
    	storyboard.setScreenshotTaker(new SeleniumScreenshotTaker(driver));
    	
    	driver.manage().window().maximize();
    	
    	return driver;
    }
    
    public void closeBrowser() {
    	if (driver != null) {
    		driver.quit();
    		driver = null;
    	}
    }
    
    @Before
    final public void beforeSpecification() {
    	// This is the name that can be given to the RunSingleTest job in Jenkins
    	String testName = this.getClass().getName().replace(BaseFixture.class.getPackage().getName() + ".", "");
    	
    	logger.info("Initialising the acceptance test class {} on thread {}", testName, Thread.currentThread().getName());
    }

    @After
    final public void afterSpecification() throws Exception {
    	logger.info("Tearing down the acceptance test class {} on thread {}", this.getClass().getSimpleName(), Thread.currentThread().getName());
    	closeBrowser();
	}
}


