
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] stone = new int[n + 1];
        int[] path = new int[n + 1];
        Queue<Integer> q = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            stone[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()); // 시작
        int e = Integer.parseInt(st.nextToken()); // 끝

        q.add(s);
        path[s] = 1;

        while (!q.isEmpty()) {
            int idx = q.poll();
            // 오른쪽
            for (int step = idx; step <= n; step += stone[idx]) {
                // int step = idx + step;
                if (step <= n && path[step] == 0) {
                    path[step] = path[idx] + 1;
                    q.add(step);
                }
            }
            // 왼쪽
            for (int step = idx; step > 0; step -= stone[idx]) {
                // int step = idx - step;
                if (step > 0 && path[step] == 0) {
                    path[step] = path[idx] + 1;
                    q.add(step);
                }
            }
        }

        System.out.println(path[e] == 0 ? -1 : path[e] - 1);
    }
}
