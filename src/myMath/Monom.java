
package myMath;
/**
 * name's : amit znaft id: 205478431 and yuval cohen id :311483432
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{

	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * this constructor gets String of monom and init new monom with this values
	 * Proper input :{ "ax^b" , "ax", "a"  | where a is a real number and b is an integer (summed a none negative) }
	 * @param m = input monom's String
	 * @param a = creating an array with the value of the coefficient and the power of this Monom
	 * 
	 */
	public Monom (String m) {
		if (m == null || m.length() == 0) {
			throw new RuntimeException("Not a Monom");
		}
		m= m.toLowerCase();
		if (m.contains("x") && m.contains("^")) {
			String a [] = m.split("x\\^");
			if (a[0].length() == 0) {
				this.set_coefficient(1);
				this.set_power(Integer.parseInt(a[1]));
			}
			else if (a[0].equals("-")) {
				this.set_coefficient(-1);
				this.set_power(Integer.parseInt(a[1]));
			}
			else {
				this.set_coefficient(Double.parseDouble(a[0]));
				this.set_power(Integer.parseInt(a[1]));
			}
		}
		else if (m.contains("x")) {
			String a [] = m.split("x");
			if (m.length() == 1 ) {
				this.set_coefficient(1);
				this.set_power(1);
			}
			else if (a[0].equals("-")) {
				this.set_coefficient(-1);
				this.set_power(1);
			}
			else {
				this.set_coefficient(Double.parseDouble(a[0]));
				this.set_power(1);
			}
		}
		else {
			this.set_coefficient(Double.parseDouble(m));
			this.set_power(0);
		}
	}
/**
 * this function represents a simple function of type y=f(x), where both y and x are real numbers.
 * @param x = the value of the point
 * @return this Monom value at f(x)
 */
	public double f(double x) {
		double a = this.get_coefficient() * (Math.pow(x, this.get_power()));
		return a;
	}
/**
 * this function Compute a new Monom which is the derivative of this Monom
 * @return the derivative of this Monom
 */
	public Monom derivative() {
		Monom a;
		if (this.get_power()-1 < 0 ) { a= new Monom (0,0); }
		else {
			a = new Monom(this.get_coefficient()*this.get_power(), (this.get_power()-1) );
		}
		return a;
	}
/**
 * this function represents add Monom with other Monom
 * @param m1 = the Monom that is added to this Monom
 */
	public void add(Monom m1) {
		if (this.get_power() == m1.get_power())
		{
			this.set_coefficient(this.get_coefficient() + m1.get_coefficient());
		}
	}
/**
 * this function represents multiply Monom with other Monom
 * @param m1 = the Monom that is multiplied to this Monom
 */
	public void multiply(Monom m1) {
		this.set_coefficient(this.get_coefficient() * m1.get_coefficient());
		this.set_power(this.get_power() + m1.get_power());
	}


	private void set_coefficient (double a) {
		this._coefficient = a;
	}
	private void set_power(int p){
		if (p<0) {
			throw new RuntimeException("Error - power cant be a negative");
		}
		this._power = p;
	}

	public double get_coefficient () { 
		return this._coefficient;
	}
	public int get_power (){
		return this._power;
	}

	private double _coefficient; 
	private int _power; 

	/**
	 * @return this Monom as a String 
	 */
	public String toString() {
		if(this.get_coefficient() == 0) {return ""; }
		if (this.get_power() == 0 ) { return this.get_coefficient() +"";}
		if(this.get_power()==1) { return this.get_coefficient()+ "x";}
		return this.get_coefficient()+"x^" + this.get_power();
	}

     /**
      * this function test if this Monom is logically equals to m1.
      * @param m1 = the Monom that compare to this Monom
      * @return true if this Monom is logically equals to m1, else return false 
      */
	public boolean equals(Monom m1) {
		if (this.get_coefficient() == m1.get_coefficient() && this.get_power() == m1.get_power()) {
			return true;
		}
		return false;
	}
}
