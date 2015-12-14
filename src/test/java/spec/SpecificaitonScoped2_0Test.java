package spec;

import java.util.concurrent.atomic.AtomicInteger;

import org.concordion.api.SpecificationScoped;
import org.junit.Rule;
import org.junit.rules.TestWatcher;

public class SpecificaitonScoped2_0Test extends BaseWebFixture2_0 {
	String testName;
	
    @SpecificationScoped
    AtomicInteger i = new AtomicInteger(2);
    
    @SpecificationScoped
    String z = "1";
    
    @SpecificationScoped
    TestMe testme = new TestMe();
        
    @Rule
    public TestWatcher watcher = new TestWatcher() {
    	@Override
    	protected void starting(org.junit.runner.Description description) {
    		testName = description.getMethodName();	
    	};
	};
    
    public int getI() {
    	getLogger().debug(testName + ": setting i to " + i.incrementAndGet());
    	
    	if (i.get() == 4) {
    		//throw new RuntimeException("i > 2");
    	}
    	
    	z = z + String.valueOf(i.get());
    	testme.testme ++;
    	
    	return i.get();
    }
    
    public class TestMe {
    	int testme = 0;
    }
}