import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class CPU {

    private int cpuTime = -1;
    private Queue<Process> cpuQueue;
    private ArrayList<Process> processes;
    private Queue<Process> processesQueue = new LinkedList<>();

    public CPU(ArrayList<Process> processes) {
        cpuQueue = new LinkedList<>();
        this.processes = processes;
    }

    public double FirstComeFirstServed() {

        //here sort the array of processes
        //you need an iterator which is going to take processes from array
        //if their arrivalTime equals cpuTime

        processes.sort(Comparator.comparingInt(p -> p.getArrivalTime()));
        processesQueue.addAll(processes);

        cpuUpdate();

        while(!processesQueue.isEmpty() || !cpuQueue.isEmpty()) {

            if(!cpuQueue.isEmpty()){

                // .poll - takes the first element and automatically removes it from the first position in queue
                Process process = cpuQueue.poll();
                if (process == null) continue;
                process.setActive(true);

                int executionTime = process.getExecutionTime();
                int startExecuting = cpuTime;

                while(process.getExecutionTime() > 0){
                    process.setExecutionTime(process.getExecutionTime() - 1);//decreasing executionTime
                    cpuUpdate();
                }

                System.out.printf("%-10s %-25s %-25s %-33s %-25s %-25s %n","[P_"+process.getId()+"]","[Arrival_Time: " + process.getArrivalTime() + "]", "[Start_executing: " + startExecuting + "]", "[Finished_executing: " + cpuTime + "]","[Executing_Time: " + executionTime  + "]" ,"[Waiting_Time: " + process.getWaitingTime() + "]");
                process.setFinished(true);
                process.setActive(false);
            }
            else{
                cpuUpdate();
            }
        }

        //summing waitingTime of every process
        double averageProcessWaitingTime = 0;
        for(Process process : processes){
            averageProcessWaitingTime += process.getWaitingTime();
        }
        return averageProcessWaitingTime/processes.size();

    }

    public void cpuUpdate(){
        //"CPU TIK"
        cpuTime++;

        //increasing waiting time for every not active and not finished process which is in the cpuQueue
        if(!cpuQueue.isEmpty()){
            for(Process process : cpuQueue) {
                process.setWaitingTime(process.getWaitingTime() + 1);
            }
        }

        while(!processesQueue.isEmpty() && processesQueue.peek().getArrivalTime() == cpuTime ){
            cpuQueue.add(processesQueue.poll());
        }
    }
}
