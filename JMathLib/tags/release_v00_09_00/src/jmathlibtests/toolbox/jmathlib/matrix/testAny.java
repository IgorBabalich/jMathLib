package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testAny extends TestCase {
	protected Interpreter ml;
	
    public testAny(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testAny.class);
	}

    /****** any() **********************************************************/
	public void testAny01() {
        ml.executeExpression("a=any(1.123);");
		assertTrue(true == ml.getScalarValueBoolean("a"));
	}
    public void testAny02() {
        ml.executeExpression("b=any(0.0)");
		assertTrue(false == ml.getScalarValueBoolean("b"));
	}
    public void testAny03() {
        double[][] a = {{0.0, 0.0, 0.0},{0.0, 5.0, 0.0},{0.0, 0.0, 1.0}};
        double[][] b = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.setArray("a", a, b);
        ml.executeExpression("z = any(a)");
		assertTrue(true == ml.getScalarValueBoolean("z"));
	}
    public void testAny04() {
        double[][] a = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.setArray("e", a, a);
        ml.executeExpression("z = any(e)");
		assertTrue(false == ml.getScalarValueBoolean("z"));
	}
 
}