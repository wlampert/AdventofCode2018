package day7;


import com.sun.corba.se.impl.protocol.INSServerRequestDispatcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;


public class day7 {
    private static final String input = "/Users/WillyLampert/Desktop/AdventOfCode/inputs/day7input.txt";

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader in = new BufferedReader(new FileReader(input));
        HashMap<Character, Instruction> hashMap = new HashMap<Character, Instruction>();

        String input;

        //Create a hashmap of all of the Instructiont for the alphabet
        char[] alphabet = {'A',  'B', 'C',  'D','E', 'F', 'G','H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] alphabet2 = {'A',  'B', 'C',  'D','E', 'F'};


        for(char c: alphabet) {
            Instruction temp = new Instruction(c);
            hashMap.put(c,temp);
        }


        //Read in the input and edit the instruction objects
        //Step W must be finished before step B can begin.
        //     5                              36
        //Add next to first, add previous for second
        while ((input = in.readLine()) != null) {
            char first = input.charAt(5);
            char second = input.charAt(36);
            Instruction temp1 = hashMap.get(first);
            Instruction temp2 = hashMap.get(second);
            temp1.addNext(second);
            temp2.addPrevious(first);
        }

        System.out.println("Done!");

        //Now go through every letter and complete them if possible and remove previous for ones that require it
        //Loop through until every letter is complete
        boolean b = true;
        int count = 0;
        while(b) {
            for (char character : alphabet) {
                Instruction temp1 = hashMap.get(character);
                if(temp1.hasBeenCompleted) {
                }
                else if (temp1.canBeCompleted()) {
                    System.out.print(character);
                    temp1.hasBeenCompleted = true;
                    count++;
                    if(count == 26) {
                        b = false;
                        break;
                    }
                    for(char character2: temp1.next) {
                        Instruction temp2 = hashMap.get(character2);
                        temp2.previous.removeElement(character);
                    }
                    break;
                }

            }
        }

    }
}
