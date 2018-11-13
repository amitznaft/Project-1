package myMath;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame {

	private ArrayList<String> ts = new ArrayList<String>();
	public LinePlotTest(Polynom a,double x0,double x1) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 700);

		DataTable data = new DataTable(Double.class, Double.class);
		DataTable data2 = new DataTable(Double.class, Double.class);
		for (double x = x0; x <= x1; x+=0.25) {
			double y = a.f(x);
			if (a.derivative().f(x+0.15) > 0 && a.derivative().f(x-0.15) < 0 ||a.derivative().f(x+0.15) < 0 && a.derivative().f(x-0.15) >0) 
			{
				data2.add(x, y);
				ts.add("("+x+","+y+")       ");
			}
			else {
				data.add(x, y);
			}
		}


		XYPlot plot = new XYPlot(data,data2);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);
		Color color = new Color(0.0f, 0.3f, 1.0f);
		plot.getPointRenderers(data).get(0).setColor(color);
		plot.getLineRenderers(data).get(0).setColor(color);
		Color color2= new Color(1.0f, 0.0f, 0.0f);
		plot.getPointRenderers(data2).get(0).setColor(color2);
		plot.getTitle().setText(""+a);
		plot.getAxisRenderer(XYPlot.AXIS_X).getLabel().setText(ts.toString());
		System.out.println("the approximated area below X-axis above this function is: " +a.area2(x0, x1, 0.01));

	}

	public static void main(String[] args) {
		Polynom a = new Polynom ( "0.2x^4-1.5x^3+3.0x^2-x-5");
		LinePlotTest frame = new LinePlotTest(a,-2.0,6.0);
		frame.setVisible(true);
	}
}
