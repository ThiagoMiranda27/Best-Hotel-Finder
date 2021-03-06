package reserva;

import reserva.TipoDeCliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

public final class InputTeclado {

	public static String validaPerfilCliente(String mensagem) {
		String valor = solicitaDados(mensagem);
		if (valor.equalsIgnoreCase(TipoDeCliente.REGULAR.toString())) {
			return TipoDeCliente.REGULAR.toString();
		} else if (valor.equalsIgnoreCase(TipoDeCliente.VIP.toString())) {
			return TipoDeCliente.VIP.toString();
		} else {
			throw new IllegalArgumentException("O Tipo de Cliente tem que ser Regular ou Vip");
		}
	}

	public static String validaPadraoData(String mensagem) {

		String dataLida = solicitaDados(mensagem);

		try {
			DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
			LocalDate data = LocalDate.parse(dataLida, formatoData);
			return data.format(formatoData).toString();
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Data no formato incorreto");
		}
	}

	public static void comparaDatas(String data, String outraData) {
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			date2 = new SimpleDateFormat("dd/MM/yyyy").parse(outraData);
			if (date1.before(date2) || date1.equals(date2)) {
			} else {
				throw new IllegalArgumentException("Data nao pode ser no dia anterior do inicio da hospedagem");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private static String solicitaDados(String mensagem) {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String dadosInput = null;
		try {
			System.out.print(mensagem);
			dadosInput = bufferRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dadosInput;
	}
}
