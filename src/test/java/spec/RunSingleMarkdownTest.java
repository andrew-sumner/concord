package spec;

import org.concordion.api.AfterSpecification;
import org.concordion.api.BeforeSpecification;
import org.junit.Rule;
import org.junit.rules.TestWatcher;


public class RunSingleMarkdownTest extends BaseFixture {	
	String testName;
	
    @Rule
    public TestWatcher watcher = new TestWatcher() {
    	@Override
    	protected void starting(org.junit.runner.Description description) {
    		testName = description.getMethodName();	
    	};
	};
	
	@BeforeSpecification
	public void startTest() {
		getLogger().error("START " + this.getClass().getSimpleName());
//		testData().value = "a";
	}
	
	@AfterSpecification
	public void endTest() {
		getLogger().error("END " + this.getClass().getSimpleName());
	}
    
    public boolean log(String message) {
    	getLogger().warn(testName + " logging " + message);
    	
    	return true;
    }
}