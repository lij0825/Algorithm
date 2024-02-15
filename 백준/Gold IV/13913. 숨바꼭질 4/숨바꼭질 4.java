import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static int[] visited = new int[100001]; // 거리 저장
    static int[] parent = new int[100001]; // 이전 부모 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Arrays.fill(visited, -1);

        // 수빈이가 좌표값이 더 클 때
        if (n > k) {
            System.out.println(n - k);
            for (int i = n; i >= k; i--) {
                sb.append(i).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
            System.exit(0);
        }
        visited[n] = 0; // 처음은 0
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);
        while (!q.isEmpty()) {
            int idx = q.poll();
            if (idx == k) {
                break;
            }
            if (idx * 2 < visited.length && visited[idx * 2] == -1) {
                visited[idx * 2] = visited[idx] + 1;
                parent[idx * 2] = idx;
                q.add(idx * 2);
            }
            if (idx + 1 < visited.length && visited[idx + 1] == -1) {
                visited[idx + 1] = visited[idx] + 1;
                parent[idx + 1] = idx;
                q.add(idx + 1);
            }
            if (idx - 1 >= 0 && visited[idx - 1] == -1) {
                visited[idx - 1] = visited[idx] + 1;
                parent[idx - 1] = idx;
                q.add(idx - 1);
            }
        }

        sb.append(visited[k]).append("\n");

        // 최단 경로를 재구성하고 출력
        Stack<Integer> path = new Stack<>();
        int temp = k;
        while (temp != n) {
            path.push(temp);
            temp = parent[temp];
        }
        path.push(n);

        while (!path.isEmpty()) {
            sb.append(path.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
