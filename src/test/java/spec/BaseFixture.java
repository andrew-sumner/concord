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
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private WebDriver driver;
	
    public Logger getLogger() {
		return logger;
	}
	
    public WebDriver getBrowser() {
    	if (driver == null) {
    		driver = new FirefoxDriver();
    	}
    	
    	driver.manage().window().maximize();
    	
    	return driver;
    }
    
    public void closeBrowser() {
    	if (driver != null) {
    		driver.quit();
    		driver = null;
    	}
    }

    @After
    final public void afterSpecification() throws Exception {
    	closeBrowser();
	}
}


