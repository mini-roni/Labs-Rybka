import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Lab2_Exception {

    static String S;
    static boolean bool;
    static String [] SS;

    public static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."+
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."+
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."+
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";


    public static void main(String[] args) {
        while (true) {
            JDialog myframe2 = new JDialog();
            myframe2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            myframe2.setAlwaysOnTop(true);
            String S2 = JOptionPane.showInputDialog(myframe2, "Enter your data to identify IP address:");
            try {
                bool = validate(S2);
                if (bool == true) {
                    JOptionPane.showMessageDialog(null, "It's an IP address");
                    break;
                } else {
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
}