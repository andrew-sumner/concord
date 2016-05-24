package spec;

import org.concordion.api.AfterSuite;
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
    @AfterSuite
    final public void afterSpecification() throws Exception {
    	System.out.println("AFTER SUITE");
	}
}


