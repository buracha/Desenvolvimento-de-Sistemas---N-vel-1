package br.com.viagem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.viagem.factory.ConnectionFactory;
import br.com.viagem.model.Cliente;

public class ClienteDAO {
	private Connection conexao;

	public ClienteDAO() {
		try {
			conexao = ConnectionFactory.conectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void criarCliente(Cliente cliente) {
		String sql = "INSERT INTO Cliente (nome, cpf, telefone, email, senha) VALUES (?, ?, ?, ?, ?)";

		try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNome());
			stmt.setInt(2, cliente.getCpf());
			stmt.setInt(3, cliente.getTelefone());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getSenha());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Cliente buscarCliente(int cpf) {
        Cliente Cliente = null;
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, cpf);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente = new Cliente();
                Cliente.setId_cliente(resultado.getInt("id_cliente"));
                Cliente.setNome(resultado.getString("nome"));
                Cliente.setCpf(resultado.getInt("cpf"));
                Cliente.setTelefone(resultado.getInt("telefone"));
                Cliente.setEmail(resultado.getString("email"));
                Cliente.setSenha(resultado.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Cliente;
    }
	
	public List<Cliente> listarClientes() {
        List<Cliente> Clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente Cliente = new Cliente();
                Cliente.setId_cliente(resultado.getInt("id_cliente"));
                Cliente.setNome(resultado.getString("nome"));
                Cliente.setCpf(resultado.getInt("cpf"));
                Cliente.setTelefone(resultado.getInt("telefone"));
                Cliente.setEmail(resultado.getString("email"));
                Cliente.setSenha(resultado.getString("senha"));
                Clientes.add(Cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Clientes;
    }
	
	public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, cpf = ?, telefone = ?, email = ?, senha = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getCpf());
            stmt.setInt(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getSenha());
            stmt.setInt(6, cliente.getId_cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void deletarCliente(int id) {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public Cliente loginCliente(int cpf, String senha) {
        Cliente Cliente = null;
        String sql = "SELECT * FROM Cliente WHERE cpf = ? AND senha = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, cpf);
            stmt.setString(2, senha);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente = new Cliente();
                Cliente.setId_cliente(resultado.getInt("id_cliente"));
                Cliente.setNome(resultado.getString("nome"));
                Cliente.setCpf(resultado.getInt("cpf"));
                Cliente.setTelefone(resultado.getInt("telefone"));
                Cliente.setEmail(resultado.getString("email"));
                Cliente.setSenha(resultado.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Cliente;
    }
	
	public List<Cliente> listarLoginCliente(int cpf, String senha) {
        List<Cliente> Clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente WHERE cpf = ? AND senha = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        	stmt.setInt(1, cpf);
            stmt.setString(2, senha);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente Cliente = new Cliente();
                Cliente.setId_cliente(resultado.getInt("id_cliente"));
                Cliente.setNome(resultado.getString("nome"));
                Cliente.setCpf(resultado.getInt("cpf"));
                Cliente.setTelefone(resultado.getInt("telefone"));
                Cliente.setEmail(resultado.getString("email"));
                Cliente.setSenha(resultado.getString("senha"));
                Clientes.add(Cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Clientes;
    } 
	
	public List<Cliente> listarLoginPassagem(int cpf) {
        List<Cliente> Clientes = new ArrayList<>();
        String sql = "SELECT * FROM Passagem WHERE cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        	stmt.setInt(1, cpf);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente Cliente = new Cliente();
                Cliente.setId_cliente(resultado.getInt("id_cliente"));
                Cliente.setNome(resultado.getString("nome"));
                Cliente.setCpf(resultado.getInt("cpf"));
                Cliente.setTelefone(resultado.getInt("telefone"));
                Cliente.setEmail(resultado.getString("email"));
                Cliente.setSenha(resultado.getString("senha"));
                Clientes.add(Cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Clientes;
    }
	
	public Cliente buscarPassagem(int cpf) {
        Cliente Cliente = null;
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, cpf);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente = new Cliente();
                Cliente.setId_cliente(resultado.getInt("id_cliente"));
                Cliente.setNome(resultado.getString("nome"));
                Cliente.setCpf(resultado.getInt("cpf"));
                Cliente.setTelefone(resultado.getInt("telefone"));
                Cliente.setEmail(resultado.getString("email"));
                Cliente.setSenha(resultado.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Cliente;
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
