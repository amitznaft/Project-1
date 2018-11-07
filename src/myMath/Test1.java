package myMath;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Test1 {
	
	Polynom_able a = new Polynom();
	Polynom_able w = new Polynom();
	Polynom_able b = new Polynom();
	
	@Before
	public void setup() {
		 a = new Polynom ("x^2+x");
		 w = new Polynom ("X^2-8X+15");
	
		 b.add(new Monom(3,0));
		 b.add(new Monom(-1,0));
		 b.add(new Monom(-2,0));
		 
	}
	
	@Test
	public void addPolynom () {
		Polynom_able b = new Polynom ("3X-2");
		a.add(b);
		Polynom_able e = new Polynom ("x^2+4X-2");
		Assert.assertTrue(a.equals(e));
	}
	
	@Test
	public void area() {
		double e = 23.0/6;
		assertEquals(e , a.area(1, 2, 0.01) , 0.05);
	}
	
	@Test
	public void mul () {
		Polynom t = new Polynom ("x^2-x");
		a.multiply(t);
		Polynom e = new Polynom ("x^4-x^2");
		Assert.assertTrue(a.equals(e));
	}
	
	@Test
	public void fTest () {
		double x = a.f(1);
		Assert.assertTrue(2.0 == x);
	}
	
	@Test
	public void root () {
		double e = 3.0;
		assertEquals(e , w.root(0, 3, 0.01) , 0.01);
	}
	
	@Test
	public void d () {
		Polynom d = new Polynom ("2X-8");
		Assert.assertTrue(d.equals(w.derivative()));
	}
	
	@Test
	public void isZero() {
		Assert.assertFalse(a.isZero());
		Assert.assertTrue(b.isZero());
	}
	
	@Test
	public void substract() {
		a.substract(w);
		Polynom b = new Polynom("9x-15");
		Assert.assertTrue(a.equals(b));
	}
	
	@Test
	public void copy() {
		Polynom_able b = a.copy();
		Assert.assertTrue(a.equals(b));
	}
	
	
	
	
}
