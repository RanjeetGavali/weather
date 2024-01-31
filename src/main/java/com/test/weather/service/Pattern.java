package com.test.weather.service;

import java.util.Arrays;

public class Pattern {

    public static void main(String[] args) {
        int rows = 5;
        printPyramidPattern(rows);
        String  str = "ranjeet";
        frequencyOfCharacter(str);
    }

    private static void frequencyOfCharacter(String str) {
        int[] arr = new int[26];
        Arrays.fill(arr,0);

        for(int i = 0 ; i < str.length() ; i++) {
            int index = str.toLowerCase().charAt(i)-'a';
            arr[index]++;
        }

        for(char c = 'a'; c <= 'z' ; c++) {
            if(arr[c-'a'] > 0) {
                System.out.println(" frequency of character " + c + " is " + arr[c - 'a']);
            }
        }
    }

    private static void printPyramidPattern(int rows) {

        for(int i = rows ; i > 0 ; i--) {

            for(int j = 0; j < i ; j++) {
                System.out.print(" ");
            }

            for(int k = 0 ; k <= rows - i ; k++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }
}
