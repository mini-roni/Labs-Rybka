import javax.swing.*;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.util.Stack;


public class Lab1_Exceptions {

    static String S;
    static int num;
    static String[] SS;
    static boolean bool;
    static String finalText = "";


    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);

        S = JOptionPane.showInputDialog(null,"Enter your text to find palindromes:");
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
                        JOptionPane.showMessageDialog(null,"No such words, sorry bae...");
                    } else {
                        JOptionPane.showMessageDialog(null,"Results:\n" + finalText + "\t");

                    }
                    break;

                }
            } catch (RuntimeException exc) {
                System.out.println(exc.getMessage());
                System.out.println("Enter your text again:\n");
            }
        }
    }

    public static boolean solve(String S, String S1){
            StringBuilder SS = new StringBuilder(S);
            SS = SS.reverse();
            return S1.equals(SS.toString());
    }

    }
