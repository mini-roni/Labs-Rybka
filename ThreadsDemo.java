import javax.swing.*;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

class Lab1 implements Runnable{
    Thread thread;
    private String threadname;

    Lab1(String name){
        threadname = name;
    }

    static String S;
    static int num;
    static String[] SS;
    static boolean bool;
    static String finalText = "";

    public synchronized void Lab1() {
        PrintWriter out = new PrintWriter(System.out);

        S = JOptionPane.showInputDialog(null,"Enter your text to find palindromes:");
        notifyAll();
        System.out.println("0");


        while (true) {
            S = S.replaceAll(",", "");
            S = S.replaceAll("\\.", "");
            S = S.replaceAll(";", "");
            S = S.replaceAll(":", "");
            S = S.toLowerCase();
            SS = S.split("\\s");
            Stack stack1 = new Stack();

            try {

                if (SS[0] == null || SS[0].isEmpty()) {
                    num = 1;
                    throw new RuntimeException("UNEXPECTED VALUES: The program cannot take in an empty String or null value\n");
                } else {
                    System.out.println("1");
                    for (int i = 0; i < SS.length; i++) {

                        for (int j = i + 1; j < SS.length; j++) {
                            bool = solve(SS[i], SS[j]);
                            String val = SS[i] + " " + SS[j];
                            stack1.push(val);
                            if (bool) {
                                num++;
                            }
                        }
                        while (!stack1.empty())
                            finalText += stack1.pop();
                    }
                    if (num == 0) {
                        notifyAll();
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            System.out.println("Thread has been interrupted");
                        }
                        JOptionPane.showMessageDialog(null,"No such words, sorry bae...");
                    } else {
                        System.out.println("3");
                        notifyAll();
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            System.out.println("Thread has been interrupted");
                        }
                        JOptionPane.showMessageDialog(null,"Results:\n" + finalText + "\t");
                        notifyAll();
                    }
                    break;

                }
            } catch (RuntimeException exc) {
                System.out.println(exc.getMessage());
                System.out.println("Enter your text again:\n");
            }
        }
        notifyAll();
    }

    public static boolean solve(String S, String S1) {
        StringBuilder SS = new StringBuilder(S);
        SS = SS.reverse();
        return S1.equals(SS.toString());
    }

    public void run(){
        System.out.println("Thread running" + threadname);
        Lab1();
    }

    public void start(){
        System.out.println("Thread started");
        if(thread == null){
            thread = new Thread(this, threadname);
            thread.start();
        }
    }
}

class Lab2 implements Runnable{
    Thread thread;
    private String threadname;

    Lab2(String name){
        threadname = name;
    }

    static String S2;
    static int num;
    static String[] SS;
    static boolean bool;

    public static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."+
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."+
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."+
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public synchronized void Lab2(){
        System.out.println("2");
        while (true) {
            JDialog myframe2 = new JDialog();
            myframe2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            myframe2.setAlwaysOnTop(true);
            System.out.println("4");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
            String S2 = JOptionPane.showInputDialog(myframe2,"Enter your data to identify IP address:");
            try {
                bool = validate(S2);
                if (bool == true) {
                    notifyAll();
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Thread has been interrupted");
                    }
                    JOptionPane.showMessageDialog(null, "It's an IP address");
                    break;
                } else {
                    notifyAll();
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Thread has been interrupted");
                    }
                    JOptionPane.showMessageDialog(null, "Nah, that's not an IP address");
                    break;
                }
            } catch (RuntimeException exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }
        }
    }

    public static boolean validate(String ip){
        if(ip == null || ip.isEmpty()){
            throw new RuntimeException("The program cannot take in an empty String or null value");

        } else {
            Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();

        }
    }

    public void run() {
        System.out.println("Thread running" + threadname);
        Lab2();
    }

    public void start(){
        System.out.println("Thread started");
        if(thread == null){
            thread = new Thread(this, threadname);
            thread.start();
        }
    }
}

class Lab3 implements Runnable {
    Thread thread;
    private String threadname;

    Lab3(String name){
        threadname = name;
    }

    static String S;
    static int num;
    static String[] SS;
    static boolean bool;

    public synchronized void Lab3 (){
        while (true) {
            JDialog myframe = new JDialog();
            myframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            myframe.setAlwaysOnTop(true);
            String word3 = JOptionPane.showInputDialog(myframe,"Enter symbols to write words backwards:");
            Stack stack3 = new Stack();
            String[] sentences = word3.split("[-/%,.!?()]");

            try {
                if (sentences[0] == null || sentences[0].isEmpty() || sentences[0] == "") {
                    throw new RuntimeException("UNEXPECTED VALUES: The program cannot take in an empty String or null value\n");
                } else {

                    for (int i = 0; i < sentences.length; i++) {
                        char array[] = sentences[i].toCharArray();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            System.out.println("Thread has been interrupted");
                        }

                        for (int j = 0; j < array.length; j++) {
                            char val = array[j];
                            stack3.push(val);
                        }

                        String finalText = "";
                        while (!stack3.empty())
                            finalText += stack3.pop();
                        notifyAll();
                        JOptionPane.showMessageDialog(null,"Results backwards:\n" + finalText + "\t");
                    }
                    break;
                }

            } catch (RuntimeException exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }
        }

    }

    public void run() {
        System.out.println("Thread running" + threadname);
        Lab3();
    }

    public void start(){
        System.out.println("Thread started");
        if(thread == null){
            thread = new Thread(this, threadname);
            thread.start();
        }
    }

}

public class ThreadsDemo {

    public static void main(String[] args) {
        Lab1 thread1 = new Lab1("Thread1");
        thread1.start();
        Lab2 thread2 = new Lab2("Thread2");
        thread2.start();
        //Lab3 thread3 = new Lab3("Thread3");
        //thread3.start();
    }
}
