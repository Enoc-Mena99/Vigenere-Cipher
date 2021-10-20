package com.vigenere;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Vigenere {

    public static void main(String[] args) {

        int strLength = args.length;

        if(strLength < 2 || strLength > 2) {

            System.out.println("Error: Provide correct arguments.");

        }

        try {

            System.out.println("Input file: " + args[1]);
            System.out.println("Key file: " + args[0]);

            //Functions
            char[] inArray = FileToCharArray(args[1]);
            char[] kArray = FileToCharArray(args[0]);
            char[] conInput = ToLowerCase(inArray);
            char[] conKey = ToLowerCase(kArray);

            String encryptedTxt = encrypt(conInput, conKey);
            String formattedEncrypted = format(80, encryptedTxt);

            System.out.println("Encrypted file content after formatting: \n");
            System.out.println(formattedEncrypted);

        } catch (IOException e) {

            e.printStackTrace();

        }

        System.out.println("The File Was Encrypted Successfully!");

    }

    public static char[] FileToCharArray(String file) throws IOException {

        StringBuilder data = new StringBuilder();
        BufferedReader dataRead = new BufferedReader(new FileReader(file));
        int counter = 0;
        char[] charArray = new char[10];
        while((counter = dataRead.read(charArray)) != -1) {

            String d = String.valueOf(charArray,0,counter);
            data.append(d);
            charArray = new char[1024];

        }

        dataRead.close();
        return data.toString().toCharArray();

    }

    private static String encrypt(char[] arr, char[] key) {

        String a = "";
        for(int i = 0, j = 0; i < arr.length; i++) {

            char b = arr[i];
            if(b < 'a' || b > 'z')
                continue;
            a += (char) ((b + key[j] - 2 * 'a') % 26 + 'a');
            j = ++j % key.length;

        }

        return a;

    }

    private static char[] ToLowerCase(char[] arr) {

        char[] con = new char[arr.length];
        for(int i = 0; i < arr.length; i++) {

            con[i] = Character.toLowerCase(arr[i]);

        }

        return con;

    }

    private static String format(int size, String string) {

        StringBuilder build = new StringBuilder(size);
        int a = 0;

        for(int i = 0; i < string.length(); i++) {

            if (a < size) {

                build.append(string.charAt(i));

            } else {

                build.append("\n");
                build.append(string.charAt(i));
                a = 0;

            }

            a++;

        }

        return build.toString();

    }

}