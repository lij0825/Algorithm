
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생수
        int m = Integer.parseInt(st.nextToken()); // 비교횟수
        int[] relat = new int[n + 1]; // 간선수
        boolean[] visited = new boolean[n + 1];
        ArrayList[] students = new ArrayList[n + 1]; // 학생 관계
        for (int i = 0; i <= n; i++) {
            students[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int smaller = Integer.parseInt(st.nextToken());
            int bigger = Integer.parseInt(st.nextToken());
            students[smaller].add(bigger);
            relat[bigger] += 1;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (relat[i] == 0) {
                q.add(i);
                visited[i] = true;
            }
        }
        while (!q.isEmpty()) {
            int idx = q.poll();
            sb.append(idx).append(" ");
            for (int i = 0; i < students[idx].size(); i++) {
                relat[(int) students[idx].get(i)] -= 1;
            }
            for (int i = 1; i <= n; i++) {
                if (relat[i] == 0 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
