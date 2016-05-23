package spec;

public class RunSingle2Test extends BaseFixture {
	String testName = getTestName();

	private String getTestName() {
		System.out.println("Waiting here....");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done waiting!");
		return "TestName";
	}

	public boolean log(String message) {
		//getBrowser().get("www.google.co.nz");
		getLogger().info("GOT HERE");
		
		return true;
	}
}