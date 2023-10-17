package br.com.viagem.aplicacao;


import java.util.List;
import java.util.Scanner;

import br.com.viagem.dao.ClienteDAO;
import br.com.viagem.dao.DestinoDAO;
import br.com.viagem.model.Cliente;
import br.com.viagem.model.Destino;


public class Admin {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();
        DestinoDAO destinoDAO = new DestinoDAO();


        while (true) {
            System.out.println("\nMENU DE OPÇÔES:");
            System.out.println("1) Fazer Cadastro do Cliente");
            System.out.println("2) Listar Cliente");
            System.out.println("3) Atualizar Cliente");
            System.out.println("4) Deletar Cliente");
            System.out.println("5) Fazer Cadastro do Destino");
            System.out.println("6) Listar Destinos");
            System.out.println("7) Atualizar Destino");
            System.out.println("8) Deletar Destino");
            System.out.println("9) Sair");
            System.out.println("");
            System.out.print("Escolha uma opção: ");
            System.out.println("");
            int opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    // Cadastrar Cliente
                    Cliente cliente = new Cliente();
                    System.out.print("Nome do Cliente: ");
                    scanner.nextLine(); // Limpar o buffer do teclado
                    cliente.setNome(scanner.nextLine());
                    System.out.print("CPF: ");
                    cliente.setCpf(scanner.nextInt());
                    System.out.print("Telefone: ");
                    cliente.setTelefone(scanner.nextInt());
                    System.out.print("Email: ");
                    scanner.nextLine();
                    cliente.setEmail(scanner.nextLine());
                    System.out.print("Senha: ");
                    cliente.setSenha(scanner.nextLine());
                    clienteDAO.criarCliente(cliente); 
                    System.out.println("Cliente cadastrado com sucesso!");           
                    break;
                    
                case 2:
                    // Listar Clientes
                    List<Cliente> clientes = clienteDAO.listarClientes();
                    System.out.println("Lista de Clientes:");
                    for (Cliente m : clientes) {
                        System.out.println("ID: (" + m.getId_cliente() +
                                ") Nome: (" + m.getNome() +
                                ") Cpf: (" + m.getCpf() +
                                ") Telefone: (" + m.getTelefone() +
                                ") Email: (" + m.getEmail() +
                                ") Senha: (" + m.getSenha() + ")");
                    }
                    break;
                    
                case 3:
                    // Atualizar Cliente
                    System.out.print("CPF do Cliente: ");
                    int clienteCpfAtualizar = scanner.nextInt();
                    Cliente clienteAtualizar = clienteDAO.buscarCliente(clienteCpfAtualizar);
                    if (clienteAtualizar != null) {                  
                        	System.out.print("Novo Nome do Cliente: ");
                            scanner.nextLine(); // Limpar o buffer do teclado
                            clienteAtualizar.setNome(scanner.nextLine());
                            System.out.print("Novo CPF do Cliente: ");
                            clienteAtualizar.setCpf(scanner.nextInt());
                            System.out.print("Novo Telefone do Cliente: ");
                            clienteAtualizar.setTelefone(scanner.nextInt());
                            System.out.print("Novo Email do Cliente: ");
                            scanner.nextLine(); // Limpar o buffer do teclado
                            clienteAtualizar.setEmail(scanner.nextLine());
                            System.out.print("Nova Senha do Cliente: ");
                            clienteAtualizar.setSenha(scanner.nextLine());
                            clienteDAO.atualizarCliente(clienteAtualizar);
                            System.out.println("Cliente atualizado com sucesso!");
                       
                    } else {
                        System.out.println("Cliente não encontrada.");
                    }
                    break;
                    
                case 4:
                    // Deletar Cliente
                    System.out.print("ID do Cliente para exclusão: ");
                    int clienteIdDeletar = scanner.nextInt();
                    Cliente clienteDeletar = clienteDAO.buscarCliente(clienteIdDeletar);
                    if (clienteDeletar != null) {
                        clienteDAO.deletarCliente(clienteIdDeletar);
                        System.out.println("Cliente excluído com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                
                case 5:
                    // Cadastrar Destino
                    Destino destino = new Destino();
                    System.out.print("Lugar: ");
                    scanner.nextLine(); // Limpar o buffer do teclado
                    destino.setLugar(scanner.nextLine());
                    System.out.print("Tipo (Internacional ou Nacional): ");
                    destino.setTipo(scanner.nextLine());
                    System.out.print("Preço: ");
                    destino.setPreco(scanner.nextInt());
                    destinoDAO.criarDestino(destino); 
                    System.out.println("Destino cadastrado com sucesso!");           
                    break;
                    
                case 6:
                    // Listar Destino
                    List<Destino> destinos = destinoDAO.listarDestinos();
                    System.out.println("Lista de Destinos:");
                    for (Destino m : destinos) {
                        System.out.println("ID: (" + m.getId_destino() +
                                ") Lugar: (" + m.getLugar() +
                                ") Preço: (" + m.getPreco() + ")");
                    }
                    break;
                    
                case 7:
                    // Atualizar Destino
                    System.out.print("ID do Destino: ");
                    int destinoIdAtualizar = scanner.nextInt();
                    Destino destinoAtualizar = destinoDAO.buscarDestino(destinoIdAtualizar);
                    if (destinoAtualizar != null) {                  
                        	System.out.print("Novo Lugar: ");
                            scanner.nextLine(); // Limpar o buffer do teclado
                            destinoAtualizar.setLugar(scanner.nextLine());
                            System.out.print("Novo tipo(Internacional ou Nacional): ");
                            destinoAtualizar.setTipo(scanner.nextLine());
                            System.out.print("Novo Preco: ");
                            destinoAtualizar.setPreco(scanner.nextInt());
                            destinoDAO.atualizarDestino(destinoAtualizar);
                            System.out.println("Destino atualizado com sucesso!");
                       
                    } else {
                        System.out.println("Destino não encontrada.");
                    }
                    break;
                    
                case 8:
                    // Deletar Destino
                    System.out.print("ID do Destino para exclusão: ");
                    int destinoIdDeletar = scanner.nextInt();
                    Destino destinoDeletar = destinoDAO.buscarDestino(destinoIdDeletar);
                    if (destinoDeletar != null) {
                        destinoDAO.deletarDestino(destinoIdDeletar);
                        System.out.println("Destino excluído com sucesso!");
                    } else {
                        System.out.println("Destino não encontrado.");
                    }
                    break;
                    
                case 9:
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
