package admin;

public class Admin {

    /**
     * Imprime o conteúdo de uma matriz
     *
     * @param fileMatrix uma matriz de strings
     */
    public static void printFileContent(String[][] fileMatrix) {
        for (int i = 0; i < fileMatrix.length; i++) {
            for (int j = 0; j < fileMatrix[i].length; j++) {
                System.out.print(fileMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Imprime a quantidade de vendas e o seu valor total
     *
     * @param salesMatrix a matriz com os dados de vendas
     * @return o valor do total de vendas caso seja preciso usar no futuro
     */
    public static double totalSales(String[][] salesMatrix) {
        double totalSales = 0;
        int counter = 0;
        for (int i = 0; i < salesMatrix.length; i++) {
            totalSales += Double.parseDouble(salesMatrix[i][5]);
            counter++;
        }
        System.out.println("\nNúmero de Vendas: " + counter);
        System.out.println("Total de Vendas : " + String.format("%.2f", totalSales) + "€\n");
        return totalSales;
    }

    /**
     * Imprime o total de lucro tendo em conta as percentagens adequadas
     *
     * @param salesMatrix      a matriz das vendas
     * @param categoriesMatrix a matriz das categorias
     * @return o valor total de lucro
     */
    public static double totalProfit(String[][] salesMatrix, String[][] categoriesMatrix) {
        double totalProfit = 0;

        for (int i = 0; i < salesMatrix.length; i++) {
            for (int j = 0; j < categoriesMatrix.length; j++) {

                if (salesMatrix[i][3].equals(categoriesMatrix[j][0])) {
                    double profitMargin = Double.parseDouble(categoriesMatrix[j][1]) / 100;
                    double price = Double.parseDouble(salesMatrix[i][5]);
                    totalProfit += profitMargin * price;
                }
            }
        }
        return totalProfit;
    }

    /**
     * Imprime as informações associadas a um determinado client
     *
     * @param clientsMatrix a matriz dos clientes
     * @param idClient      o valor do id do cliente a pesquisar
     */
    public static void searchClientById(String[][] clientsMatrix, int idClient) {
        for (int i = 0; i < clientsMatrix.length; i++) {
            int idClientMatrix = Integer.parseInt(clientsMatrix[i][0]);

            if (idClientMatrix == idClient) {
                System.out.println("\nNome: " + clientsMatrix[i][1] + " - Contacto: " + clientsMatrix[i][2] +
                        " - Email: " + clientsMatrix[i][3] + "\n");
            }
        }
    }


    /**
     * Imprime o jogo mais caro e os clientes que o compraram
     *
     * @param salesMatrix   a matriz das vendas
     * @param clientsMatrix a matriz dos clientes
     */
    public static void mostExpensiveGame(String[][] salesMatrix, String[][] clientsMatrix) {
        double mostExpensive = 0;
        String game = "";
        // Verifica qual é o jogo mais caro
        for (int i = 0; i < salesMatrix.length; i++) {
            double price = Double.parseDouble(salesMatrix[i][5]);

            if (price > mostExpensive) {
                mostExpensive = price;
                game = salesMatrix[i][4];
            }
        }
        System.out.println("\nJogo Mais Caro: " + game + "\n\nCompradores:\n");

        // Imprime todos os clientes que compraram o jogo mais caro
        for (int i = 0; i < salesMatrix.length; i++) {
            String idSales = salesMatrix[i][1];

            if (salesMatrix[i][4].equals(game)) {
                for (int j = 0; j < clientsMatrix.length; j++) {
                    String idClients = clientsMatrix[j][0];

                    if (idSales.equals(idClients)) {
                        System.out.println("Nome: " + clientsMatrix[j][1] + " - Contacto: " + clientsMatrix[j][2] + " - Email: " + clientsMatrix[j][3]);
                    }
                }
            }
        }
    }

    /**
     * Imprime o melhor cliente e todos os jogos que comprou
     *
     * @param salesMatrix   a matriz das vendas
     * @param clientsMatrix a matriz dos clientes
     */
    public static void bestClient(String[][] salesMatrix, String[][] clientsMatrix) {
        String bestClient = "";
        double highestSpent = 0;
        // Verifica qual é o cliente que mais gastou
        for (int i = 0; i < clientsMatrix.length; i++) {
            String idClient = clientsMatrix[i][0];
            double totalSpent = 0;

            for (int j = 0; j < salesMatrix.length; j++) {
                if (salesMatrix[j][1].equals(idClient)) {
                    totalSpent += Double.parseDouble(salesMatrix[j][5]);
                }
            }
            if (totalSpent > highestSpent) {
                highestSpent = totalSpent;
                bestClient = idClient;
            }
        }
        // Imprime as informações associadas ao melhor cliente
        for (int i = 0; i < clientsMatrix.length; i++) {
            if (clientsMatrix[i][0].equals(bestClient)) {
                System.out.println("\nNome: " + clientsMatrix[i][1] + " - Contacto: " + clientsMatrix[i][2]
                        + " - Email: " + clientsMatrix[i][3]);
                break;
            }
        }
        // Imprime todos os jogos comprados pelo cliente que mais gastou
        System.out.println("\nLista de Jogos Comprados: \n");
        for (int i = 0; i < salesMatrix.length; i++) {
            if (salesMatrix[i][1].equals(bestClient)) {
                System.out.println(salesMatrix[i][4]);
            }
        }
    }

    /**
     * Imprime a categoria que gerou mais lucro e o respetivo lucro
     *
     * @param salesMatrix      a matriz das vendas
     * @param categoriesMatrix a matriz das categorias
     */
    public static void bestCategory(String[][] salesMatrix, String[][] categoriesMatrix) {
        String bestCategory = "";
        double highestProfit = 0;

        for (int i = 0; i < categoriesMatrix.length; i++) {
            String category = categoriesMatrix[i][0];
            double profitMargin = Double.parseDouble(categoriesMatrix[i][1]) / 100;
            double accumulatedProfit = 0;

            for (int j = 0; j < salesMatrix.length; j++) {
                String categorySales = salesMatrix[j][3];
                double price = Double.parseDouble(salesMatrix[j][5]);

                if (category.equals(categorySales)) {
                    accumulatedProfit += (price * profitMargin);
                }
            }
            if (accumulatedProfit > highestProfit) {
                highestProfit = accumulatedProfit;
                bestCategory = category;
            }
        }
        System.out.println(String.format("\nMelhor Categoria: " + bestCategory + " - Lucro Total: " + String.format("%.2f", highestProfit) + "€\n"));
    }

    /**
     * Imprime todos os clientes que compraram um determinado jogo dado pelo utilizador
     *
     * @param salesMatrix   a matriz das vendas
     * @param clientsMatrix a matriz dos clientes
     * @param game          uma string jogo que é dado pelo utilizador
     */
    public static void searchSalesByGame(String[][] salesMatrix, String[][] clientsMatrix, String game) {
        for (int i = 0; i < salesMatrix.length; i++) {
            String idSales = salesMatrix[i][1];
            String gameSales = salesMatrix[i][4];

            if (game.equals(gameSales)) {
                for (int j = 0; j < clientsMatrix.length; j++) {
                    String idClient = clientsMatrix[j][0];

                    if (idSales.equals(idClient)) {
                        System.out.println("Nome: " + clientsMatrix[j][1] + " - Contacto: " + clientsMatrix[j][2]
                                + " - Email: " + clientsMatrix[j][3]);
                    }
                }
            }
        }
    }

    /**
     * Ordena de forma crescente pelo seu lucro gerado
     * @param salesMatrix a matriz das vendas
     * @param categoriesMatrix a matriz das categorias
     * @param games uma array sem jogos duplicados
     * @return uma matriz de jogos e lucro de forma ordenada
     */
    public static String[][] sortedProfitPerGame(String[][] salesMatrix, String[][] categoriesMatrix, String[] games) {
        String[][] gamesMatrix = new String[games.length][2];
        // Preenche a nova matriz com os jogos únicos vindo do array criada num método anterior e acrescenta o lucro
        for (int i = 0; i < gamesMatrix.length; i++) {
            gamesMatrix[i][0] = games[i];
            String gameName = gamesMatrix[i][0];
            double accumulatedProfit = 0;

            for (int j = 0; j < salesMatrix.length; j++) {
                if (gameName.equals(salesMatrix[j][4])) {
                    String categorySales = salesMatrix[j][3];
                    double price = Double.parseDouble(salesMatrix[j][5]);

                    for (int k = 0; k < categoriesMatrix.length; k++) {
                        if (categorySales.equals(categoriesMatrix[k][0])) {
                            double profitMargin = Double.parseDouble(categoriesMatrix[k][1]) / 100;
                            accumulatedProfit += (price * profitMargin);
                        }
                    }
                }
            }
            gamesMatrix[i][1] = String.valueOf(accumulatedProfit);
        }
        // Ordenar a matriz através de buble sort
        for (int i = 0; i < gamesMatrix.length; i++) {
            for (int j = 0; j < gamesMatrix.length - i - 1; j++) {

                if (Double.parseDouble(gamesMatrix[j][1]) > Double.parseDouble(gamesMatrix[j + 1][1])) {
                    String tempProfit = gamesMatrix[j][1];
                    gamesMatrix[j][1] = gamesMatrix[j + 1][1];
                    gamesMatrix[j + 1][1] = tempProfit;

                    String tempGame = gamesMatrix[j][0];
                    gamesMatrix[j][0] = gamesMatrix[j + 1][0];
                    gamesMatrix[j + 1][0] = tempGame;
                }
            }
        }
        return gamesMatrix;
    }
}
