import java.util.Queue;
public class Producer extends Thread {
    int bufferSize = 8;
    Queue <Integer> buffer;
    int N;
    static int prime = 2;
    Producer(int bufferSize, Queue<Integer> buffer, int number) {
        this.buffer = buffer;
        this.bufferSize = bufferSize;
        this.N = number;
    }

    @Override
    public void run() {
        boolean[] numbers = new boolean[N + 1];
        numbers[0] = false;
        numbers[1] = false;
        for (int i = 2; i <= N; i++) {
            numbers[i] = true;
        }

        while (prime <= N){
            synchronized (buffer) {
                while (buffer.size() < bufferSize && prime <= N) {
                    if (numbers[prime]) {
                        buffer.add(prime);
                        for (int i = prime; i <= N; i+=prime) {
                            numbers[i] = false;
                        }
                    }
                    prime++;
                }
            }
        }
    }
}