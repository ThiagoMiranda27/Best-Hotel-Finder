package reserva;

import static reserva.TipoDeCliente.REGULAR;
import static reserva.TipoDeCliente.VIP;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorMelhorHotel {

	public Taxa pegarMelhorTaxa(TipoDeCliente tipoDeCliente, List<Date> periodo, List<Hotel> hoteis) {

		List<Taxa> listaDeTaxas = new ArrayList<Taxa>();

		for (Hotel hotel : hoteis) {
			Taxa taxa = calcularTaxaDoHotel(tipoDeCliente, periodo, hotel);
			listaDeTaxas.add(taxa);
		}

		Taxa menorTaxa = calcularMenorTaxa(listaDeTaxas);

		return menorTaxa;
	}
	
	private static Taxa calcularMenorTaxa(List<Taxa> listaDeTaxas) {

		Taxa menorTaxa = null;

		for (Taxa taxa : listaDeTaxas) {
			if (menorTaxa == null || taxa.getPreco() < menorTaxa.getPreco()) {
				menorTaxa = taxa;
			} else if (taxa.getPreco().equals(menorTaxa.getPreco()) && taxa.getHotel().getClassificacao() > menorTaxa.getHotel().getClassificacao()) {
				menorTaxa = taxa;
			}
		}

		return menorTaxa;
	}

	private static Taxa calcularTaxaDoHotel(TipoDeCliente tipoDeCliente, List<Date> periodo, Hotel hotel) {

		GerenciadorDasDatas gerenciadorDasDatas = new GerenciadorDasDatas();
		Double preco = 0d;
		for (Date data : periodo) {
			if (tipoDeCliente == REGULAR) {
				if (gerenciadorDasDatas.fimDeSemana(data)) {
					preco += hotel.getPrecoFimSemanaRegular();
				} else {
					preco += hotel.getPrecoDiaSemanaRegular();
				}
			} else if (tipoDeCliente == VIP) {
				if (gerenciadorDasDatas.fimDeSemana(data)) {
					preco += hotel.getPrecoFimSemanaReward();
				} else {
					preco += hotel.getPrecoDiaSemanaReward();
				}
			}
		}

		Taxa taxa = new Taxa(hotel, preco);

		return taxa;
	}
	
}
