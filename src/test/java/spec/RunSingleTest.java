package spec;

import org.junit.Rule;
import org.junit.rules.TestWatcher;

public class RunSingleTest extends BaseFixture {
	String testName;
	
	@Rule
	public TestWatcher watcher = new TestWatcher() {
		@Override
		protected void starting(org.junit.runner.Description description) {
			testName = description.getMethodName();
		};
	};

	public boolean log(String message) {
		//getBrowser().get("www.google.co.nz");
		System.out.println(message);
//		int i = 10 / 0;
		
//		storyboard.addScreenshot("Google1", "search page");
//		storyboard.addScreenshot("Google2", "search page");
//		storyboard.addScreenshot("Google3", "search page");
//
//		getLogger().warn(testName + " logging " + message);

		return true;
	}

	
}