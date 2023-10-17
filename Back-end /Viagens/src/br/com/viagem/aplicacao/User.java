package br.com.viagem.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.viagem.dao.ClienteDAO;
import br.com.viagem.dao.DestinoDAO;
import br.com.viagem.dao.PassagemDAO;
import br.com.viagem.model.Cliente;
import br.com.viagem.model.Destino;
import br.com.viagem.model.Passagem;

public class User {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();
        DestinoDAO destinoDAO = new DestinoDAO();
        PassagemDAO passagemDAO = new PassagemDAO();


        while (true) { 
            System.out.println("\nMENU DE OPÇÔES:");
            System.out.println("1) Fazer Login");
            System.out.println("2) Sair");
            System.out.print("Escolha uma opção: "); 

            int opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                	System.out.print("Digite seu CPF: ");
                    int clienteCpf = scanner.nextInt();
                    System.out.print("Digite sua Senha: ");
                    scanner.nextLine();
                    String clienteSenha = scanner.nextLine();
                    Cliente clienteLogin = clienteDAO.loginCliente(clienteCpf,clienteSenha);
                    while (true) {
                    if (clienteLogin != null) {                  
                    	List<Cliente> clientes = clienteDAO.listarLoginCliente(clienteCpf,clienteSenha);
                    	System.out.println("");
                    	System.out.println("Cliente:");
                        for (Cliente m : clientes) {
                            System.out.println("ID: (" + m.getId_cliente() + ")");
                            System.out.println("Nome: (" + m.getNome() +")");
                            System.out.println("Cpf: (" + m.getCpf() +")");
                            System.out.println("Telefone: (" + m.getTelefone() +")");
                            System.out.println("Email: (" + m.getEmail() +")");
                            System.out.println("Senha: (" + m.getSenha() + ")");
                        }
                        
                        System.out.println("");
                        System.out.println("Destinos:");
                        List<Destino> destinos = destinoDAO.listarDestinos();
                        System.out.println("Lista de Destinos:");
                        for (Destino m : destinos) {
                            System.out.println("ID: (" + m.getId_destino() +
                                    ") Lugar: (" + m.getLugar() +
                                    ") Preço: (" + m.getPreco() + ")");
                        }
                        List<Passagem> Passagens = passagemDAO.listarLoginPassagem(clienteCpf);
                        System.out.println("");
                        System.out.println("Passagens:");
                        for (Passagem m : Passagens) {
                            System.out.println("ID: (" + m.getId_passagem() +
                                    ") Nome: (" + m.getCliente().getNome() +
                                    ") Destino: (" + m.getDestino().getLugar() +
                                    ") Destino: (" + m.getDestino().getPreco() + 
                                    ") Destino: (" + m.getDestino().getTipo() +
                                    ") Data: (" + m.getDataPassagem() + ")");
                        }
                        
                        System.out.println("\nMENU DE OPÇÔES:");
                        System.out.println("1) Cadastra Passagem");
                        System.out.println("2) Sair");
                        System.out.print("Escolha uma opção: ");
                        
                        int opcao1 = scanner.nextInt();
                        
                        switch (opcao1) {
                            case 1: 
		                     // Cadastrar Passagem
		                        Passagem passagem = new Passagem();
		                        System.out.print("CPF do Cliente: ");
		                        int clienteId = scanner.nextInt();
		                        Cliente clientePassagem = clienteDAO.buscarPassagem(clienteId);
		                        if (clientePassagem != null) {
		                            passagem.setCliente(clientePassagem);
		                            System.out.print("ID do Destino: ");
		                            int destinoId = scanner.nextInt();
		                            Destino destinoPassagem = destinoDAO.buscarDestino(destinoId);
		                            if (destinoPassagem != null) {
		                                passagem.setDestino(destinoPassagem);
		                                System.out.print("Data da Consulta (dd/mm/yyyy): ");
		                                String dataString = scanner.next();
		                                try {
		                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		                                    Date dataPassagem = sdf.parse(dataString);
		                                    passagem.setDataPassagem(dataPassagem);
		                                    passagemDAO.criarPassagem(passagem);
		                                    System.out.println("Passagem cadastrada com sucesso!");
		                                } catch (ParseException e) {
		                                    System.out.println("Formato de data inválido. Use dd/mm/yyyy.");
		                                }
		                            } else {
		                                System.out.println("Destino não encontrado.");
		                            }
		                        } else {
		                            System.out.println("Cliente não encontrado.");
		                        }
		                        
		                        System.out.println("");
		                        System.out.println("Destinos:");
		                        List<Destino> destinos1 = destinoDAO.listarDestinos();
		                        System.out.println("Lista de Destinos:");
		                        for (Destino m : destinos1) {
		                            System.out.println("ID: (" + m.getId_destino() +
		                                    ") Lugar: (" + m.getLugar() +
		                                    ") Preço: (" + m.getPreco() + ")");
		                        }
		                        List<Passagem> Passagens1 = passagemDAO.listarLoginPassagem(clienteCpf);
		                        System.out.println("");
		                        System.out.println("Passagens:");
		                        for (Passagem m : Passagens1) {
		                            System.out.println("ID: (" + m.getId_passagem() +
		                                    ") Nome: (" + m.getCliente().getNome() +
		                                    ") Destino: (" + m.getDestino().getLugar() +
		                                    ") Destino: (" + m.getDestino().getPreco() + 
		                                    ") Destino: (" + m.getDestino().getTipo() +
		                                    ") Data: (" + m.getDataPassagem() + ")");
		                        }
                        }
                        break;
                    } else {
                        System.out.println("Cliente não encontrada.");
                    }
                    
                    }
                    break;
                
                case 2:
                    // Sair
                    System.out.println("Saindo do sistema...");
                    clienteDAO.fecharConexao();
                    //destinoDAO.fecharConexao();
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        }
        

	}

}
