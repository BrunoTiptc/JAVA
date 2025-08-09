package CRUD;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AtividadeMapaI {

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));

        try {
            System.setOut(new PrintStream(
                new FileOutputStream(FileDescriptor.out), true,
                StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Aviso: não foi possível forçar UTF-8 na saída.");
        }

        Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        List<Cliente> clientes = new ArrayList<>();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Criar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Atualizar cliente");
            System.out.println("4 - Excluir cliente");
            System.out.println("5 - Buscar cliente por ID");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1 -> {
                    System.out.print("Digite o ID do cliente: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o telefone do cliente: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Digite o e-mail do cliente: ");
                    String email = scanner.nextLine();

                    clientes.add(new Cliente(id, nome, telefone, email));
                    System.out.println("Cliente adicionado com sucesso!");
                }

                case 2 -> {
                    System.out.println("Lista de clientes:");
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (Cliente cliente : clientes) {
                            System.out.println("ID: " + cliente.getId());
                            System.out.println("Nome: " + cliente.getNome());
                            System.out.println("Telefone: " + cliente.getTelefone());
                            System.out.println("E-mail: " + cliente.getEmail());
                            System.out.println("------------------------");
                        }
                    }
                }

                case 3 -> {
                    System.out.print("Digite o ID do cliente a ser atualizado: ");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();

                    Cliente clienteEncontrado = null;
                    for (Cliente cliente : clientes) {
                        if (cliente.getId() == idCliente) {
                            clienteEncontrado = cliente;
                            break;
                        }
                    }

                    if (clienteEncontrado != null) {
                        System.out.print("Novo nome: ");
                        clienteEncontrado.setNome(scanner.nextLine());
                        System.out.print("Novo telefone: ");
                        clienteEncontrado.setTelefone(scanner.nextLine());
                        System.out.print("Novo e-mail: ");
                        clienteEncontrado.setEmail(scanner.nextLine());

                        System.out.println("Cliente atualizado com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }

                case 4 -> {
                    System.out.print("Digite o ID do cliente que deseja excluir: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();

                    Cliente clienteExcluir = null;
                    for (Cliente cliente : clientes) {
                        if (cliente.getId() == idExcluir) {
                            clienteExcluir = cliente;
                            break;
                        }
                    }

                    if (clienteExcluir != null) {
                        clientes.remove(clienteExcluir);
                        System.out.println("Cliente removido com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }

                case 5 -> {
                    System.out.print("Digite o ID do cliente que deseja buscar: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();

                    Cliente clienteEncontrado = null;
                    for (Cliente cliente : clientes) {
                        if (cliente.getId() == idBuscar) {
                            clienteEncontrado = cliente;
                            break;
                        }
                    }

                    if (clienteEncontrado != null) {
                        System.out.println("Detalhes do cliente:");
                        System.out.println("ID: " + clienteEncontrado.getId());
                        System.out.println("Nome: " + clienteEncontrado.getNome());
                        System.out.println("Telefone: " + clienteEncontrado.getTelefone());
                        System.out.println("E-mail: " + clienteEncontrado.getEmail());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }

                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            limparTela();
        }
    }
}
