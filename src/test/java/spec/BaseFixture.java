package spec;

import org.concordion.api.AfterSuite;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public abstract class BaseFixture {
	
    @AfterSuite
    final public void afterSpecification() throws Exception {
    	System.out.println("AFTER SUITE");
	}
    
}


