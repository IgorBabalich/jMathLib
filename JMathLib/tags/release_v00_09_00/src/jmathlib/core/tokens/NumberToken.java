package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;

import java.text.NumberFormat;

/**Class representing numbers used in expression
holds a 2D array of complex numers in a 3d array
values[yx][REAL/IMAG]
All operations on a NumberToken create a new NumberToken*/
public class NumberToken extends DataToken
{            
    /**Complex values of the token
     * the data is organizes as on single vector. 
     * e.g. a=[1,2;3,4] will be stored like below
     * values = 1
     *          3
     *          2
     *          4         */
    /**stores the number format for displaying the number*/
    private static NumberFormat numFormat = NumberFormat.getInstance();

    /**Index for real values within array*/
    protected static final int REAL = 0;
    
    /**Index for Imaginary values within array*/
    protected static final int IMAG = 1;


    public NumberToken()
    {
        super(); 
    }

    public NumberToken(int priority, String type)
    {
        super(priority, type);
    }
    
    /**
     * Convert y,x points to element number n
     * @param  y
     * @param  x
     * @return n
     */
    protected int yx2n(int y, int x)
    {
        int n = x*sizeY + y;
        return n;
    }

    /*protected int n2y(int n)
    {
        int x = (int) (n/sizeY); // column to start
        int y = n - x*sizeY;     // row to start
        return y;
    }*/

    /*protected int n2x(int n)
    {
        int x = (int) (n/sizeY); // column to start
        return x;
    }*/

    /**
     *  Convert from index to n (e.g. index={2,3,5} -> n)
     *  @param
     *  @return
     */
    public int index2n(int[] index)
    {
        String s="";
        for (int i=0; i<index.length; i++)
            s += index[i]+" ";
            
        ErrorLogger.debugLine("NumberToken: index2n: index: "+s);
    
        int dn = noElem;
        int n  = 0;
        
        for (int i=index.length-1; i>0; i--)
        {
            dn = dn / sizeA[i];
            n += dn * index[i];
        }
        
        n+= index[0];
        
        return n;
    }
    
    /**
     * 
     * @param y
     * @param x
     * @return
     */
    public OperandToken getElement(int y, int x)
    {   
        int n = yx2n(y,x);
        return getElement(n);
    }


    /**
     * 
     * @param y
     * @param x
     * @param num
     */
    public void setElement(int y, int x, OperandToken num)
    {
        int    n    = yx2n(y,x);
        setElement(n, num);
    }
    
    /**Evaluate the token. This causes it to return itself*/
    public OperandToken evaluate(Token[] operands)
    {
        return this;    
    }

    /**Checks if this operand is a numeric value
    @return true if this is a number, false if it's 
    an algebraic expression*/
    public boolean isNumeric()
    {
        return true;
    }

    /**@return true if this number token is a scalar (1*1 matrix)*/
    public boolean isScalar()
    {

        for (int i=0; i<sizeA.length; i++)
        {
            // in case one entry in the size-array is unequal 1 it 
            //  is not a scalar any more
            if (sizeA[i]!=1)
                return false;
        }

        return true;
    }

    public String toString()
    {
        return null;
    }


} // end NumberToken
