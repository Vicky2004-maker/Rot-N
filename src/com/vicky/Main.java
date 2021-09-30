package com.vicky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final List<String> upperAlphabets = Arrays.asList(
            "A","B","C","D","E","F","G",
            "H","I","J","K","L","M","N",
            "O","P","Q","R","S","T","U",
            "V","W","X","Y","Z");

    private static final List<String> upperAlphabetsRev = Arrays.asList(
            "Z","Y","X","W","V","U","T",
            "S","R","Q","P","O","N","M",
            "L","K","J","I","H","G","F",
            "E","D","C","B","A");

    private static List<String> lowerAlphabets = new ArrayList<>();
    private static List<String> lowerAlphabetsRev = new ArrayList<>();

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (String c: upperAlphabets) {
            lowerAlphabets.add(c.toLowerCase());
        }

        for (String c: upperAlphabetsRev) {
            lowerAlphabetsRev.add(c.toLowerCase());
        }

        String toReplace = "";
        int toSkip = 0;
        try {
            toReplace = br.readLine();
            toSkip = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            encrypt(toReplace, toSkip);
            //decrypt(toReplace, toSkip);
        }

    }

    private static void encrypt(String value, int toSkip)
    {
        char[] encryptedWord = new char[value.length()];
        String finalAns = value;

        int pos = 0, newPos = 0, position = -1;
        boolean upper;
        for (char c : value.toCharArray()) {
            position++;

            pos = upperAlphabets.indexOf(String.valueOf(c));
            if (pos == -1) {
                pos = lowerAlphabets.indexOf(String.valueOf(c));
                upper = false;
            } else {
                upper = true;
            }

            newPos = (pos + toSkip) > 25 ? (pos + toSkip) % 26 : (pos + toSkip);

           encryptedWord[position] = upper ? upperAlphabets.get(newPos).charAt(0) : lowerAlphabets.get(newPos).charAt(0);
        }
        System.out.println(String.valueOf(encryptedWord));
    }

    private static void decrypt(String encryptedWord, int toRev)
    {
        char[] decryptedWord = new char[encryptedWord.length()];

        int pos = 0, originalPos = 0, position = -1;
        boolean upper;
        for (char c: encryptedWord.toCharArray()) {
            position++;

            pos = upperAlphabetsRev.indexOf(String.valueOf(c));
            if(pos == -1)
            {
                upper = false;
                pos = lowerAlphabetsRev.indexOf(String.valueOf(c));
            }
            else
            {
                upper = true;
            }

            originalPos = (pos + toRev) > 25 ? (pos + toRev) % 26 : (pos + toRev);

            decryptedWord[position] = upper ? upperAlphabetsRev.get(originalPos).charAt(0) : lowerAlphabetsRev.get(originalPos).charAt(0);
        }
        System.out.println(String.valueOf(decryptedWord));
    }
}
