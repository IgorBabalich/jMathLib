package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for calculating a magic matrix */
/* The function has been taken from the JAMA-examples */
public class magic extends ExternalFunction
{
	/**return a  matrix 
	@param operands[0] = dimension of magic matrix */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		// one operands (e.g. magic(n) )
		if (getNArgIn(operands) != 1)
			throwMathLibException("magic: number of arguments != 1");
            
	    if (!(operands[0] instanceof DoubleNumberToken)) 
        	throwMathLibException("magic: argument must be a number");

        // dimension of matrix
        int n = (int)((DoubleNumberToken)operands[0]).getValueRe();
        
        double[][] M = magic_calculation(n);

		return new DoubleNumberToken(M);		

	} // end eval

    public double[][] magic_calculation(int n)
    {
        // create empty magic matrix
        double[][] M = new double[n][n];

        // Odd order
        if ((n % 2) == 1)
        {
            int a = (n+1)/2;
            int b = (n+1);
            for (int j = 0; j < n; j++)
            {
                for (int i = 0; i < n; i++)
                {
                    M[i][j] = n*((i+j+a) % n) + ((i+2*j+b) % n) + 1;
                }
            }
        } 
        // Doubly Even Order
        else if ((n % 4) == 0)
        {
            for (int j = 0; j < n; j++) 
            {
                for (int i = 0; i < n; i++) 
                {
                    if (((i+1)/2)%2 == ((j+1)/2)%2) 
                    {
                        M[i][j] = n*n-n*i-j;
                    } 
                    else
                    {
                        M[i][j] = n*i+j+1;
                    }
                }
            }
        }
        // Singly Even Order
        else 
        {
            int p = n/2;
            int k = (n-2)/4;
            double[][] A = magic_calculation(p);
            for (int j = 0; j < p; j++) 
            {
                for (int i = 0; i < p; i++) 
                {
                    double aij = A[i][j];
                    M[i][j] = aij;
                    M[i][j+p] = aij + 2*p*p;
                    M[i+p][j] = aij + 3*p*p;
                    M[i+p][j+p] = aij + p*p;
                }
            }
            for (int i = 0; i < p; i++) 
            {
                for (int j = 0; j < k; j++) 
                {
                    double t = M[i][j]; M[i][j] = M[i+p][j]; M[i+p][j] = t;
                }
                for (int j = n-k+1; j < n; j++) 
                {
                    double t = M[i][j]; M[i][j] = M[i+p][j]; M[i+p][j] = t;
                }
            }
            double t = M[k][0]; 
            M[k][0] = M[k+p][0]; 
            M[k+p][0] = t;
            t = M[k][k]; 
            M[k][k] = M[k+p][k]; 
            M[k+p][k] = t;
        }
        
        return M;
        
    } // end magic_calculation
}



/*
@GROUP
matrix
@SYNTAX
magic(n)
@DOC
creates a magic matrix with equal sums of all rows and column 
@NOTES
@EXAMPLES
> magic(3)
 ans = [8,1,6]
       [3,5,7]
       [4,9,2]
@SEE

*/
