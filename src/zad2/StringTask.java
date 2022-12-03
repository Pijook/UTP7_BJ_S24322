package zad2;

public class StringTask implements Runnable {

    private String text;
    private volatile String result;
    private int amount;
    private volatile TaskState state;
    private volatile boolean done;

    private Thread thread;

    public StringTask(String text, int amount) {
        this.text = text;
        this.amount = amount;

        this.result = null;
        this.state = TaskState.CREATED;
        this.done = false;
    }

    public void start(){
        thread = new Thread(
                () -> {
                    state = TaskState.RUNNING;
                    StringTask.this.run();
                }
        );

        thread.start();
    }

    public void abort(){
        thread.interrupt();
        state = TaskState.ABORTED;
        done = true;
    }

    public TaskState getState() {
        return state;
    }

    public boolean isDone() {
        return done;
    }

    public String getResult() {
        return result;
    }

    @Override
    public void run() {
        result = "";
        for(int i = 0; i < amount; i++){
            if(Thread.interrupted()){
                break;
            }

            result = result + text;
        }

        if(state != TaskState.ABORTED){
            done = true;
            state = TaskState.READY;
        }
    }
}
