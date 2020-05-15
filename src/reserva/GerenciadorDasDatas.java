package reserva;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class GerenciadorDasDatas {
	
	
	public Date stringParaDate(String formato){

		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formato);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean fimDeSemana (Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		if(diaDaSemana == Calendar.SATURDAY || diaDaSemana == Calendar.SUNDAY) {
			return true;
		}
		
		return false;
	}

	public List<Date> pegarPeriodoAlocacao (Date dataInicio, Date dataFim){
		List<Date> datas = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dataInicio);
		
		while(calendar.getTime().before(dataFim) || calendar.getTime().equals(dataFim)) {
			Date resultado = calendar.getTime();
			datas.add(resultado);
			calendar.add(Calendar.DATE, 1);
		}
		
		return datas;
	}
	
}
