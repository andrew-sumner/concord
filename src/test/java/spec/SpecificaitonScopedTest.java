package spec;

import org.junit.Rule;
import org.junit.rules.TestWatcher;

public class SpecificaitonScopedTest extends BaseWebFixture {
	String testName;
	
    //@SpecificationScoped
    Integer i = 0;
    
    @Rule
    public TestWatcher watcher = new TestWatcher() {
    	@Override
    	protected void starting(org.junit.runner.Description description) {
    		testName = description.getMethodName();	
    	};
	};
    
    public int getI() {
    	i++;
    	getLogger().debug(testName + ": setting i to " + i);
    	return i;
    }
}