import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        ArrayList<Process> processChain = ProcessesGenerator.generateProcesses(20, 100, 15);

//        processChain.sort(Comparator.comparingInt(p -> p.getArrivalTime()));
//        Queue<Process> processesQueue = new LinkedList<>();
//        processesQueue.addAll(processChain);
//
//        while (!processesQueue.isEmpty()) {
//            Process process = processesQueue.poll();
//            System.out.printf("%-7s %-10s %n", "[P_"+process.getId()+"]","[Arrival_Time: " + process.getArrivalTime() + "]");
//        }

        CPU cpu = new CPU(processChain);
        System.out.println(cpu.FirstComeFirstServed());

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("Czas wykonania: " + duration / 1_000_000.0 + " ms");

    }
}