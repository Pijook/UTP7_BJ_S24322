package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters {

    private String letters;
    private List<Thread> threads;

    public Letters(String letters) {
        this.letters = letters;
        this.threads = new ArrayList<>();

        for(int i = 0; i < letters.length(); i++){
            String singleLetter = String.valueOf(letters.charAt(i));
            Thread thread = new Thread(() -> {
                while(!Thread.interrupted()){
                    try {
                        System.out.print(singleLetter);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            thread.setName("Thread " + singleLetter);
            threads.add(thread);
        }
    }

    public List<Thread> getThreads() {
        return threads;
    }
}
