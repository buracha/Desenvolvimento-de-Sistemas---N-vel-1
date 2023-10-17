package br.com.viagem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.viagem.factory.ConnectionFactory;
import br.com.viagem.model.Destino;

public class DestinoDAO {
	
	private Connection conexao;

	public DestinoDAO() {
		try {
			conexao = ConnectionFactory.conectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void criarDestino(Destino destino) {
		String sql = "INSERT INTO Destino (lugar, tipo, preco) VALUES (?, ?, ?)";

		try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, destino.getLugar());
			stmt.setString(2, destino.getTipo());
			stmt.setInt(3, destino.getPreco());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Destino buscarDestino(int id) {
        Destino Destino = null;
        String sql = "SELECT * FROM Destino WHERE id_destino = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Destino = new Destino();
                Destino.setId_destino(resultado.getInt("id_destino"));
                Destino.setLugar(resultado.getString("lugar"));
                Destino.setTipo(resultado.getString("tipo"));
                Destino.setPreco(resultado.getInt("preco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Destino;
    }
	
	public List<Destino> listarDestinos() {
        List<Destino> Destinos = new ArrayList<>();
        String sql = "SELECT * FROM Destino";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
            	Destino Destino = new Destino();
            	Destino.setId_destino(resultado.getInt("id_destino"));
                Destino.setLugar(resultado.getString("lugar"));
                Destino.setTipo(resultado.getString("tipo"));
                Destino.setPreco(resultado.getInt("preco"));
                Destinos.add(Destino);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Destinos;
    }
	
	public void atualizarDestino(Destino destino) {
        String sql = "UPDATE Destino SET lugar = ?, tipo = ?, preco = ? WHERE id_destino = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, destino.getLugar());
            stmt.setString(2, destino.getTipo());
            stmt.setInt(3, destino.getPreco());
            stmt.setInt(4, destino.getId_destino());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void deletarDestino(int id) {
        String sql = "DELETE FROM Destino WHERE id_destino = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void fecharConexao() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
