import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int numberOfDataSets = 10;

        ArrayList<Process>[] processChains = new ArrayList[numberOfDataSets];

        for(int i = 0; i < numberOfDataSets; i++){
            processChains[i] = ProcessesGenerator.generateProcesses(5000, 5000, 50);
        }

        CPU cpu = new CPU();

        for(int i = 0; i < numberOfDataSets; i++){

            System.out.println("[Data_number: " + i + " ]\n");

            System.out.printf("%15s %n",cpu.firstComeFirstServed(deepCopy(processChains[i])) + "]");
            System.out.printf("%15s %n",cpu.shortestJobFirst(deepCopy(processChains[i]))+ "]");
            System.out.printf("%15s %n",cpu.roundRobin(deepCopy(processChains[i]), 3)+ "]");

            System.out.println();
        }
    }

    public static ArrayList<Process> deepCopy(ArrayList<Process> original) {

        ArrayList<Process> copy = new ArrayList<>();

        for (Process p : original) {
            copy.add(p.clone());
        }
        return copy;
    }
}