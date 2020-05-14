package reserva;

import java.util.ArrayList;

public class HoteisExistentes {
	
	public ArrayList<Hotel> hoteis(){
		
		ArrayList<Hotel> hoteis = new ArrayList<Hotel>();
		hoteis.add(new Hotel("Plaza", 3, 110d, 80d, 90d, 80d));
		hoteis.add(new Hotel("Hilton", 4, 160d, 110d, 60d, 50d));
		hoteis.add(new Hotel("Continental", 5, 220d, 100d, 150d, 40d));

		return hoteis;
	}
	
}
