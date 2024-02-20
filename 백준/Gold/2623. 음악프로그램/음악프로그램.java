import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 가수 수
        int m = Integer.parseInt(st.nextToken()); // pd수
        ArrayList[] singers = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            singers[i] = new ArrayList<>();
        }
        int[] line = new int[n + 1]; // 간선수
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int singer = Integer.parseInt(st.nextToken());
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < singer; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            for (int j = 0; j < list.size() - 1; j++) {
                singers[list.get(j)].add(list.get(j + 1));
                line[list.get(j + 1)] += 1;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (line[i] == 0) {
                q.add(i);
                visited[i] = true;
            }
        }
        while (!q.isEmpty()) {
            int idx = q.poll();
            sb.append(idx).append("\n");
            for (int i = 0; i < singers[idx].size(); i++) {
                line[(int) singers[idx].get(i)] -= 1;
            }
            for (int i = 1; i <= n; i++) {
                if (line[i] == 0 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < line.length; i++) {
            sum += line[i];
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sum != 0 ? 0 : sb.toString());

    }
}
