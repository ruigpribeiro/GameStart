package gamestart;

import admin.Admin;
import client.Client;
import utils.CSVUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {

    /**
     * Imprime o menu login, verifica as credencias do admin e encaminha para outros menus
     * @param salesMatrix a matriz das vendas
     * @param clientsMatrix a matriz dos clientes
     * @param categoriesMatrix a matriz das categorias
     * @throws FileNotFoundException
     */
    public static void login(String[][] salesMatrix, String[][] clientsMatrix, String[][] categoriesMatrix) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String[][] adminMatrix = CSVUtils.convertCSVtoMatrix("resources/GameStart_Admins.csv", false);

        while (true) {
            System.out.print("Tipo de Utilizador (admin/client): ");
            String option = sc.nextLine();

            switch (option) {
                case "admin":
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();
                    if (loginAuthentication(adminMatrix, username, password)) {
                        System.out.println("\n*** Login efetuado com sucesso! ***\n");
                        admin(salesMatrix, clientsMatrix, categoriesMatrix);
                        return; // Saí do método
                    } else {
                        System.out.println("Dados incorretos! Tente novamente.\n");
                    }
                    break;
                case "client":
                    System.out.println();
                    client(salesMatrix, clientsMatrix, categoriesMatrix);
                    break;
                default:
                    System.out.println("\nUtilizador incorreto! Tente novamente.\n");
                    break;
            }
        }
    }

    /**
     * Verifica no ficheiro dos admins se o username e a password estão corretas
     * @param adminCSV a matriz dos admins
     * @param username um username
     * @param password uma password
     * @return verdadeiro ou falso dependendo da verificação
     */
    public static boolean loginAuthentication(String[][] adminCSV, String username, String password) {
        for (int i = 0; i < adminCSV.length; i++) {
            if (adminCSV[i][0].equals(username) && adminCSV[i][1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Imprime um menu admin com diferentes opções
     * @param salesMatrix a matriz das vendas
     * @param clientsMatrix a matriz dos clientes
     * @param categoriesMatrix a matriz das categorias
     */
    public static void admin(String[][] salesMatrix, String[][] clientsMatrix, String[][] categoriesMatrix) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("******* MENU ADMIN *******");
            System.out.println("1. Consultar Ficheiros");
            System.out.println("2. Total de Vendas");
            System.out.println("3. Total de Lucro");
            System.out.println("4. Pesquisar Cliente");
            System.out.println("5. Jogo Mais Caro");
            System.out.println("6. Melhor Cliente");
            System.out.println("7. Melhor Categoria");
            System.out.println("8. Pesquisar Vendas");
            System.out.println("9. Top 5 Jogos");
            System.out.println("10. Bottom 5 Jogos");
            System.out.println("11. Sair do programa");
            System.out.print("\nEscolha uma opção: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    files(salesMatrix, clientsMatrix, categoriesMatrix);
                    break;
                case 2:
                    Admin.totalSales(salesMatrix);
                    break;
                case 3:
                    System.out.println("\nTotal de Lucro: " + String.format("%.2f", Admin.totalProfit(salesMatrix, categoriesMatrix)) + "€\n");
                    break;
                case 4:
                    Admin.searchClientById(clientsMatrix, askClientID());
                    break;
                case 5:
                    Admin.mostExpensiveGame(salesMatrix, clientsMatrix);
                    System.out.println();
                    break;
                case 6:
                    Admin.bestClient(salesMatrix, clientsMatrix);
                    System.out.println();
                    break;
                case 7:
                    Admin.bestCategory(salesMatrix, categoriesMatrix);
                    break;
                case 8:
                    Admin.searchSalesByGame(salesMatrix, clientsMatrix, askUser("jogo"));
                    System.out.println();
                    break;
                case 9:
                    printTop5Games(Admin.sortedProfitPerGame(salesMatrix, categoriesMatrix, Client.printCatalog(salesMatrix)));
                    System.out.println();
                    break;
                case 10:
                    printBottom5Games(Admin.sortedProfitPerGame(salesMatrix, categoriesMatrix, Client.printCatalog(salesMatrix)));
                    System.out.println();
                    break;
                case 11:
                    copyright();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    /**
     * Imprime um menu cliente com diferentes opções
     * @param salesMatrix a matriz das vendas
     * @param clientsMatrix a matriz dos clientes
     * @param categoriesMatrix a matriz das categorias
     * @throws FileNotFoundException
     */
    public static void client(String[][] salesMatrix, String[][] clientsMatrix, String[][] categoriesMatrix) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("******* MENU CLIENT *******");
            System.out.println("1. Registar Cliente");
            System.out.println("2. Procurar Estacionamento");
            System.out.println("3. Ver Catálogo Jogos");
            System.out.println("4. Ver Catálogo Gráficos");
            System.out.println("5. Ver Catálogo Editora");
            System.out.println("6. Ver Catálogo Categoria");
            System.out.println("7. Jogo Mais Recente");
            System.out.println("8. Sair do programa");
            System.out.print("\nEscolha uma opção: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    Client.registerNewClient();
                    break;
                case 2:
                    Client.searchCarParking();
                    break;
                case 3:
                    System.out.println();
                    printUniqueGames(Client.printCatalog(salesMatrix));
                    System.out.println();
                    break;
                case 4:
                    Client.printGraphicsCatalog();
                    break;
                case 5:
                    Client.printPublisherCatalog(salesMatrix, categoriesMatrix, askUser("editora"));
                    System.out.println();
                    break;
                case 6:
                    Client.printCategoryCatalog(salesMatrix, askUser("categoria"));
                    System.out.println();
                    break;
                case 7:
                    System.out.println("\n" + Client.latestGame(salesMatrix) + "\n");
                    break;
                case 8:
                    copyright();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    /**
     * Imprime um menu com opções de ficheiros e reencaminha para outros métodos
     * @param salesMatrix a matriz das vendas
     * @param clientsMatrix a matriz dos clientes
     * @param categoriesMatrix a matriz das categorias
     */
    public static void files(String[][] salesMatrix, String[][] clientsMatrix, String[][] categoriesMatrix) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nFicheiros Disponiveis: ");
        System.out.println("1. Vendas");
        System.out.println("2. Clientes");
        System.out.println("3. Categorias");
        System.out.print("\nEscolha uma opção: ");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                Admin.printFileContent(salesMatrix);
                break;
            case 2:
                Admin.printFileContent(clientsMatrix);
                break;
            case 3:
                Admin.printFileContent(categoriesMatrix);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    /**
     * Pergunta o id do cliente
     *
     * @return o valor do id do cliente
     */
    public static int askClientID() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduza o ID do cliente: ");
        return sc.nextInt();
    }

    /**
     * Pergunta algo ao user
     * @return uma string com o input do utilizador
     */
    public static String askUser(String text) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Escolha um " + text +": ");
        return sc.nextLine();
    }

    /**
     * Imprime um array de jogos
     *
     * @param games um array de jogos únicos
     */
    public static void printUniqueGames(String[] games) {
        for (int i = 0; i < games.length; i++) {
            if (games[i] != null) {
                System.out.println(games[i]);
            }
        }
    }

    /**
     * Imprime o top 5 de jogos mais lucrativos
     * @param games uma matriz ordenada com os jogos e o seu respetivo lucro
     */
    public static void printTop5Games(String[][] games) {
        int index = 1;
        for (int i = games.length - 1; i >= games.length - 5; i--) {
            System.out.println("Top " + index + ": " + games[i][0] + " - Lucro Total: " + String.format("%.2f", Double.parseDouble(games[i][1])) + "€");
            index++;
        }
    }

    /**
     * Imprime o bottom 5 de jogos menos lucrativos
     * @param games uma matriz ordenada com os jogos e o seu respetivo lucro
     */
    public static void printBottom5Games(String[][] games) {
        int index = 1;
        for (int i = 0; i < 5; i++) {
            System.out.println("Bottom " + index + ": " + games[i][0] + " - Lucro Total: " + String.format("%.2f", Double.parseDouble(games[i][1])) + "€");
            index++;
        }
    }

    /**
     * Imprime um ficheiro txt
     * @throws FileNotFoundException
     */
    public static void copyright() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("resources/GameStart_Copyright.txt"));
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            System.out.println(currentLine);
        }
    }
}
