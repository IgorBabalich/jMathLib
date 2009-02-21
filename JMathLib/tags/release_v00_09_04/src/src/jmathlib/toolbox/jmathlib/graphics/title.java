package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**Add text to the title of the current axes*/
public class title extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		//ErrorLogger.debugLine("title: evaluate");

        if (getNArgIn(operands) != 1)
			throwMathLibException("title: number of arguments != 1");
            
	    if (!(operands[0] instanceof CharToken)) 
        	throwMathLibException("title: argument must be a string");

		String title = ((CharToken)operands[0]).toString();

		globals.getGraphicsManager().getCurrentFigure().getCurrentAxes().setTitle(title);
		globals.getGraphicsManager().getCurrentFigure().repaint();

		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
title(title-string)
@DOC
Displays a title on a graph
@EXAMPLES
<programlisting>
title("graph title")
</programlisting>
@NOTES
@SEE
xlabel, ylabel, zlabel
*/

