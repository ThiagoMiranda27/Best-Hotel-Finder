package reserva;

public class Taxa {
	Hotel hotel;
	Double preco;

	public Taxa(Hotel hotel, Double preco) {
		this.hotel = hotel;
		this.preco = preco;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return this.getHotel().getNome() + ", preco: " + this.getPreco() + "R$";
	}
	
}
