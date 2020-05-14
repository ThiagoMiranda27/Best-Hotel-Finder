package reserva;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

    public class InputTeclado {

        private final String ERROTIPOCLIENTE = "Tipo de cliente invalido";

        public InputTeclado(){}

        public void input() {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Qual perfil de Cliente voce pertence? (Regular/Vip)");
            String tipoCliente = scanner.next();
            if( !tipoCliente.equalsIgnoreCase("Regular") ||
                !tipoCliente.equalsIgnoreCase("Vip") ) {
                System.out.println(ERROTIPOCLIENTE);
            }

            System.out.println("Quando voce iniciara a hospedagem? (DD/MM/YYYY)");
            String dataInicio = scanner.next();

            System.out.println("Quando voce finalizara a hospedagem? (DD/MM/YYYY)");
            String dataFim = scanner.next();

            GerenciadorDasDatas gerenciaDatasEscolhidas = new GerenciadorDasDatas();

            Date datainicio = gerenciaDatasEscolhidas.stringParaDate(dataInicio);
            Date dataFimHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataFim);

            System.out.println("Voce ficara " + gerenciaDatasEscolhidas.pegarPeriodoAlocacao(datainicio, dataFimHospedagem).size() + " dias.");


        }
    }
