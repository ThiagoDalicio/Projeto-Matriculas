package app;

import service.MatriculaService;
import service.DadosService;
import model.Matricula;
import model.Dados;
import java.util.Scanner;
import java.util.List;

public class AppMenuMatricula {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MatriculaService matriculaService = new MatriculaService();
        DadosService dadosService = new DadosService();

        int opcao;
        do {
            System.out.println("\n===== MENU MATRÍCULAS =====");
            System.out.println("1 - Incluir Matrícula");
            System.out.println("2 - Consultar Matrícula");
            System.out.println("3 - Alterar Matrícula");
            System.out.println("4 - Excluir Matrícula");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    incluirMatricula(scanner, matriculaService, dadosService);
                    break;
                case 2:
                    consultarMatricula(scanner, matriculaService, dadosService);
                    break;
                case 3:
                    alterarMatricula(scanner, matriculaService);
                    break;
                case 4:
                    excluirMatricula(scanner, matriculaService);
                    break;
                case 0:
                    System.out.println("Tchau...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void incluirMatricula(Scanner scanner, MatriculaService matriculaService, DadosService dadosService) {
        System.out.println("\n1.1 - Incluir Matrícula");
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        matriculaService.inserir(nome);
        System.out.println("Matrícula inserida com sucesso!");

        System.out.println("\n1.2 - Incluir Dados");
        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();

        // Validação da idade
        int idade = 0;
        boolean idadeValida = false;
        while (!idadeValida) {
            System.out.print("Digite a idade: ");
            String input = scanner.nextLine().trim();

            if (input.matches("\\d+")) {
                idade = Integer.parseInt(input);
                idadeValida = true;
            } else {
                System.out.println("⚠ Entrada inválida! Digite um número inteiro para a idade.");
            }
        }

        // Validação do gênero
        String genero = "";
        boolean generoValido = false;
        while (!generoValido) {
            System.out.print("Digite o gênero (Masculino/Feminino/Outro): ");
            genero = scanner.nextLine().trim();

            if (genero.equalsIgnoreCase("Masculino") || genero.equalsIgnoreCase("Feminino") || genero.equalsIgnoreCase("Outro")) {
                generoValido = true;
            } else {
                System.out.println("⚠ Entrada inválida! Digite 'Masculino', 'Feminino' ou 'Outro'.");
            }
        }

        dadosService.inserir(nome, endereco, cep, idade, genero);
        System.out.println("✅ Dados inseridos com sucesso!");
    }

    private static void consultarMatricula(Scanner scanner, MatriculaService matriculaService, DadosService dadosService) {
        System.out.print("Digite o ID da matrícula para visualizar os dados: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Dados dados = dadosService.buscarPorId(id);
        if (dados != null) {
            System.out.println("\n===== DADOS DA MATRÍCULA =====");
            System.out.println("Nome: " + dados.getNome());
            System.out.println("Endereço: " + dados.getEndereco());
            System.out.println("CEP: " + dados.getCep());
            System.out.println("Estado: " + dados.getEstado());
            System.out.println("Cidade: " + dados.getCidade());
            System.out.println("Idade: " + dados.getIdade());
            System.out.println("Gênero: " + dados.getGenero());
        } else {
            System.out.println("Nenhum dado encontrado para essa matrícula.");
        }
    }

    private static void alterarMatricula(Scanner scanner, MatriculaService matriculaService) {
        System.out.print("\nDigite o ID da matrícula a ser alterada: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Digite o novo nome: ");
        String novoNome = scanner.nextLine();
        matriculaService.alterar(id, novoNome);
        System.out.println("Nome atualizado com sucesso!");
    }

    private static void excluirMatricula(Scanner scanner, MatriculaService matriculaService) {
        System.out.print("\nDigite o ID da matrícula a ser excluída: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        matriculaService.excluir(id);
        System.out.println("Matrícula excluída com sucesso!");
    }
}

