package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

public class gcf extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{
        
        if (getNArgIn(operands) != 0)
			throwMathLibException("gcf: number of arguments != 0");

        // get figure number
        double n = getGraphicsManager().getCurrentFigure().getHandle();
        
		return new DoubleNumberToken(n);
	}
}

/*
@GROUP
graphics
@SYNTAX
gcf
@DOC
get handle to current figure
@EXAMPLES
.
@NOTES
@SEE
gca, gco, gcbo, gcbf
*/

