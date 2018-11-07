package myMath;

/**
 * name's : amit znaft id: 205478431 and yuval cohen id :311483432
 *          
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;


import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{


	private ArrayList<Monom> Poly;
	static Comparator<Monom> cmpBypower = new Monom_Comperator();

	public Polynom () {
		this.Poly= new ArrayList<Monom>();
	}
	/**
	 * this constructor gets String of polynom and init new polynom with this values
	 * @param p = input Polynom's String
	 */
	public Polynom (String p) {
		if (p == null ) {
			throw new RuntimeException("Not a Polynom");
		}
		p= p.replaceAll(" ", "");
		if (p == "") { 
			this.Poly= new ArrayList<Monom>();
			return;
		}
		this.Poly= new ArrayList<Monom>();
		p= p.replaceAll("-", "\\+-");
		String [] s = p.split("\\+");
		for (int i=0; i<s.length; i++) {
			if (s[i].length() ==0) { continue ; }
			Monom a = new Monom(s[i]);
			this.add(a);
		}
	}

	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 */
	public Iterator<Monom> iteretor(){
		return this.Poly.iterator();
	}

	/**
	 * this function represents add Monom to this Polynom
	 * @param m1 = the Monom that is added to the Polynom
	 * @param it = the Iterator that pointing on Monom's in this Polynom
	 * @param a = pointing the next element in the iteration
	 * this function use Monom_Comperator to sort the Arraylist of this Polynom
	 */
	
	
	public void add(Monom m1) {
		if (m1.get_coefficient() == 0) { return ; }
		Iterator<Monom> it = this.iteretor();
		if (it.hasNext()) {
			boolean b= true;
			while(it.hasNext()) {
				Monom a = it.next();
				if (a.get_power() == m1.get_power()) {
					a.add(m1);
					if (a.get_coefficient() == 0) {this.Poly.remove(a); }
					b= false;
					break;
				}
			}
			if (b == true) {
				this.Poly.add(new Monom(m1));	
			}
		}
		else {
			this.Poly.add(new Monom(m1));
		}
		this.Poly.sort(cmpBypower);	
	}

	/**
	 * this function represents add Polynom p1 to this Polynom
	 * @param p1 = the Polynom that is added to the Polynom
	 * @param a = pointing the next element in the iteration
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> it2 = p1.iteretor();
		while (it2.hasNext()) {
			Monom a = it2.next();
			this.add(new Monom(a));
		}
	}
	/**
	 *  this function represents substract Polynom p1 from this Polynom
	 * @param p1 = the Polynom that is subtracted from the Polynom
	 * @param a = pointing the next element in the iteration
	 */
	public void substract(Polynom_able p1) {
		Iterator<Monom> it2 = p1.iteretor();
		while (it2.hasNext()) {
			Monom a = it2.next();
			a.multiply(new Monom (-1,0));
		}
		this.add(p1);
	}
	/**
	 *  this function represents multiply Polynom with other Polynom
	 * @param p1 = the Polynom that is multiplied to this Polynom
	 * @param c = new Polynom that is copy of this Polynom, in order to clear this Polynom and added new values to this Polynom
	 * @param d = pointing the next element in the iteration of it1
	 * @param g = new Monom that gets the value of the next element in the iteration of it2 and multiply with d
	 */
	public void multiply(Polynom_able p1) {
		Polynom_able c = this.copy();
		Poly.clear();
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()) {
			Iterator<Monom> it2 = c.iteretor();
			Monom d = it.next();
			while(it2.hasNext()) {
				Monom g = new Monom(it2.next());
				g.multiply(d);
				this.add(new Monom (g));
			}
		}
	}
	/**
	 * this function create a deep copy of this Polynum
	 * @param a = pointing the next element in the iteration of it
	 * @return deep copy of this Polynum
	 */
	public Polynom_able copy() {
		Polynom c = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			Monom a = it.next();
			c.add(new Monom(a));
		}
		return c;
	}

	/**
	 * @return this Polynom as a String
	 */
	public String toString () {
		if (this.Poly.isEmpty()) {return "0"; }
		String str = "";
		Iterator<Monom> it3 = this.iteretor();
		while (it3.hasNext()) {
			Monom a = it3.next();
			if(a.get_coefficient() > 0)
				str += "+" + a.toString();
			else {
				str += a.toString();
			}
		}

		return str ;
	}
	/**
	 * this function represents a simple function of type y=f(x), where both y and x are real numbers.
	 * @param x = the value of the point
	 * @param a = pointing the next element in the iteration of it
	 * @param sum = summed all values in each Monom (iteration)
	 * @return this Polynom value at f(x)
	 */
	public double f(double x) {
		double sum = 0;
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			Monom a = it.next();
			sum  +=  a.f(x);
		}
		return sum;
	}
	/**
	 * this function Compute a new Polynom which is the derivative of this Polynom
	 * @param c = new Polynom to add the derivative of each Monom (iteration)
	 * @return the derivative of this Polynom
	 */
	public Polynom_able derivative() {
		Polynom_able c = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			c.add(new Monom (it.next().derivative()));
		}
		return c;
	}
	/**
	 * this function test if this Polynom is logically equals to p1.
	 * @param p1 = the Polynom that compare to this Polynom
	 * @return true if this Polynom is logically equals to p1, else return false
	 * @param a = pointing the next element in the iteration of it
	 * @param c = pointing the next element in the iteration of it2
	 */
	public boolean equals (Polynom_able p1) {
		boolean b = true;
		Iterator<Monom> it = this.iteretor();
		Iterator<Monom> it2 = p1.iteretor();
		while(it.hasNext() && it2.hasNext()) {
			Monom a = it.next();
			Monom c = it2.next();
			if (a.equals(c)) {
				continue;
			}
			else {
				return false;
			}
		}
		if (it.hasNext() || it2.hasNext()) { return false;}
		return b;
	}
	/**
	 * the function test if this is the Zero Polynom
	 * @return true if this the Zero Polynom, else return false
	 */
	public boolean isZero() {
		return this.Poly.isEmpty();
	}
	/**
	 * this function Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps
	 * @param x0 = starting point
	 * @param x1 = ending point
	 * @param eps = size steps (rectangle width )
	 * @param n = number of rectangle's
	 * @param sum = summed the area of each rectangle
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	public double area(double x0,double x1, double eps){
		if (x1 < x0) {
			double a = x0;
			x0=x1;
			x1=a;
		}
		double n = (x1-x0) / eps;
		double sum =0;
		for (int i=1; i<=n; i++) {
			if (this.f(x0) >0) {
				sum += this.f(x0) * eps;
				x0 += eps;
			}
		}
		return sum;
	}
	/**
	 * this function checks for existing x' (x0<=x'<=x1) which provides a solution to the equation
	 *  assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * *	(i) x0<=x2<=x2 && (ii) f(x2)<eps
	 *  @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return the solution to the equation (m)
	 * @throws Exception if there is not solution to the equation or if the eps value is greater than domain length
	 */
	public double root(double x0, double x1, double eps) {
		if (x0 > x1) {
			double a = x0;
			x0=x1;
			x1=a;
		}
		if (x1-x0 < eps ) { throw new RuntimeException("the eps value is greater than domain length ");
		}
		if (this.f(x0) * this.f(x1) <= 0) {
			double m = (x1-x0)/2;
			m+= x0;
			while ((x1-x0) > eps) {
				if (this.f(m) == 0 ) { return m; }
				if (this.f(m) * this.f(x0)<0) {
					x1 = m;
					m -= (x1-x0) / 2;
				}
				else {
					x0 = m;
					m += (x1-x0) /2;
				}
			}
			return m;
		}
		if (this.f(x0) == 0 ) { return x0; }
		if (this.f(x1) == 0) { return x1; }
		throw new RuntimeException("there is no root this domain ");
	}









}
