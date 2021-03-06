package jmathlibtests.core;

import jmathlib.tools.junit.framework.*;

/**
 * TestSuite that runs all the tests
 *
 */
public class AllTests {

	public static void main (String[] args) {
	    jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	public static Test suite ( ) {
		TestSuite suite= new TestSuite("core");

		/* include subdirectories here */
        suite.addTest(jmathlibtests.core.functions.AllTests.suite());
        suite.addTest(jmathlibtests.core.graphics.AllTests.suite());
		suite.addTest(jmathlibtests.core.interfaces.AllTests.suite());
        suite.addTest(jmathlibtests.core.interpreter.AllTests.suite());
        suite.addTest(jmathlibtests.core.tokens.AllTests.suite());
        

		/* include tests in this directory here */
		//suite.addTest(MathLib.Tools.TestSuite.Interpreter.testParser.suite());

	    return suite;
	}
}

