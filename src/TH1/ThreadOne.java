package TH1;

import java.util.Queue;

public class ThreadOne extends Thread {
    private Student student;

    public ThreadOne(Student student) {
        this.student = student;
    }

    @Override
    public void run() {
        Queue<Student> queue = XFile.readFile();
        queue.offer(student);

        XFile.writeFile(queue);
    }
}
