/*
Autor: Oscar Valdés
Última modificación: 07 de Junio de 2022
Descripción: Este programa lee los archivos de una carpeta y se los alimenta a un analizador lexico uno a la vez.
*/

import java.io.File;

public class seqSynt {
    public static void main(String[] args) throws InterruptedException {
        // START TIMER
        long start = System.currentTimeMillis();

        // GET INPUT FILES AND SCANNER
        String directory = "C:\\Users\\oscar\\Documentos\\IMC_Proyecto\\";
        File[] files = new File(directory + "Sequential\\Inputs").listFiles();
        File myscanner = new File(directory + "Scanner\\scannerV2.exe");

        // INITIALIZE VARIABLES
        int fileCount = files.length;

        for (int i = 0; i < fileCount; i++) {
            if (files[i].isFile() /* IGNORE DIRECTORIES */) {
                try {
                    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "type " + files[i].getAbsolutePath() + " | " + myscanner.getAbsolutePath() + " .\\Outputs\\" + files[i].getName() + ".html").inheritIO();
                    builder.start();
                } catch (Exception e) {
                    System.out.println("Exceptio while scanning file: " + files[i].getAbsolutePath() + "\n" + e);
                }
            }
        }

        // STOP TIMER
        long end = System.currentTimeMillis();
        long execTime = end - start;
        System.out.println("Time of execution was: " + execTime + " milliseconds.");
    }
}
