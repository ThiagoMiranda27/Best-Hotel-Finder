package reserva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputTeclado {

    private final String CLIENTE_INVALIDO = "Tipo de cliente invalido";
    private final String DATA_INVALIDA = "Data invalida";
    private final String DATA_CONFLITO = "Data nao pode ser no dia anterior do inicio da hospedagem";
    GerenciadorDasDatas g = new GerenciadorDasDatas();


    public InputTeclado() {
    }

    public String validaPerfilCliente(String mensagem) {
        String tipo = solicitaDados(mensagem);

        if (tipo.equalsIgnoreCase(TipoDeCliente.REGULAR.getTipoCliente())) {
            return TipoDeCliente.REGULAR.name();

        } else if (tipo.equalsIgnoreCase(TipoDeCliente.REWARD.getTipoCliente())) {
            return TipoDeCliente.REWARD.name();

        } else {
            System.out.println(CLIENTE_INVALIDO);
        }
        return null;
    }


    public String validaPadraoData(String mensagem) {
        String date = solicitaDados(mensagem);

        try {
            SimpleDateFormat d = new SimpleDateFormat(g.datePattern);
            d.setLenient(false);
            d.parse(String.valueOf(date));
            return date;

        } catch (Exception e) {
            System.out.println(DATA_INVALIDA);
        }
        return null;

    }

    public void comparaDatas(String data, String outraData) {
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat(g.datePattern).parse(data);
            date2 = new SimpleDateFormat(g.datePattern).parse(outraData);
            if (date1.before(date2)) {
            } else {
                throw new IllegalArgumentException(DATA_CONFLITO);
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