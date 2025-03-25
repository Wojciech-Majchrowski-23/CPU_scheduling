public class Process {

    private int id, arrivalTime, executionTime, waitingTime = 0, startedExecutingTime = 0;
    private int estimatedExecutingTime;
    private boolean active = false, finished = false, shown = false;


    public Process(int id, int arrivalTime, int executionTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.executionTime = executionTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public int getStartedExecutingTime() {
        return startedExecutingTime;
    }

    public void setStartedExecutingTime(int startedExecutingTime) {
        this.startedExecutingTime = startedExecutingTime;
    }

    public int getEstimatedExecutingTime() {
        return estimatedExecutingTime;
    }

    public void setEstimatedExecutingTime(int estimatedExecutingTime) {this.estimatedExecutingTime = estimatedExecutingTime;}
}
