package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVUtils {

    /**
     * Transforma um ficheiro csv em uma matriz de strings
     * @param csvFilePath caminho do ficheiro csv
     * @param ignoreHeader boleano para indicar se o cabeçalho é para ser ignorado
     * @return uma matriz de strings com os dados do ficheiro csv
     * @throws FileNotFoundException
     */
    public static String[][] convertCSVtoMatrix(String csvFilePath, boolean ignoreHeader) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(csvFilePath));
        int lineCount = 0;

        if (ignoreHeader) {
            sc.nextLine();
        }
        // Contar o número de linhas
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lineCount++;
        }
        sc.close();

        sc = new Scanner(new File(csvFilePath)); // Abre outra vez o scanner para voltar a ler o csv do ínicio
        if (ignoreHeader) {
            sc.nextLine();
        }
        String[][] matrix = new String[lineCount][];
        int index = 0;
        // Ciclo para preencher a matriz com os dados do csv
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            matrix[index] = line.split(";"); // Coloca um array dentro da matriz no respetivo index
            index++;
        }
        return matrix;
    }

    /**
     * Imprime um ficheiro txt na consola
     * @param filePath o caminho do ficheiro txt
     * @throws FileNotFoundException
     */
    public static void printTxt(String filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
        }
    }
}
