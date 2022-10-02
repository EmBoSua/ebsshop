package TH1;

import java.util.Queue;

public class ThreadTwo extends Thread {
    private boolean destroyNow = false;

    public void DestroyNow(){
        destroyNow = true;
    }

    @Override
    public void run() {
        while (!destroyNow) {
            showData();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void showData() {
        Queue<Student> queue = XFile.readFile();
        System.out.println("\n+++++++++++++++++++++++++++++\n");
        for (Student std : queue) {
            System.out.println(std);
        }
        System.out.println("\n+++++++++++++++++++++++++++++");
    }
}
