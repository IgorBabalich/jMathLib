package jmathlib.toolbox.funfun;

/* This file is part or JMATHLIB 
 * author:  Stefan Mueller 03/27/2005 initial version   
 * */

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;


/**An external function for computing a mesh of a matrix  */
public class euler extends ExternalFunction
{
	/**integrates a "function" using the euler forward integration method
    *     [t,y] = euler (function, [t0 tf], y0, dt) 
    */
	public OperandToken evaluate(Token[] operands)
	{

		// [t,y] = euler (function, [t0 tf], y0, dt)
		if (getNArgIn(operands)!=4)
			throwMathLibException("euler: number of input arguments != 4");

		// Check number of return arguments
		if (getNoOfLeftHandArguments()!=2)
		    throwMathLibException("euler: number of output arguments != 2");

		if ( !(operands[0] instanceof CharToken) ||
             !(operands[1] instanceof DoubleNumberToken) ||
             !(operands[2] instanceof DoubleNumberToken) ||
             !(operands[3] instanceof DoubleNumberToken) )
			throwMathLibException("euler: wrong typ or types of parameters");

		// get name of function to integrate
        String funcName = ((CharToken)operands[0]).toString();
        
        // get time span: initial time (t0) and final time (tf)
        double[][] t_span =  ((DoubleNumberToken)operands[1]).getReValues();
        if ((t_span.length != 1) || (t_span[0].length != 2))
                throwMathLibException("euler: need [t0 tf]");
        double     t0=  t_span[0][0];
        double     tf=  t_span[0][1];
        
        // check if t0 < tf
        if (t0>=tf)
            throwMathLibException("euler: start time must be smaller than end time");
        
        // get initial state
        double[][] y0 =  ((DoubleNumberToken)operands[2]).getReValues();
        if (y0.length != 1)
                throwMathLibException("euler: initial conditions work only row vectors");
        
        // length of state vector (always use row vector)
        int m = y0[0].length;
        
        // get step size
        double dt =  ((DoubleNumberToken)operands[3]).getValueRe();
                
        // calculate number of integration steps
        int n = new Double(Math.round((tf-t0)/dt)).intValue();
        
        // initialize time and output arrays  [n rows, initial conditions size rows]
        double[][] t     = new double[n][1];
        double[][] y_out = new double[n][m];
        
        System.out.println("t0  = "+t0);
        System.out.println("tf  = "+tf);
        

        // create function which will be used for integration
        //FunctionToken function = new FunctionToken(funcName);

        // preparation of initial conditions
        int k=1;
        double[][] y = new double[m][1];   // state is a column vector
        
        for (int i=0; i< m; i++)
        {
            //System.out.println("y("+i+")= "+y0[i][0]);
            y[i][0]     = y0[0][i];  // row vector -> column vector
            y_out[0][i] = y0[0][i];  // y(t=t0)
        }
        
        // loop to integrate function
        while(k<n)
        {
            //System.out.println("k  = "+k);

            FunctionToken function = new FunctionToken(funcName);

            // set up operands for integrated function 
            OperandToken[] op = new OperandToken[2];
            op[0]=new DoubleNumberToken(t0 + k * dt);
            op[1]=new DoubleNumberToken(y);
            function.setOperands(op);
        
            //evaluate function
            OperandToken result =  function.evaluate(null);
        
            // retrieve output vector of integration
            if ( !(result instanceof DoubleNumberToken) )
                throwMathLibException("euler: wrong return type of function");

            // build output
            double[][] y_ret =  ((DoubleNumberToken)result).getReValues();
            for (int i=0; i< m; i++)
            {
                //System.out.println("dy("+k+" "+i+")= "+y_ret[i][0]);
                y_out[k][i] = y_out[k-1][i] + y_ret[i][0] * dt;
                //System.out.println("y("+k+" "+i+")= "+y_out[k][i]);
                y[i][0]     = y_out[k][i];
            }
            t[k][0] = t0 + k * dt;    
            
            k++;
            
        } // end integration loop
        
  		OperandToken values[][] = new OperandToken[1][2];
		values[0][0] = new DoubleNumberToken(t);
		values[0][1] = new DoubleNumberToken(y_out);
		return new MatrixToken( values );

	} // end eval
}

/*
@GROUP
FunFun
@SYNTAX
[t,y] = euler (function, [t0 tf], y0, dt)
@DOC
Integration of a given function using Euler's method.
@EXAMPLES
[t,y]=euler('vdp1',[0,10],[2,0],0.1)
@NOTES
@SEE

*/
