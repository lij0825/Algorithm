

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static Deque<String>[] q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        q = new ArrayDeque[n];
        for (int i = 0; i < n; i++) {
            q[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                q[i].add(str[j]);
            }
        }

        String[] L = br.readLine().split(" ");
        Deque<String> Lq = new ArrayDeque();
        for (int i = 0; i < L.length; i++) {
            Lq.add(L[i]);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += q[i].size();
        }

        if (sum != Lq.size()) {
            System.out.println("Impossible");
            System.exit(0);
        }
        for (int i = 0; i < L.length; i++) {
            for (int j = 0; j < n; j++) {
                if (!Lq.isEmpty() && Lq.peekFirst().equals(q[j].peek())) {
                    q[j].pollFirst();
                    Lq.pollFirst();
                }
            }
        }
        if (!Lq.isEmpty()) {
            System.out.println("Impossible");
        } else {
            System.out.println("Possible");
        }
    }
}
