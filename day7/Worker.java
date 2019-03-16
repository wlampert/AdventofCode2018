package day7;

public class Worker {
    public int workerNumber;
    public int timeRemaining;
    public boolean isWorking;
    public Instruction instruction;

    public Worker(int x) {
        workerNumber = x;
        timeRemaining = 0;
        isWorking = false;
    }
}
