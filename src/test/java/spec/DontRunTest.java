package spec;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class DontRunTest {
	String testName = getTestName();

	private String getTestName() {
		System.out.println("DontRunTest: Waiting here....");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DontRunTest: Done waiting!");
		return "TestName";
	}

	public String getGreeting() {
		return "Hi";
	}
}