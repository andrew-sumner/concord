package spec;

import java.util.concurrent.atomic.AtomicInteger;

import org.concordion.api.SpecificationScoped;
import org.junit.Rule;
import org.junit.rules.TestWatcher;

public class SpecificaitonScopedTest extends BaseWebFixture {
	String testName;
	
    @SpecificationScoped
    AtomicInteger i = new AtomicInteger(2);
    
    @SpecificationScoped
    String b = "1";
    
    @Rule
    public TestWatcher watcher = new TestWatcher() {
    	@Override
    	protected void starting(org.junit.runner.Description description) {
    		testName = description.getMethodName();	
    	};
	};
    
    public String getI() {    	
    	i.incrementAndGet();
    	getLogger().debug(testName + ": setting i to " + i.get());
    	//return i.get();
    	
    	b = b + "1";
    	
    	return b;
    }
}