import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        ArrayList<Process> processChain = ProcessesGenerator.generateProcesses(5000, 60000, 15);

        CPU cpu = new CPU(processChain);
        System.out.println(cpu.FirstComeFirstServed());

    }
}