import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] node = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            node[i] = new ArrayList<>();
        }
        int[] dp = new int[n + 1];
        int[] line = new int[n + 1];
        int[] time = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            String[] str = br.readLine().split(" ");
            time[i] = Integer.parseInt(str[0]);

            for (int j = 1; j < str.length; j++) {
                if (!str[j].equals("-1")) {
                    int idx = Integer.parseInt(str[j]);
                    node[idx].add(i);
                    line[i] += 1;
                }
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (line[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int idx = q.poll();

            for (int nextIdx : node[idx]) {
                line[nextIdx] -= 1;
                dp[nextIdx] = Math.max(dp[nextIdx], dp[idx] + time[idx]);
                if (line[nextIdx] == 0) {

                    q.add(nextIdx);
                }
            }
        }
        for (int i = 0; i < n + 1; i++) {
            dp[i] += time[i];
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(dp[i]);
        }
    }
}
