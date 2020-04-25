import java.util.Stack;
import java.util.*;
import javax.swing.*;

public class Lab3_Exceptions {
    public static void main(String[] args) {

        while (true) {
            String word = JOptionPane.showInputDialog("Enter symbols");
            Stack s = new Stack();

            String[] sentences = word.split("[-/%,.!?()]");

            try {
                if (sentences[0] == null || sentences[0].isEmpty() || sentences[0] == "") {
                    throw new RuntimeException("UNEXPECTED VALUES: The program cannot take in an empty String or null value\n");
                } else {

                    for (int i = 0; i < sentences.length; i++) {
                        char array[] = sentences[i].toCharArray();

                        for (int j = 0; j < array.length; j++) {
                            char val = array[j];
                            s.push(val);
                        }

                        String finalText = "";
                        while (!s.empty())
                            finalText += s.pop();
                        System.out.print(finalText + "\t");
                    }
                    break;
                }

            } catch (RuntimeException exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }
        }
    }
}