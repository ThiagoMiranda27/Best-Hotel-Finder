package reserva;

import java.util.Date;
import java.util.List;
public class Main {

	public static void main(String[] args) {
		
		InputTeclado inputTeclado = new InputTeclado();
		
		String tipoCliente = inputTeclado.input("Qual perfil de Cliente voce pertence? (Regular/Vip)");
		String dataInicio = inputTeclado.input("Quando voce iniciara a hospedagem? (DD/MM/YYYY)");
		String dataFim = inputTeclado.input("Quando voce finalizara a hospedagem? (DD/MM/YYYY)");

		TipoDeCliente tipoDeCliente = TipoDeCliente.valueOf(tipoCliente);
		
		GerenciadorDasDatas gerenciaDatasEscolhidas = new GerenciadorDasDatas();
		Date dataInicioHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataInicio);
		Date dataFimHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataFim);
		List<Date> periodo = gerenciaDatasEscolhidas.pegarPeriodoAlocacao(dataInicioHospedagem, dataFimHospedagem);

		HoteisExistentes hoteisExistentes = new HoteisExistentes();
	
	}

}
