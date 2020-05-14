package reserva;

public class Hotel {
	
	private String nome;
	private int classificacao;
	private double precoDiaSemanaRegular;
	private double precoDiaSemanaReward;
	private double precoFimSemanaRegular;
	private double precoFimSemanaReward;
		
	
	public Hotel() {
	}

	public Hotel(String nome, int classificacao, double precoDiaSemanaRegular, double precoDiaSemanaReward,
			double precoFimSemanaRegular, double precoFimSemanaReward) {
		this.nome = nome;
		this.classificacao = classificacao;
		this.precoDiaSemanaRegular = precoDiaSemanaRegular;
		this.precoDiaSemanaReward = precoDiaSemanaReward;
		this.precoFimSemanaRegular = precoFimSemanaRegular;
		this.precoFimSemanaReward = precoFimSemanaReward;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	public double getPrecoDiaSemanaRegular() {
		return precoDiaSemanaRegular;
	}
	public void setPrecoDiaSemanaRegular(double precoDiaSemanaRegular) {
		this.precoDiaSemanaRegular = precoDiaSemanaRegular;
	}
	public double getPrecoDiaSemanaReward() {
		return precoDiaSemanaReward;
	}
	public void setPrecoDiaSemanaReward(double precoDiaSemanaReward) {
		this.precoDiaSemanaReward = precoDiaSemanaReward;
	}
	public double getPrecoFimSemanaRegular() {
		return precoFimSemanaRegular;
	}
	public void setPrecoFimSemanaRegular(double precoFimSemanaRegular) {
		this.precoFimSemanaRegular = precoFimSemanaRegular;
	}
	public double getPrecoFimSemanaReward() {
		return precoFimSemanaReward;
	}
	public void setPrecoFimSemanaReward(double precoFimSemanaReward) {
		this.precoFimSemanaReward = precoFimSemanaReward;
	}
	
}
