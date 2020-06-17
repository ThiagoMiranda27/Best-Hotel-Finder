package com.example.besthotelfinder.gerenciador;

public enum TipoDeCliente {

	REGULAR ("Regular"), VIP ("Vip");

	private final String content;
	TipoDeCliente(String valor) {
		content = valor;
	}

	public String getTipoCliente() {
		return content;
	}
}