

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int n = Integer.parseInt(br.readLine()); // 컴퓨터 수
        int k = Integer.parseInt(br.readLine()); // 연결된 컴퓨터 쌍 수

        graph = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1]; // 컴퓨터 감염 여부

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u][v] = graph[v][u] = true; // 연결된 컴퓨터
        }
        // 감염여부 갱신
        loop(1);

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (visited[i]) {
                count++; // 감염된 컴퓨터 개수
            }
        }

        System.out.println(count);
    }

    // 컴퓨터 감염 여부 갱신
    static void loop(int x) {
        visited[x] = true;
        for (int i = 1; i < graph.length; i++) {
            if (graph[x][i] && !visited[i]) {
                loop(i);
            }
        }
    }
}
