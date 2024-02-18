import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 길이
        int m = Integer.parseInt(st.nextToken()); // 텔포 개수
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()); // 시작
        int e = Integer.parseInt(st.nextToken()); // 끝
        int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);
        visited[s] = 0;
        ArrayList[] tp = new ArrayList[n + 1]; // 텔포 좌표들
        for (int i = 0; i < n + 1; i++) {
            tp[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int tps = Integer.parseInt(st.nextToken());
            int tpe = Integer.parseInt(st.nextToken());
            tp[tps].add(tpe);
            tp[tpe].add(tps);
        }
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(s);
        while (!q.isEmpty()) {
            int idx = q.poll();
            if (idx == e) {
                break;
            }
            if (tp[idx] != null) {
                for (int i = 0; i < tp[idx].size(); i++) {
                    int useTp = (int) tp[idx].get(i);
                    if (visited[useTp] == -1) {
                        visited[useTp] = visited[idx] + 1;
                        q.add(useTp);
                    }
                }
            }
            if (idx + 1 < visited.length && visited[idx + 1] == -1) {
                visited[idx + 1] = visited[idx] + 1;
                q.add(idx + 1);
            }
            if (idx - 1 >= 0 && visited[idx - 1] == -1) {
                visited[idx - 1] = visited[idx] + 1;
                q.add(idx - 1);
            }
        }
        System.out.println(visited[e]);
    }
}