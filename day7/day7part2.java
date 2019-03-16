package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Vector;

public class day7part2 {

    private static final String input = "/Users/WillyLampert/Desktop/AdventOfCode/inputs/day7input.txt";

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader in = new BufferedReader(new FileReader(input));
        HashMap<Character, Instruction> hashMap = new HashMap<Character, Instruction>();

        String input;

        //Create a hashmap of all of the Instruction for the alphabet
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] alphabet2 = {'A', 'B', 'C', 'D', 'E', 'F'};


        for (char c : alphabet) {
            Instruction temp = new Instruction(c);
            hashMap.put(c, temp);
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



        //Part 2

        //Create 5 Workers
        //Loop through every second
        Vector<Worker> list = new Vector<Worker>();
        for(int i = 0; i<5; i++) {
            Worker worker = new Worker(i);
            list.add(worker);
        }


        boolean b = true;
        int second = 0;
        int count = 0;
        int numWorking = 0;
        while(b) {
            for(Worker worker: list) {
                if(worker.isWorking) {
                    worker.timeRemaining--;
                    if(worker.timeRemaining == 0) {
                        worker.isWorking = false;
                        worker.instruction.hasBeenCompleted = true;
                        System.out.print(worker.instruction.name);
                        count++;
                        if(count == 26) {
                            b = false;
                            break;
                        }
                        for(char character2: worker.instruction.next) {
                            Instruction temp2 = hashMap.get(character2);
                            temp2.removePrevious(worker.instruction.name);
                        }
                    }
                    else {
                        numWorking++;
                    }
                }
            }

            if(numWorking < 5) {
                for (int i = 0; i < 26; i++) {
                    char character = alphabet[i];
                    Instruction temp1 = hashMap.get(character);
                    if (temp1.hasBeenCompleted || temp1.isBeingCompleted) {
                    }
                    else if (temp1.canBeCompleted()) {
                        for (Worker worker : list) {
                            if (!worker.isWorking) {
                                temp1.isBeingCompleted = true;
                                worker.instruction = temp1;
                                worker.isWorking = true;
                                numWorking++;
                                worker.timeRemaining += 60 + i + 1;
                                break;
                            }
                        }
                    }
                    if(numWorking == 5) {
                        break;
                    }
                }
            }
            numWorking = 0;
            if(second%10000 == 9999) {
                System.out.println(second);
                System.exit(1);
            }
            second++;
        }

        System.out.println();
        System.out.println(second - 1);

    }

}
