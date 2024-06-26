import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Mian {
    public static long startTime;
    public static void main(String[] args) throws IOException {


        JFrame f=new JFrame("Calculate prime number");
        JLabel l1,l2,l3;
        l1=new JLabel("Enter N : ");
        l1.setBounds(30,20, 100,30);
        final JTextField tf=new JTextField();
        tf.setBounds(150,25, 150,20);
        l2=new JLabel("Enter File Name only(without .txt) : ");
        l2.setBounds(30,70, 100,30);
        final JTextField tf2=new JTextField();
        tf2.setBounds(150,75, 150,20);
        l3=new JLabel("Enter Buffer size  : ");
        l3.setBounds(30,120, 120,30);
        final JTextField tf3=new JTextField();
        tf3.setBounds(150,125, 150,20);
        JButton b=new JButton("Start Producer");
        b.setBounds(30,170,150,20);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int buf = (Integer.valueOf(tf3.getText()));
                long startTime = System.currentTimeMillis();
                int n = Integer.valueOf(tf.getText());
                String file = String.valueOf(tf2.getText());


                ////////////////////////////
                Queue<Integer> buffer = new LinkedList<>();
                Producer P1 = new Producer(buf, buffer, n);
                Consumer C1 = null;
                try {
                    C1 = new Consumer(buffer, file, n);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                startTime = System.currentTimeMillis();
                P1.start();
                C1.start();
            }});

                f.add(tf);
                f.add(tf2);
                f.add(tf3);
                f.add(b);
                f.add(l1);
                f.add(l2);
                f.add(l3);
                f.setSize(350,300);
                f.setLayout(null);
                f.setVisible(true);
                f.setLocationRelativeTo(null);
                f.setResizable(false);




        }
    }
