package myMath;

/**
 * name's : amit znaft id: 205478431 and yuval cohen id :311483432
 *          
 */


import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	/**
	 * this function comapre between two Monom's by their power
	 * @return 0 if they have the same power
	          -1 if the power of Monom a bigger then b
	           1 if the power of Monom b bigger then a
	 *
	 */
	public int compare(Monom a , Monom b){
		if (a.get_power() == b.get_power()) {
			return 0;
		}
		else if (a.get_power()> b.get_power()) {
			return -1;
		}
		else {
			return 1;
		}
	}
	

}
