package somoCompany.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	Boolean x = false;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(x == false) {
			x = true;
			return true;
		}
		
		return false;
	}
	
	//Whenever test fails it will come to this block of code to check whether the Test is actually a failure or a spook failure.

}
