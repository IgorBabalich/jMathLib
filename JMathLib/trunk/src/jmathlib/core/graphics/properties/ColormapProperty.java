package jmathlib.core.graphics.properties;


import java.text.DecimalFormat;
import jmathlib.core.graphics.*;

public class ColormapProperty extends Property
{
	private double[] red;    // vector for red-color
    private double[] green;  // vector for green-color
    private double[] blue;   // vector for blue-color

    private double[][] c = 
    { {0,         0,    0.5625},
      {0,         0,    0.6250},
      {0,         0,    0.6875},
      {0,         0,    0.6875},
      {0,         0,    0.7500},
      {0,         0,    0.8125},
      {0,         0,    0.8750},
      {0,         0,    0.9375},
      {0,         0,    1.0000},
      {0,    0.0625,    1.0000},
      {0,    0.1250,    1.0000},
      {0,    0.1875,    1.0000},
      {0,    0.2500,    1.0000},
      {0,    0.3125,    1.0000},
      {0,    0.3750,    1.0000},
      {0,    0.4375,    1.0000},
      {0,    0.5000,    1.0000},
      {0,    0.5625,    1.0000},
      {0,    0.6250,    1.0000},
      {0,    0.6875,    1.0000},
      {0,    0.7500,    1.0000},
      {0,    0.8125,    1.0000},
      {0,    0.8750,    1.0000},
      {0,    0.9375,    1.0000},
      {0,    1.0000,    1.0000},
 {0.0625,    1.0000,    0.9375},
 {0.1250,    1.0000,    0.8750},
 {0.1875,    1.0000,    0.8125},
 {0.2500,    1.0000,    0.7500},
 {0.3125,    1.0000,    0.6875},
 {0.3750,    1.0000,    0.6250},
 {0.4375,    1.0000,    0.5625},
 {0.5000,    1.0000,    0.5000},
 {0.5625,    1.0000,    0.4375},
 {0.6250,    1.0000,    0.3750},
 {0.6875,    1.0000,    0.3125},
 {0.7500,    1.0000,    0.2500},
 {0.8125,    1.0000,    0.1875},
 {0.8750,    1.0000,    0.1250},
 {0.9375,    1.0000,    0.0625},
 {1.0000,    1.0000,         0},
 {1.0000,    0.9375,         0},
 {1.0000,    0.8750,         0},
 {1.0000,    0.8125,         0},
 {1.0000,    0.7500,         0},
 {1.0000,    0.6875,         0},
 {1.0000,    0.6250,         0},
 {1.0000,    0.5625,         0},
 {1.0000,    0.5000,         0},
 {1.0000,    0.4375,         0},
 {1.0000,    0.3750,         0},
 {1.0000,    0.3125,         0},
 {1.0000,    0.2500,         0},
 {1.0000,    0.1875,         0},
 {1.0000,    0.1250,         0},
 {1.0000,    0.0625,         0},
 {1.0000,         0,         0},
 {0.9375,         0,         0},
 {0.8750,         0,         0},
 {0.8125,         0,         0},
 {0.7500,         0,         0},
 {0.6875,         0,         0},
 {0.6250,         0,         0},
 {0.5625,         0,         0},
 {0.5000,         0,         0}};

    public ColormapProperty(PropertySet parent, String name)
    {
    super(parent, name);
      try{
          set(c);
      }
      catch (Exception e){
       
      }
   }

    public ColormapProperty(PropertySet parent, String name, 
	                     double[] red, double[] green, double[] blue)
	{
		super(parent, name);
		this.red   = red;
        this.green = green;
        this.blue  = blue;
	}

	public Object get()
	{
		return getArray();
	}

	public void set(Object value) throws PropertyException
	{
	    
	    try
        {
	        
	        double[][] array = (double[][])value;
	    
    	    int n = array.length;
            
            for (int i=0; i<n; i++)
            {
                red[i]   = array[i][0];
                green[i] = array[i][1];
                blue[i]  = array[i][2];
                
                if (red[i]<0    || red[i]>1   ||
                    green[i]<0  || green[i]>1 ||
                    blue[i]<0   || blue[i]>1     )
                throw new PropertyException("invalid array value - ");
            }

        }
        catch (ClassCastException e)
		{
			throw new PropertyException("invalid array value - " + value.toString());
		}
	}

	public double[][] getArray()
	{
	    double[][] array = new double[red.length][3];
	    
	    for (int i=0; i<red.length; i++)
	    {
	        array[i][0] = red[i];
            array[i][1] = green[i];
            array[i][2] = blue[i];
	    }
		return array;
	}

	public void setArray(double[] a)
	{
		try { set(a); }
		catch (PropertyException e) { }
	}

	//private void setArrayInternal(double[] v)
	//{
	//	array = v;
	//	valueChanged();
	//}

	public String toString()
	{
		
		String s = "[ ";
		DecimalFormat fmt = new DecimalFormat("0.0 ");
		for (int i=0; i<red.length; i++)
        {
            s += fmt.format(red[i]);
            s += fmt.format(green[i]);
            s += fmt.format(blue[i]);
        }
        s += "]";
        return s;
	}
}
