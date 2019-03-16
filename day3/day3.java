package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day3 {
    private static final String input = "/Users/WillyLampert/Desktop/AdventOfCode/inputs/day3input.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader ( new FileReader(input));
        int[][] grid = new int[1000][1000];
        Claim[] claims = new Claim[1500];

        String input;
        int id = 0;
        while((input = in.readLine()) != null) {
            //#1 @ 265,241: 16x26
            //Need to parse it by splitting it up at non digits, starting at index 5 and iterating through
            id++;
            Claim claim = new Claim(id);

            String digits = "";
            int count = 0;
            for(int i = 5; i<input.length(); i++) {
                Character c = input.charAt(i);

                if(Character.isDigit(c)) {
                    digits += c;
                }
                else if(digits.length() > 0){
                    int x = Integer.parseInt(digits);
                    digits = "";
                    count++;
                    if(count == 1) {
                        claim.leftIndent = x;
                    }
                    else if(count == 2) {
                        claim.topIndent = x;
                    }
                    else if(count == 3) {
                        claim.width = x;
                    }
                    else {
                        System.exit(1);
                    }
                }
                //Once you get to end of the input, the last number you get is the height
                if(i == input.length()-1) {
                    claim.height = Integer.parseInt(digits);
                }
            }

            claims[id-1] = claim;
        }

        for(Claim claim: claims) {
            if(claim == null) {
                break;
            }
            for (int x = claim.leftIndent; x < claim.leftIndent + claim.width; x++) {
                for (int y = claim.topIndent; y < claim.topIndent + claim.height; y++) {
                    grid[x][y]++;
                }
            }
        }

        int overlap = 0;
        for(int j = 0; j<1000; j++) {
            for(int k = 0; k<1000; k++) {
                if(grid[j][k] > 1) {
                    overlap++;
                }
            }
        }

        System.out.println(overlap);

        for(Claim claim: claims) {
            if(claim == null) {
                break;
            }
            boolean overlapBoolean = false;
            for (int x = claim.leftIndent; x < claim.leftIndent + claim.width; x++) {
                for (int y = claim.topIndent; y < claim.topIndent + claim.height; y++) {
                    if(grid[x][y] > 1) {
                        overlapBoolean = true;
                    }
                }
            }
            if(!overlapBoolean) {
                System.out.println(claim.ID);
            }
        }

    }

}
