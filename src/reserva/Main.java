package reserva;

import java.util.Date;
import java.util.List;
public class Main {

	public static void main(String[] args) {
		
		String tipoCliente = "";
		String dataInicio = "";
		String dataFim = "";
		
		InputTeclado inputTeclado = new InputTeclado();

		try {
			tipoCliente = inputTeclado.input("Qual perfil de Cliente voce pertence? (Regular/Vip)", 0);
			dataInicio = inputTeclado.input("Quando voce iniciara a hospedagem? (DD/MM/YYYY)", 1);
			dataFim = inputTeclado.input("Quando voce finalizara a hospedagem? (DD/MM/YYYY)", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TipoDeCliente tipoDeCliente = TipoDeCliente.valueOf(tipoCliente);
		
		GerenciadorDasDatas gerenciaDatasEscolhidas = new GerenciadorDasDatas();
		Date dataInicioHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataInicio);
		Date dataFimHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataFim);
		List<Date> periodo = gerenciaDatasEscolhidas.pegarPeriodoAlocacao(dataInicioHospedagem, dataFimHospedagem);

		HoteisExistentes hoteisExistentes = new HoteisExistentes();
	
	}

}
