package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for writing to the main display*/
public class disp extends ExternalFunction
{
	/**write operand to main display
	@param operand[n] = items to display*/
	public OperandToken evaluate(Token[] operands)
	{
		for(int index = 0; index < operands.length; index++)
		{
			getInterpreter().displayText(operands[index].toString());
		}
		
		return null; 
	}
}

/*
@GROUP
system
@SYNTAX
DISP(string1, string2, string3, ...)
@DOC
This displays a set of lines of text.
@NOTES
@EXAMPLES
DISP("Hello", "world")
Hello
world
@SEE
*/
