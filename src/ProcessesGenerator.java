import java.util.ArrayList;

public class ProcessesGenerator {

    public ProcessesGenerator() {}

    public static ArrayList<Process> generateProcesses(int numberOfProcesses,int maxArrivalTime, int maxExecutionTime) {
        ArrayList<Process> processes = new ArrayList<>();

        int executionTime, arrivalTime;

        for(int i = 0; i < numberOfProcesses; i++){
            arrivalTime = (int)(Math.random() * maxArrivalTime);
            executionTime = (int)(Math.random() * maxExecutionTime) + 5;
            processes.add(new Process(i, arrivalTime, executionTime));
        }

        return processes;

    }

}
