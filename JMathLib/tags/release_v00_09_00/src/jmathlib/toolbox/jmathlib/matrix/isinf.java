package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function that return 1.0 is all elements of the argument are nonzero  */
public class isinf extends ExternalFunction
{
	/**return a  matrix 
	* @param operands[0] = matrix 
	* @return 1.0 if all elements of the argument are nonzero  */
	public OperandToken evaluate(Token[] operands)
	{

		// two operands (e.g. not(A) )
        if (getNArgIn(operands) != 1)
			throwMathLibException("isnan: number of arguments != 1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("isnan: works on numbers only");


		// get data from arguments
        double[][] a_r = ((DoubleNumberToken)operands[0]).getValuesRe();
        double[][] a_i = ((DoubleNumberToken)operands[0]).getValuesIm();
		int a_dy       = a_i.length;
        int a_dx       = a_i[0].length;	

        double[][] ret = ((DoubleNumberToken)operands[0]).getValuesIm();
	
		for (int xi=0; xi<a_dx ; xi++)
		{
			for (int yi=0; yi<a_dy ; yi++)
			{
                
				if ( (a_r[yi][xi]==Double.POSITIVE_INFINITY) ||
                     (a_i[yi][xi]==Double.POSITIVE_INFINITY) ||   
                     (a_r[yi][xi]==Double.NEGATIVE_INFINITY) ||
                     (a_i[yi][xi]==Double.NEGATIVE_INFINITY)    )
                    ret[yi][xi] = 1.0;
                else
                    ret[yi][xi] = 0.0;

            }
		}	
        
		return new DoubleNumberToken(ret);		
		
        
	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
answer = isinf(matrix)
@DOC
.
@NOTES
@EXAMPLES
isinf([3,4;Inf,9]) ->  [0,0;1,0]
@SEE
isreal, isnan, isfinite
*/

