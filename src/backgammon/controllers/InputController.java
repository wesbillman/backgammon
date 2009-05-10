package backgammon.controllers;

import java.io.*;
import java.util.logging.*;

/**
 * Taken from sample code provided by instrutor, slightly modified.
 */
public class InputController {
    public static String readString() {
        String str = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1);

        try {
            str = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(InputController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }

    public static int readInt() {
        return Integer.parseInt(readString());
    }

    public static double readDouble() {
        return Double.parseDouble(readString());
    }
}
