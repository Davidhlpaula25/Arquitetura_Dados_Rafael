package main;

import java.util.List;
import java.util.Scanner;

import modelo.Produto;
import servico.ProdutoService;

public class Main {
    public static void main(String[] args) {
        ProdutoService produtoService = new ProdutoService();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("----- Gerenciamento de Produtos -----");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do produto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o preço do produto: ");
                    double preco = scanner.nextDouble();
                    Produto produto = new Produto(id, nome, preco);
                    produtoService.cadastrarProduto(produto);
                    System.out.println("Produto cadastrado com sucesso.");
                    break;
                case 2:
                    List<Produto> produtos = produtoService.obterTodosProdutos();
                    System.out.println("----- Lista de Produtos -----");
                    for (Produto p : produtos) {
                        System.out.println(p);
                    }
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);
        scanner.close();
    }
}
