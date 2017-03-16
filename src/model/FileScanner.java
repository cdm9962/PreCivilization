package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Responsible for scanning, writing to, and manipulating files.
 *
 * @author Connor D. Milligan
 */
public class FileScanner {
    private Scanner scanner;

    /**
     * File scanner method.
     * @return String representing the scanned text
     * @throws FileNotFoundException
     */
    private String scanLogo() throws FileNotFoundException {
        InputStream stream = new FileInputStream("splashLogo.txt");
        Scanner scanner = new Scanner(stream);
        String logo = " ";

        while (scanner.hasNextLine()){
            logo += scanner.nextLine();
            logo += "\n ";
            System.out.println(logo);
        }

        return logo;
    }

}
