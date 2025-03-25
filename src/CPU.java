import java.util.*;

public class CPU {

    private int cpuTime = -1;

    public CPU() {}

    public double firstComeFirstServed(ArrayList<Process> processes) {

        Queue<Process> cpuQueue = new LinkedList<>();
        System.out.printf("%-30s", "[First_Come_First_Served]");
        return processingForFCFSandSJF(processes, cpuQueue);
    }

    public double shortestJobFirst(ArrayList<Process> processes) {

        Queue<Process> cpuQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getExecutionTime));
        System.out.printf("%-30s","[Shortest_Job_First]");
        return processingForFCFSandSJF(processes, cpuQueue);
    }

    public double roundRobin(ArrayList<Process> processes, int quantumOfTime) {

        System.out.printf("%-30s", "[Round_Robin]");

        Queue<Process> cpuQueue = new LinkedList<>();

        int numberOfSwitchesBetweenProcesses = 0;
        double averageProcessWaitingTime = 0;
        int completedProcesses = 0;

        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        int index = 0;

        while (completedProcesses < processes.size()) {
            while (index < processes.size() && processes.get(index).getArrivalTime() <= cpuTime) {
                cpuQueue.add(processes.get(index));
                index++;
            }

            if (cpuQueue.isEmpty()) {
                cpuTime++;
                continue;
            }

            Process current = cpuQueue.poll();

            if (!current.isActive()) {
                current.setStartedExecutingTime(cpuTime);
                current.setEstimatedExecutingTime(current.getExecutionTime());
                current.setActive(true);
            }

            int executionTime = Math.min(quantumOfTime, current.getExecutionTime());
            cpuTime += executionTime;
            current.setExecutionTime(current.getExecutionTime() - executionTime);

            if (current.getExecutionTime() == 0) {
                current.setFinished(true);
                current.setWaitingTime(current.getStartedExecutingTime() - current.getArrivalTime());
                averageProcessWaitingTime += current.getWaitingTime();
                completedProcesses++;
//                System.out.printf("%-10s %-25s %-25s %-33s %-33s %-30s %-25s %n",
//                        "[P_" + current.getId() + "]",
//                        "[Arrival_Time: " + current.getArrivalTime() + "]",
//                        "[Start_executing: " + current.getStartedExecutingTime() + "]",
//                        "[Finished_executing: " + cpuTime + "]",
//                        "[Estimated_executing_Time: " + current.getEstimatedExecutingTime() + "]",
//                        "[Real_executing_Time: " + (cpuTime - current.getStartedExecutingTime()) + "]",
//                        "[Waiting_Time: " + current.getWaitingTime() + "]");
            } else {
                cpuQueue.add(current);
                numberOfSwitchesBetweenProcesses++;
            }
        }

        System.out.printf("%-66s %5s", "[Switches_Between_Branches: " + numberOfSwitchesBetweenProcesses + "]", "[Average_Waiting_Time: ");
        cpuTime = 0;
        return averageProcessWaitingTime / processes.size();
    }


    public double processingForFCFSandSJF(ArrayList<Process> processes, Queue<Process> cpuQueue){

        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        Queue<Process> processesQueue = new LinkedList<>(processes);

        int numberOfSwitchesBetweenProcesses = 0;
        double averageProcessWaitingTime = 0;

        cpuUpdate(processesQueue, cpuQueue);

        while(!processesQueue.isEmpty() || !cpuQueue.isEmpty()) {

            if (!cpuQueue.isEmpty()) {

                Process current = cpuQueue.poll();
                if (current == null) continue;
                numberOfSwitchesBetweenProcesses++;
                current.setActive(true);

                int executionTime = current.getExecutionTime();
                current.setStartedExecutingTime(cpuTime);

                while (executionTime > 0) {
                    executionTime-=1;
                    cpuUpdate(processesQueue, cpuQueue);
                }
                current.setWaitingTime(current.getStartedExecutingTime() - current.getArrivalTime());
                averageProcessWaitingTime += current.getWaitingTime();
//
//                System.out.printf("%-10s %-25s %-25s %-33s %-25s %-25s %n",
//                        "[P_" + current.getId() + "]",
//                        "[Arrival_Time: " + current.getArrivalTime() + "]",
//                        "[Start_executing: " + current.getStartedExecutingTime() + "]",
//                        "[Finished_executing: " + cpuTime + "]",
//                        "[Executing_Time: " + current.getExecutionTime() + "]",
//                        "[Waiting_Time: " + current.getWaitingTime() + "]");
            } else {
                cpuUpdate(processesQueue, cpuQueue);
            }
        }

        averageProcessWaitingTime = averageProcessWaitingTime/processes.size();

        int starvedProcesses = 0;
        for(Process process : processes) {
            if(process.getWaitingTime() > 3*averageProcessWaitingTime) {
                starvedProcesses++;
            }
        }

        System.out.printf("%-35s %-30s %-5s", "[Switches_Between_Branches: " + (numberOfSwitchesBetweenProcesses - 1), "[Starved_Processes: " + starvedProcesses + "]", "[Average_Waiting_Time: ");
        cpuTime = 0;
        return averageProcessWaitingTime;
    }

    public void cpuUpdate(Queue<Process> processesQueue, Queue<Process> cpuQueue) {
        cpuTime++;

        while(!processesQueue.isEmpty() && processesQueue.peek().getArrivalTime() == cpuTime ){
            cpuQueue.add(processesQueue.poll());
        }

        // loop for SJF visualization

//        for(Process process : cpuQueue){
//            System.out.print("[P_" + process.getId() + "] ");
//        }
//        System.out.println();
    }

    public boolean isEveryProcessFinished(ArrayList<Process> processes) {
        for(Process process : processes) {
            if(!process.isFinished()) return false;
        }
        return true;
    }

    public boolean isAnyProcessActive(ArrayList<Process> processes) {
        for(Process process : processes) {
            if(process.isActive()) return true;
        }
        return false;
    }
}
