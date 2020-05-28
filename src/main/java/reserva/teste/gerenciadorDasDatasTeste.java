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
    public void quandoDataForFimDeSemanaDeveRetornarTrue() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("23/05/2020");

        Assert.assertEquals(true, gerenciaData.fimDeSemana(date));

    }

    @Test
    public void quandoDataForDiaSemanaDeveRetornarFalse() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("19/05/2020");

        Assert.assertEquals(false, gerenciaData.fimDeSemana(date));

    }


    @Test
    public void deveRetornarAsDatasQueFicaraHospedado() throws ParseException {
        Date dateInicio = new SimpleDateFormat("dd/MM/yyyy").parse("19/05/2020");
        Date dateFim = new SimpleDateFormat("dd/MM/yyyy").parse("22/05/2020");

        List<Date> date = new ArrayList<Date>();

        date.add(dateInicio);
        date.add(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2020"));
        date.add(new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2020"));
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