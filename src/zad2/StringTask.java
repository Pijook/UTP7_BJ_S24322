package zad2;

import com.sun.org.apache.regexp.internal.RE;

public class StringTask implements Runnable {

    private String text;
    private String result;
    private int amount;
    private TaskState state;
    private boolean done;

    public StringTask(String text, int amount) {
        this.text = text;
        this.amount = amount;

        this.result = null;
        this.state = TaskState.CREATED;
        this.done = false;
    }

    public void start(){
        Thread thread = new Thread(
                () -> {
                    state = TaskState.RUNNING;
                    StringTask.this.run();
                }
        );

        thread.start();
    }

    public void abort(){
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
            if(state == TaskState.ABORTED){
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
