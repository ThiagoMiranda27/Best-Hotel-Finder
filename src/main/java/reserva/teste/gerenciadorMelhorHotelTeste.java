package reserva.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import reserva.GerenciadorMelhorHotel;
import reserva.HoteisExistentes;
import reserva.Hotel;
import reserva.Taxa;
import reserva.TipoDeCliente;

public class gerenciadorMelhorHotelTeste {
	GerenciadorMelhorHotel Geren = new GerenciadorMelhorHotel();

	@Test
	public void testeMelhorTarifaPlaza() throws ParseException {
		List<Date> date = new ArrayList<Date>();

		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("18/05/2020"));
		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("19/05/2020"));
		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2020"));

		TipoDeCliente cliente = TipoDeCliente.VIP;

		HoteisExistentes hoteisE = new HoteisExistentes();
		ArrayList<Hotel> hotelT = hoteisE.hoteis();
		Hotel Plaza = hotelT.get(0);

		Hotel A = GerenciadorMelhorHotel.calcularTaxaDoHotel(cliente, date, Plaza).getHotel();

		Assert.assertEquals(A, Geren.pegarMelhorTaxa(cliente, date, hotelT).getHotel());

	}

	@Test
	public void testeMelhorTarifaHilton() throws ParseException {
		List<Date> date = new ArrayList<Date>();

		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("23/05/2020"));
		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("24/05/2020"));

		TipoDeCliente cliente = TipoDeCliente.REGULAR;

		HoteisExistentes hoteisE = new HoteisExistentes();
		ArrayList<Hotel> hotelT = hoteisE.hoteis();
		Hotel Hilton = hotelT.get(1);

		Hotel A = GerenciadorMelhorHotel.calcularTaxaDoHotel(cliente, date, Hilton).getHotel();

		Assert.assertEquals(A, Geren.pegarMelhorTaxa(cliente, date, hotelT).getHotel());

	}

	@Test
	public void testeMelhorTarifaContinental() throws ParseException {
		List<Date> date = new ArrayList<Date>();

		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("23/05/2020"));
		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("24/05/2020"));

		TipoDeCliente cliente = TipoDeCliente.VIP;

		HoteisExistentes hoteisE = new HoteisExistentes();
		ArrayList<Hotel> hotelT = hoteisE.hoteis();
		Hotel Continental = hotelT.get(2);

		Hotel A = GerenciadorMelhorHotel.calcularTaxaDoHotel(cliente, date, Continental).getHotel();

		Assert.assertEquals(A, Geren.pegarMelhorTaxa(cliente, date, hotelT).getHotel());

	}

	@Test
	public void testeMenorTaxa() {
		Hotel hotel = new Hotel();
		List<Taxa> listaDeTaxas = new ArrayList<Taxa>();

		listaDeTaxas.add(new Taxa(hotel, 10.0));
		listaDeTaxas.add(new Taxa(hotel, 20.0));
		listaDeTaxas.add(new Taxa(hotel, 30.0));

		Double metodoMenorTaxa = GerenciadorMelhorHotel.calcularMenorTaxa(listaDeTaxas).getPreco();
		Double menorValor = listaDeTaxas.get(0).getPreco();

		Assert.assertEquals(menorValor, metodoMenorTaxa);

	}

	@Test
	public void testeCalcularTaxaDoHotelPlaza180Reais() throws ParseException {
		List<Date> date = new ArrayList<Date>();

		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("23/05/2020"));
		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("24/05/2020"));

		TipoDeCliente cliente = TipoDeCliente.REGULAR;

		HoteisExistentes hoteisE = new HoteisExistentes();
		ArrayList<Hotel> hotelT = hoteisE.hoteis();
		Hotel Plaza = hotelT.get(0);

		Double preco = Geren.calcularTaxaDoHotel(cliente, date, Plaza).getPreco();
		Double preco2 = 180.0;
		Assert.assertEquals(preco2, preco);

	}

	@Test
	public void testeCalcularTaxaDoHotelHilton120Reais() throws ParseException {
		List<Date> date = new ArrayList<Date>();

		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("23/05/2020"));
		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("24/05/2020"));

		TipoDeCliente cliente = TipoDeCliente.REGULAR;

		HoteisExistentes hoteisE = new HoteisExistentes();
		ArrayList<Hotel> hotelT = hoteisE.hoteis();
		Hotel Hilton = hotelT.get(1);

		Double preco = Geren.calcularTaxaDoHotel(cliente, date, Hilton).getPreco();
		Double preco2 = 120.0;
		Assert.assertEquals(preco2, preco);

	}

	@Test
	public void testeCalcularTaxaDoHotelContinental300Reais() throws ParseException {
		List<Date> date = new ArrayList<Date>();

		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("23/05/2020"));
		date.add(new SimpleDateFormat("dd/MM/yyyy").parse("24/05/2020"));

		TipoDeCliente cliente = TipoDeCliente.REGULAR;

		HoteisExistentes hoteisE = new HoteisExistentes();
		ArrayList<Hotel> hotelT = hoteisE.hoteis();
		Hotel Continental = hotelT.get(2);

		Double preco = Geren.calcularTaxaDoHotel(cliente, date, Continental).getPreco();
		Double preco2 = 300.0;
		Assert.assertEquals(preco2, preco);

	}
}
