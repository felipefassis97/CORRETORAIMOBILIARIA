import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Imovel> imoveis = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===== CORRETORA IMOBILIÁRIA =====");
            System.out.println("1. Cadastrar Casa");
            System.out.println("2. Cadastrar Apartamento");
            System.out.println("3. Listar Imóveis");
            System.out.println("4. Alugar Imóvel");
            System.out.println("5. Liberar Imóvel");
            System.out.println("6. Calcular Aluguel");
            System.out.println("7. Deletar Imóvel");
            System.out.println("8. Listar Imóveis Alugados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarImovel("Casa");
                case 2 -> cadastrarImovel("Apartamento");
                case 3 -> listarImoveis();
                case 4 -> alugarImovel();
                case 5 -> liberarImovel();
                case 6 -> calcularAluguel();
                case 7 -> deletarImovel();
                case 8 -> listarImoveisAlugados();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarImovel(String tipo) {
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Número: ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome do proprietário: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        Proprietario p = new Proprietario(nome, telefone, cpf);
        Imovel imovel = tipo.equals("Casa") ? new Casa(endereco, numero, p)
                                            : new Apartamento(endereco, numero, p);

        imoveis.add(imovel);
        System.out.println(tipo + " cadastrada com sucesso!");
    }

    private static void listarImoveis() {
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }
        System.out.println("\n=== LISTA DE IMÓVEIS ===");
        for (int i = 0; i < imoveis.size(); i++) {
            System.out.println("[" + i + "] " + imoveis.get(i).toString());
        }
    }

    private static void alugarImovel() {
        listarImoveis();
        System.out.print("Escolha o número do imóvel que deseja alugar: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx < 0 || idx >= imoveis.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Imovel im = imoveis.get(idx);
        if (im.estaAlugado()) {
            System.out.println("Este imóvel já está alugado!");
            return;
        }

        System.out.print("Nome do inquilino: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        Inquilino inq = new Inquilino(nome, telefone, cpf);
        im.alugar(inq);
        System.out.println("Imóvel alugado com sucesso para " + nome + "!");
    }

    private static void liberarImovel() {
        listarImoveis();
        System.out.print("Escolha o número do imóvel que deseja liberar: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx < 0 || idx >= imoveis.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Imovel im = imoveis.get(idx);
        if (!im.estaAlugado()) {
            System.out.println("Este imóvel já está disponível!");
            return;
        }

        im.liberar();
        System.out.println("Imóvel liberado com sucesso!");
    }

    private static void calcularAluguel() {
        listarImoveis();
        System.out.print("Escolha o número do imóvel: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx < 0 || idx >= imoveis.size()) {
            System.out.println("Número inválido!");
            return;
        }

        System.out.print("Quantos meses deseja calcular o aluguel? ");
        int meses = sc.nextInt();

        int valor = imoveis.get(idx).calcularAluguel(meses);
        System.out.println("Valor total do aluguel: R$ " + valor);
    }

    private static void deletarImovel() {
        listarImoveis();
        System.out.print("Escolha o número do imóvel que deseja deletar: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx < 0 || idx >= imoveis.size()) {
            System.out.println("Número inválido!");
            return;
        }

        imoveis.remove(idx);
        System.out.println("Imóvel removido com sucesso!");
    }

    private static void listarImoveisAlugados() {
        boolean temAlugados = false;
        System.out.println("\n=== IMÓVEIS ALUGADOS ===");
        for (int i = 0; i < imoveis.size(); i++) {
            Imovel im = imoveis.get(i);
            if (im.estaAlugado()) {
                System.out.println("[" + i + "] " + im.toString());
                temAlugados = true;
            }
        }
        if (!temAlugados) System.out.println("Nenhum imóvel alugado no momento.");
    }
}
