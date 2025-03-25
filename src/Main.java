import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int numberOfDataSets = 5;

        ArrayList<Process>[] processChains = new ArrayList[numberOfDataSets];

        for(int i = 0; i < numberOfDataSets; i++){
            processChains[i] = ProcessesGenerator.generateProcesses(10, 50, 5);
        }

        CPU cpu = new CPU();

        for(int i = 0; i < numberOfDataSets; i++){
            //System.out.println(cpu.firstComeFirstServed(processChains[i]));
            //System.out.println(cpu.shortestJobFirst(processChains[i]));
            System.out.println(cpu.roundRobin(processChains[i], 3));
            //processes aren't sorted by arrival time because some of the later processes could be shorter
        }

    }
}