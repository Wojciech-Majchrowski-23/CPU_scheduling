import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Process> processChain = ProcessesGenerator.generateProcesses(30, 300, 15);

        CPU cpu = new CPU(processChain);
        System.out.println(cpu.FirstComeFirstServed());

    }
}