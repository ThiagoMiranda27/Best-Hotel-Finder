package reserva.teste;

import org.junit.Assert;
import org.junit.Test;

import reserva.GerenciadorDasDatas;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;

public class gerenciadorDasDatasTeste {
    GerenciadorDasDatas gerenciaData = new GerenciadorDasDatas();

    @Test
    public void quandoDataForSabadoDeveRetornarTrue() throws ParseException {
        String formato = "23/05/2020";
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formato);

        Assert.assertEquals(true, gerenciaData.fimDeSemana(date));

    }

    @Test
    public void quandoDataForDiaSemanaDeveRetornarFalse() throws ParseException {
        String formato = "19/05/2020";
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formato);

        Assert.assertEquals(false, gerenciaData.fimDeSemana(date));

    }

    @SuppressWarnings("deprecation")
    @Test
    public void deveRetornarAsDatasQueFicaraHospedado() throws ParseException {
        Date dateInicio = new Date(2020, 05, 19);
        Date dateDia = new Date(2020, 05, 20);
        Date dateDia2 = new Date(2020, 05, 21);
        Date dateFim = new Date(2020, 05, 22);

        List<Date> date = new ArrayList<Date>();

        date.add(dateInicio);
        date.add(dateDia);
        date.add(dateDia2);
        date.add(dateFim);

        Assert.assertEquals(date, gerenciaData.pegarPeriodoAlocacao(dateInicio, dateFim));
    }

    @Test
    public void testParaDateFormatoCerto() throws ParseException {

        String invalida = "15/10/2015";
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(invalida);

        Assert.assertEquals(date, gerenciaData.stringParaDate(invalida));

    }

    @Test
    public void testParaDateFormatoErrado() {
        String invalida = "15";
        Assert.assertEquals(null, gerenciaData.stringParaDate(invalida));

    }

}