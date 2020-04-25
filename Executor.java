import javax.swing.*;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Executor {
    public static void main(String args[]){
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> f1;
        Future<String> f2;
        Future<String> f3;

        System.out.println("Starting");

        f1 = es.submit(new Lab1());
        f2 = es.submit(new Lab2());
        f3 = es.submit(new Lab3());

        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        catch (ExecutionException exc) {
            System.out.println(exc);
        }

        es.shutdown();
        System.out.println("Done");

    }
}

class Lab1 implements Callable<String>{
    static String S;
    static int num;
    static String [] SS;
    static boolean bool;
    String finalTextlab1 = "";
    boolean boollab1;

    public static boolean solve(String S, String S1){
        StringBuilder SS = new StringBuilder(S);
        SS = SS.reverse();
        return S1.equals(SS.toString());
    }

    @Override
    public String call() throws Exception {
        PrintWriter out = new PrintWriter(System.out);
        while (true) { //shows the enter message until user enters a text
            String S = JOptionPane.showInputDialog(null, "Enter your text to find palindromes:");
            while (S == null) { //shows the enter message again and again if user presses 'cancel'
                JOptionPane.showMessageDialog(null, "ERROR: You must enter you text");
                S = JOptionPane.showInputDialog(null, "Enter your text to find palindromes:");
            }
            S = S.replaceAll(",", "");
            S = S.replaceAll("\\.", "");
            S = S.replaceAll(";", "");
            S = S.replaceAll(":", "");
            S = S.toLowerCase();
            SS = S.split("\\s");
            Stack stack1 = new Stack();

            try {
                if (SS[0] == null || SS[0].isEmpty()) { //throws exception if user entered nothing
                    num = 1;
                    throw new RuntimeException("UNEXPECTED VALUES: The program cannot take in an empty String or null value\n");
                } else {
                    for (int i = 0; i < SS.length; i++) {
                        for (int j = i + 1; j < SS.length; j++) {
                            boollab1 = solve(SS[i], SS[j]); //calling a method for comparing two words
                            if (boollab1) { //if two words are palindromes
                                num++;
                                String val = SS[i] + " " + SS[j] + "\n";
                                stack1.push(val); //putting words in stack
                            }
                        }
                        while (!stack1.empty())
                            finalTextlab1 += stack1.pop(); //making a long text out of all pairs of words that are palindromes
                    }
                    break;
                }
            } catch (RuntimeException exc) {
                System.out.println(exc.getMessage());
                System.out.println("Enter your text again:\n");
            }
        }

        if (num == 0) {
            return ("Results for palindromes:\nNo such words, sorry bae...");
        } else {
            return ("Results for palindromes:\n" + finalTextlab1 + "\t");
        }
    }
}

class Lab2 implements Callable<String>{
    boolean boollab2;
    public static boolean validate(String ip) {
        final String IPADDRESS_PATTERN =
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        if (ip == null || ip.isEmpty()) {
            throw new RuntimeException("UNEXPECTED VALUES: The program cannot take in an empty String or null value");

        } else {
            Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();

        }
    } //validates String, used in Lab2
    @Override
    public String call() throws Exception {
        while (true) { //shows the enter message until user enters a text
            JDialog myframe2 = new JDialog();
            myframe2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            myframe2.setAlwaysOnTop(true);
            String S2 = JOptionPane.showInputDialog(myframe2, "Enter your data to identify IP address:");
            while (S2 == null){ //shows the enter message again and again if user presses 'cancel'
                JOptionPane.showMessageDialog(null, "ERROR: You must enter you text");
                S2 = JOptionPane.showInputDialog(myframe2, "Enter your data to identify IP address:");
            }
            try {
                boollab2 = validate(S2); //calling a method that validates whether a text is an IP
                break;
            } catch (RuntimeException exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }
        }
        if (boollab2 == true) {
            return("Result fo IP:\nEntered text is an IP address");
        } else {
            return ("Result fo IP:\nEntered text is not an IP address");
        }
    }
}

class Lab3 implements Callable<String>{
    String word3;
    String finalTextlab3 = "";
    @Override
    public String call() throws Exception {
        while (true) { //shows the enter message until user enters a text
            JDialog myframe = new JDialog();
            myframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            myframe.setAlwaysOnTop(true);
            word3 = JOptionPane.showInputDialog(myframe,"Enter symbols to write words backwards:");
            while (word3 == null){ //shows the enter message again and again if user presses 'cancel'
                JOptionPane.showMessageDialog(null, "ERROR: You must enter you text");
                word3 = JOptionPane.showInputDialog(null, "Enter symbols to write words backwards:");
            }
            Stack stack3 = new Stack();
            String[] sentences = word3.split("[-/%,.!?()]");

            try {
                if (sentences[0] == null || sentences[0].isEmpty() || sentences[0] == "") {
                    throw new RuntimeException("UNEXPECTED VALUES: The program cannot take in an empty String or null value\n");
                } else {
                    //notify();
                    for (int i = 0; i < sentences.length; i++) {
                        char array[] = sentences[i].toCharArray();

                        for (int j = 0; j < array.length; j++) {
                            char val = array[j];
                            stack3.push(val);
                        }
                        while (!stack3.empty())
                            finalTextlab3 += stack3.pop();
                    }
                    break;
                }

            } catch (RuntimeException exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }
        }
        return ("Results backwards:\n" + finalTextlab3 + "\t");
    }
}
