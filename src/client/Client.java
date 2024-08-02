package client;

import utils.CSVUtils;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {

    /**
     * Imprime as informações introduzidas pelo cliente
     */
    public static void registerNewClient() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome: ");
        String name = sc.nextLine();
        System.out.print("Contacto: ");
        String contact = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.println("\nNome: " + name + " - Contato: " + contact + " - Email: " + email + "\n");
    }

    /**
     * Imprime todos os números triangulares múltiplos de 5 num limite de 121
     */
    public static void searchCarParking() {
        for (int i = 1; i <= 121; i++) {
            int triangular = (i * (i + 1)) / 2;

            if (triangular <= 121 && triangular % 5 == 0) {
                System.out.println(triangular);
            }
        }
    }

    /**
     * Array com jogos únicos
     *
     * @param salesMatrix a matriz das vendas
     * @return um array com os jogos sem duplicados
     */
    public static String[] printCatalog(String[][] salesMatrix) {
        String[] gamesWithNulls = new String[salesMatrix.length];
        int index = 0;

        for (int i = 0; i < salesMatrix.length; i++) {
            String game = salesMatrix[i][4];
            boolean isDuplicated = false;

            // Volta a correr a matriz e procura se há duplicados
            for (int j = 0; j < salesMatrix.length; j++) {
                if (gamesWithNulls[j] != null && gamesWithNulls[j].equals(game)) {
                    isDuplicated = true;
                    break; // Basta encontrar um duplicado, dá break e passa para a próxima iteração do i
                }
            }
            if (!isDuplicated) {
                gamesWithNulls[index] = salesMatrix[i][4];
                index++; // Invés de usar o i como index, usa
            }
        }
        // Nova array e ciclo para poder limpar os nulls
        String[] games = new String[index];
        for (int i = 0; i < index; i++) {
            games[i] = gamesWithNulls[i];
        }
        return games;
    }

    /**
     * Mostra um menu e imprime um ficheiro txt dependendo da escolha do utilizador
     *
     * @throws FileNotFoundException
     */
    public static void printGraphicsCatalog() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n1. Call of Duty");
        System.out.println("2. Fifa");
        System.out.println("3. Hollow Knight");
        System.out.println("4. Mortal Kombat");
        System.out.println("5. Overcooked");
        System.out.println("6. Witcher 3: Wild Hunt");
        System.out.println("7. Minecraft");
        System.out.print("\nEscolha uma opção: ");
        int option = sc.nextInt();

        String filepath = "resources/CatalogoGrafico/";

        switch (option) {
            case 1:
                CSVUtils.printTxt(filepath + "callofduty.txt");
                break;
            case 2:
                CSVUtils.printTxt(filepath + "fifa.txt");
                break;
            case 3:
                CSVUtils.printTxt(filepath + "hollowKnight.txt");
                break;
            case 4:
                CSVUtils.printTxt(filepath + "mortalKombat.txt");
                break;
            case 5:
                CSVUtils.printTxt(filepath + "overcooked.txt");
                break;
            case 6:
                CSVUtils.printTxt(filepath + "witcher3.txt");
                break;
            case 7:
                CSVUtils.printTxt(filepath + "minecraft.txt");
                break;
        }
    }

    /**
     * Jogo mais recente adicionado
     *
     * @param salesMatrix a matriz das vendas
     * @return o valor da última posição do array de jogos
     */
    public static String latestGame(String[][] salesMatrix) {
        int lastIndex = printCatalog(salesMatrix).length - 1;
        return printCatalog(salesMatrix)[lastIndex];
    }

    /**
     * Imprime todas as categorias e os respetivos jogos dada uma editora
     * @param salesMatrix a matriz das vendas
     * @param categoriesMatrix a matriz das categorias
     * @param publisher uma string com a editora dada pelo utilizador
     */
    public static void printPublisherCatalog(String[][] salesMatrix, String[][] categoriesMatrix, String publisher) {
        int count = 0;
        // Conta tudo o que seja da mesma editora introduzida
        for (int i = 0; i < salesMatrix.length; i++) {
            if (salesMatrix[i][2].equals(publisher)) {
                count++;
            }
        }

        String[][] categoriesAndGames = new String[count][2];
        // Preenche a matriz com a categoria e o jogo
        for (int i = 0; i < categoriesAndGames.length; i++) {
            if (salesMatrix[i][2].equals(publisher)) {
                categoriesAndGames[i][0] = salesMatrix[i][3];
                categoriesAndGames[i][1] = salesMatrix[i][4];
            }
        }

        // Apresenta os jogos dividos por categoria, pertencentes à mesma editora introduzida
        for (int i = 0; i < categoriesMatrix.length; i++) {
            String category = categoriesMatrix[i][0];
            boolean hasGames = false;
            // Verifica se há jogos para a categoria
            for (int j = 0; j < categoriesAndGames.length; j++) {
                if (categoriesAndGames[j][0] != null && categoriesAndGames[j][0].equals(category)) {
                    if (!hasGames) {
                        System.out.println("\n**** " + category + " *****");
                        hasGames = true;
                    }
                    System.out.println(categoriesAndGames[j][1]);
                    categoriesAndGames[j][0] = null; // Substitui a categoria por null para não repetir
                }
            }
        }
    }

    /**
     * Imprime todas as editoras e os respetivos jogos dada uma categoria
     * @param salesMatrix a matriz das vendas
     * @param category uma string com a categoria dada pelo utilizador
     */
    public static void printCategoryCatalog(String[][] salesMatrix, String category) {
        int count = 0;
        // Conta tudo o que seja da mesma categoria introduzida
        for (int i = 0; i < salesMatrix.length; i++) {
            if (salesMatrix[i][3].equals(category)) {
                count++;
            }
        }

        String[][] publishersAndGames = new String[count][2];
        // Preenche a matriz com a editora e o jogo
        for (int i = 0; i < publishersAndGames.length; i++) {
            if (salesMatrix[i][3].equals(category)) {
                publishersAndGames[i][0] = salesMatrix[i][2]; // Editora
                publishersAndGames[i][1] = salesMatrix[i][4]; // Jogo
            }
        }

        // Apresenta os jogos dividos por editora, pertencentes à mesma categoria
        for (int i = 0; i < publishersAndGames.length; i++) {
            String publisher = publishersAndGames[i][0];

            if (publishersAndGames[i][0] != null) {
                System.out.println("\nEditora: " + publisher);
            }
            for (int j = 0; j < publishersAndGames.length; j++) {
                if (publishersAndGames[j][0] != null && publishersAndGames[j][0].equals(publisher)) {
                    System.out.println(publishersAndGames[j][1]);
                    publishersAndGames[j][0] = null; // Substitui a editora por null para não repetir
                }
            }
        }
    }
}
