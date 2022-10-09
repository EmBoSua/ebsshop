package TH1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //test
        chooseFunction();
        chooseFunction();
    }

    private static void chooseFunction() {
        boolean loop = true;
        ThreadTwo threadTwo = new ThreadTwo();

        while (loop) {
            System.out.println("\n=================================================\n");
            System.out.println("1.  Them");
            System.out.println("2.  Hien");
            System.out.println("3.  Thoat");
            System.out.println("\n=================================================");
            System.out.print("Chon: ");
            Byte choose = null;
            try {
                choose = scanner.nextByte();
            } catch (InputMismatchException e) {
                System.err.println("Kieu du lieu khong phu hop !.");
            }
            scanner.nextLine();

            switch (choose) {
                case 1:
                    Student student = enterStudent();
                    ThreadOne threadOne = new ThreadOne(student);
                    threadOne.start();
                    break;
                case 2:
                    System.out.println(threadTwo.getState());
                    if (threadTwo.getState() == Thread.State.RUNNABLE
                            || threadTwo.getState() == Thread.State.TIMED_WAITING) {
                        System.out.printf("Da chay roi !.");
                    } else {
                        threadTwo.start();
                    }
                    break;
                case 3:
                    loop = false;
                    threadTwo.DestroyNow();
                    break;
                default:
                    System.err.println("Chuc nang khong phu hop !.");
            }
        }
    }

    private static Student enterStudent() {
        System.out.print("Nhap ten: ");
        String name = scanner.nextLine();
        System.out.print("Nhap tuoi: ");
        byte age = enterAge();
        System.out.print("Nhap gioi tinh: ");
        String sex = scanner.nextLine();

        Student std = new Student(name, age, sex);
        return std;
    }

    private static byte enterAge() {
        String input;
        while (true) {
            input = scanner.nextLine();
            try {
                byte num = Byte.parseByte(input);
                if (num < 0 || num > 10)
                    throw new NumberFormatException();
                else
                    return num;
            } catch (NumberFormatException e) {
                System.err.println("Du lieu nhap KHONG DUNG (18-100)");
            }
        }
    }
}
