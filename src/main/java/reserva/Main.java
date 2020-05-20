package reserva;

import org.graalvm.compiler.hotspot.phases.profiling.FinalizeProfileNodesPhase;
import reserva.InputTeclado;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InputTeclado inputTeclado = new InputTeclado();

        String tipoCliente = inputTeclado.validaPerfilCliente("Qual perfil de Cliente voce pertence? (Regular/Vip) ");
        String dataInicio = inputTeclado.validaPadraoData("Quando voce iniciara a hospedagem? (DD/MM/YYYY) ");
        String dataFim = inputTeclado.validaPadraoData("Quando voce finalizara a hospedagem? (DD/MM/YYYY) ");
        inputTeclado.comparaDatas(dataInicio, dataFim);

        TipoDeCliente tipoDeCliente = TipoDeCliente.valueOf(tipoCliente);

        GerenciadorDasDatas gerenciaDatasEscolhidas = new GerenciadorDasDatas();
        Date dataInicioHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataInicio);
        Date dataFimHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataFim);
        List<Date> periodo = gerenciaDatasEscolhidas.pegarPeriodoAlocacao(dataInicioHospedagem, dataFimHospedagem);

        HoteisExistentes hoteisExistentes = new HoteisExistentes();

        GerenciadorMelhorHotel gerenciadorMelhorHotel = new GerenciadorMelhorHotel();
        Taxa melhorTaxa = gerenciadorMelhorHotel.pegarMelhorTaxa(tipoDeCliente, periodo, hoteisExistentes.hoteis());
        System.out.println("O Hotel mais barato encontrado foi: " + melhorTaxa.getHotel());
        System.out.println("O seu preco ficou em: " + melhorTaxa.getPreco() + "R$");

    }

}