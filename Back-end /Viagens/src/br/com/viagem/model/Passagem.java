package br.com.viagem.model;

import java.util.Date;

public class Passagem {
	private int id_passagem;
	private Cliente cliente;
	private Destino destino;
	private Date dataPassagem;

	public Passagem(Cliente cliente, Destino destino, Date dataPassagem) {
		super();	
		this.cliente = cliente;
		this.destino = destino;
		this.dataPassagem = dataPassagem;
	}
	
	public Passagem() {
		
	}

	public int getId_passagem() {
		return id_passagem;
	}

	public void setId_passagem(int id_passagem) {
		this.id_passagem = id_passagem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Date getDataPassagem() {
		return dataPassagem;
	}

	public void setDataPassagem(Date dataPassagem) {
		this.dataPassagem = dataPassagem;
	}
	
	
	
}
