package day7;

import java.util.Vector;

public class Instruction {
    public char name;
    public Vector<Character> next;
    public Vector<Character> previous;
    public boolean hasBeenCompleted;
    public boolean isBeingCompleted;

    public Instruction(char c) {
        this.name = c;
        next = new Vector<>();
        previous = new Vector<>();
    }

    public void addNext(char c) {
        next.add(c);
    }

    public void addPrevious(char c) {
        previous.add(c);
    }

    public void removePrevious(char c) {
        previous.removeElement(c);
    }

    public boolean canBeCompleted() {
        if(previous.size() == 0) {
            return true;
        }
        return false;
    }

}
