package TH1;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class XFile {
    private static String path = "src/TH1/data.txt";
    private static File file = new File(path);

    public synchronized static void writeFile(Queue queue) {
        if (queue != null) {
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;

            try {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);

                oos.writeObject(queue);
                oos.flush();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error when write file: " + e.getMessage());
            } finally {
                streamClose(oos);
                streamClose(fos);
            }
        }
    }

    public synchronized static Queue<Student> readFile() {
        Queue<Student> queue = null;

        if (file.length() != 0) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;

            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);

                queue = (Queue<Student>) ois.readObject();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error when read file: " + e.getMessage());
            } finally {
                streamClose(ois);
                streamClose(fis);
            }
        }

        if (queue == null)
            return new ArrayDeque<>();

        return queue;
    }

    private static void streamClose(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                System.err.println("Error when close file: " + e.getMessage());
            }
        }
    }

    private static void streamClose(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                System.err.println("Error when close file: " + e.getMessage());
            }
        }
    }
}
