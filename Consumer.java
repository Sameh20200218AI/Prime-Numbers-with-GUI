import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {

    JFrame f2 = new JFrame("output");
    JLabel o1, o2, o3, o4, o5, o6, o7;
    String fileName;
    Queue<Integer> buffer;
    BufferedWriter writer;
    int N;

    Consumer(Queue<Integer> buffer, String file, int N) throws IOException {
        this.buffer = buffer;
        this.fileName = file;
        this.N = N;
        writer = new BufferedWriter(new FileWriter(fileName));
    }

    @Override
    public void run() {
        o1 = new JLabel("The Largest Prime Number : ");
        o1.setBounds(30, 20, 300, 30);
        o2 = new JLabel();
        o2.setBounds(310, 20, 40, 30);
        o3 = new JLabel("# of element (Prime Number) generated : ");
        o3.setBounds(30, 60, 300, 30);
        o4 = new JLabel();
        o4.setBounds(310, 60, 40, 30);

        o5 = new JLabel("Time elapsed since the start of processing :  ");
        o5.setBounds(30, 90, 300, 30);
        o6 = new JLabel();
        o6.setBounds(310, 90, 200, 30);
        JButton b = new JButton("Finish program");
        b.setBounds(30, 125, 150, 30);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        f2.add(o1);
        f2.add(o2);
        f2.add(o3);
        f2.add(o4);
        f2.add(o5);
        f2.add(o6);
        f2.add(b);
        f2.setSize(600, 230);
        f2.setLayout(null);
        f2.setVisible(true);
        try {
            TimeUnit.MICROSECONDS.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
        int counter = 0;
        long endTime;
        while (Producer.prime <= N) {
            synchronized (buffer) {
                while (buffer.size() > 0) {
                    try {
                        while (!buffer.isEmpty()) {
                            String largest = Integer.toString(buffer.poll());
                            writer.write("\"" + largest + "\" ,");
                            endTime = System.currentTimeMillis();

                            o2.setText(Integer.toString(Integer.parseInt(largest)));

                            o4.setText(Integer.toString(++counter));

                            long ti = 0;
                            String tim = endTime - Mian.startTime + " ms";
                            o6.setText(tim);

                            if (N == 0) {
                                o2.setText("there is not prime number(empty)");
                                o4.setText(Integer.toString(0));
                                ti = 0;
                                tim = endTime - Mian.startTime + " ms";
                                o6.setText(tim);

                            }


                        }

                        // writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            TimeUnit.MICROSECONDS.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}