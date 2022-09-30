/*
Autor: Oscar Valdés
Última modificación: 07 de Junio de 2022
Descripción: Este programa lee los archivos de una carpeta y se los alimenta a un analizador lexico de manera paralela mediante hilos.
*/

import java.io.File;

class MultithreadingScanner implements Runnable {
    // INITIALIZE PRIVATE VARIABLES
    private volatile int first;
    private volatile int last;
    private volatile File[] files;
    private volatile File myscanner;

    // METHOD TO ASSIGN FILES
    public void setFirstLast(int first, int last) {
        this.first = first;
        this.last = last;
    }

    // METHOD TO PASS FILES
    public void setFiles(File[] files) {
        this.files = files;
    }

    // METHOD TO SET SCANNER
    public void setScanner(File myscanner){
        this.myscanner = myscanner;
    }

    // RUNNABLE THREAD METHOD
    public void run() {
        try {
            // DISPLAY THE ID OF THIS THREAD
            // System.out.println("Running thread is: " + Thread.currentThread().getId());
            
            // SCAN ALL OF THE FILES ASSIGNED TO THIS THREAD
            for (int i = this.first; i <= this.last; i++) {
                if (this.files[i].isFile() /* IGNORE DIRECTORIES */) {
                    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "type " + this.files[i].getAbsolutePath() + " | " + myscanner.getAbsolutePath() + " .\\Outputs\\" + this.files[i].getName() + ".html").inheritIO();
                    builder.start();
                }
            }

        } catch (Exception e) {
            System.out.println("Exception caught in thread" + Thread.currentThread().getId() + "\n" + e);
        }
    }
}

public class parSynt {
    public static void main(String[] args) throws InterruptedException {
        // START TIMER
        long start = System.currentTimeMillis();

        // GET INPUT FILES AND SCANNER
        String directory = "C:\\Users\\oscar\\Documentos\\IMC_Proyecto\\";
        File[] files = new File(directory + "Parallel\\Inputs").listFiles();
        File myscanner = new File(directory + "Scanner\\scannerV2.exe");

        // INITIALIZE VARIABLES
        int fileCount = files.length;
        int num_threads = 8;
        int part_n = fileCount / num_threads;
        int remains = fileCount % num_threads;

        // CREATE THREADS AND SCANNER ARRAY
        Thread[] myThreads = new Thread[num_threads];
        MultithreadingScanner[] myScanners = new MultithreadingScanner[num_threads];

        // ASIGN FILES AND SCANNER TO THREADS
        for (int i = 0; i < num_threads; i++) {
            myScanners[i] = new MultithreadingScanner();
            if (i < remains) {
                myScanners[i].setFirstLast(i * (part_n + 1), part_n + i + part_n * i);
            } else {
                myScanners[i].setFirstLast(remains + part_n * i, remains + part_n * i + part_n - 1);
            }
            myScanners[i].setFiles(files);
            myScanners[i].setScanner(myscanner);
            myThreads[i] = new Thread(myScanners[i]);
            myThreads[i].start();
        }

        // WAIT FOR ALL THREADS TO FINISH
        for (int i = 0; i < num_threads; i++) {
            myThreads[i].join();
        }

        // STOP TIMER
        long end = System.currentTimeMillis();
        long execTime = end - start;
        System.out.println("Time of execution was: " + execTime + " milliseconds.");
    }
}
