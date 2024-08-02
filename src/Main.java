import gamestart.Menu;
import utils.CSVUtils;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        try {
            String[][] salesMatrix = CSVUtils.convertCSVtoMatrix("resources/GameStart_Vendas.csv", true);
            String[][] clientsMatrix = CSVUtils.convertCSVtoMatrix("resources/GameStart_Clientes.csv", true);
            String[][] categoriesMatrix = CSVUtils.convertCSVtoMatrix("resources/GameStart_Categorias.csv", true);

            Menu.login(salesMatrix, clientsMatrix, categoriesMatrix);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}