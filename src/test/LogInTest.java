package test;

import org.junit.Test;

import source.ApiFunctions;
import source.LogIn;

/**
 * UnitTest Class to test LogIn.
 * @author Lourens W
 */

public class LogInTest {
	
	/** Declares the api. */
	private ApiFunctions api = new ApiFunctions();

	/**
	 * Creates LogIn.
	 */
	@Test
	public final void testLogIn() {
		new LogIn(api);
	}

}
