package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class day1 {

    private static final String input = "/Users/WillyLampert/Desktop/AdventOfCode/inputs/day1input.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader ( new FileReader(input));
        Hashtable<Integer, Integer> table = new Hashtable<>();

        int frequency = 0;
        String next = in.readLine();
        int nextInt;
        while((next != null) ){
            nextInt = Integer.parseInt(next);
            frequency+=nextInt;
            if (table.containsKey(frequency)) {
                System.out.println(frequency);
                System.exit(1);
            }
            else {
                table.put(frequency,frequency);
            }

            next = in.readLine();
            if(next == null) {
                in = new BufferedReader ( new FileReader(input));
                next = in.readLine();
            }

        }


    }
}