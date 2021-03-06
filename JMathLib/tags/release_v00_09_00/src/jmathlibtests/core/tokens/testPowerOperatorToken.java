package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testPowerOperatorToken extends TestCase {
	protected Interpreter ml;
	
    public testPowerOperatorToken(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testPowerOperatorToken.class);
	}

	/************* simple expressions ****************************************/
    public void test001() {
        ml.executeExpression("a=2^3;");
		assertTrue(7.99 <= ml.getScalarValueRe("a"));
        assertTrue(8.01 >= ml.getScalarValueRe("a"));
	}


												    
}