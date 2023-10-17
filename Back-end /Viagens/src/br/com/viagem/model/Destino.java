package br.com.viagem.model;

public class Destino {
	private int id_destino;
	private String lugar;
	private String tipo;
	private int preco;
	
	public Destino(int id_destino, String lugar, String tipo, int preco) {
		super();		
		this.lugar = lugar;
		this.tipo = tipo;
		this.preco = preco;
	}
	
	public Destino () {
		
	}

	public int getId_destino() {
		return id_destino;
	}

	public void setId_destino(int id_destino) {
		this.id_destino = id_destino;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	
	
	
}
