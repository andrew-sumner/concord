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
		return true;
	}

	
}