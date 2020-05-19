package reserva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class InputTeclado {

	public String input(String entrada, int tipoInput) throws ParseException {

		System.out.print(entrada + " ");
		
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
						
			if (tipoInput == 0) {
				String valor = bufferRead.readLine();
				if (valor.equalsIgnoreCase("regular")) {
					return TipoDeCliente.REGULAR.toString();
				} else if (valor.equalsIgnoreCase("vip")) {
					return TipoDeCliente.VIP.toString();
				}
				else
				{
					throw new IllegalArgumentException("O Tipo de Cliente tem que ser Regular ou Vip");
					
				}
			}
			if (tipoInput == 1) {
				String dataLida = bufferRead.readLine();
				if (dataLida.matches("^([0-2][0-9]|(3)[0-1])(/)(((0)[0-9])|((1)[0-2]))(/)\\d{4}$")) {
					return dataLida;
				}
				else
				{
					throw new IllegalArgumentException("Data no formato incorreto");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}