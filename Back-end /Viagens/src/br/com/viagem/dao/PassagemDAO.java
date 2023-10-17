package br.com.viagem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.viagem.factory.ConnectionFactory;
import br.com.viagem.model.Passagem;

public class PassagemDAO {
	private Connection conexao;
    ClienteDAO clienteDAO = new ClienteDAO();
    DestinoDAO destinoDAO = new DestinoDAO();

    public PassagemDAO() {
    	try {
			conexao = ConnectionFactory.conectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void criarPassagem(Passagem passagem) {
        String sql = "INSERT INTO Passagem (cpf_cliente, destino_id, data_passagem) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, passagem.getCliente().getCpf());
            stmt.setInt(2, passagem.getDestino().getId_destino());
            stmt.setTimestamp(3, new java.sql.Timestamp(passagem.getDataPassagem().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Passagem> listarLoginPassagem(int cpf) {
        List<Passagem> Passagens = new ArrayList<>();
        String sql = "SELECT * FROM Passagem WHERE cpf_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        	stmt.setInt(1, cpf);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Passagem Passagem = new Passagem();
                Passagem.setId_passagem(resultado.getInt("id_passagem"));
                Passagem.setCliente(clienteDAO.buscarCliente(resultado.getInt("cpf_cliente")));
                Passagem.setDestino(destinoDAO.buscarDestino(resultado.getInt("destino_id")));
                Passagem.setDataPassagem(resultado.getTimestamp("data_passagem"));
                Passagens.add(Passagem);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Passagens;
    }

}
