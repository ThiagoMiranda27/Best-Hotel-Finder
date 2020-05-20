package reserva;

import reserva.InputTeclado;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String tipoCliente = InputTeclado.validaPerfilCliente("Qual perfil de Cliente voce pertence? (Regular/Vip) ");
        String dataInicio = InputTeclado.validaPadraoData("Quando voce iniciara a hospedagem? (DD/MM/YYYY) ");
        String dataFim = InputTeclado.validaPadraoData("Quando voce finalizara a hospedagem? (DD/MM/YYYY) ");
        InputTeclado.comparaDatas(dataInicio, dataFim);

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