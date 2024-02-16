import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());// 사람수
        boolean[] visited = new boolean[N + 1]; // 방문체크
        // 비교 대상 1,2 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int person1 = Integer.parseInt(st.nextToken());
        int person2 = Integer.parseInt(st.nextToken());
        // 관계
        int m = Integer.parseInt(br.readLine());
        ArrayList[] line = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            line[i] = new ArrayList<>();
        }
        int[] cnt = new int[N + 1];
        Arrays.fill(cnt, -1);
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            line[p].add(c);
            line[c].add(p);
        }

        queue.add(person1);
        visited[person1] = true;
        cnt[person1] = 0;
        while (!queue.isEmpty()) {
            int link = queue.poll();
            for (int i = 0; i < line[link].size(); i++) {
                if (visited[(Integer) line[link].get(i)]) {
                    continue;
                }

                queue.add((Integer) line[link].get(i));
                visited[(Integer) line[link].get(i)] = true;
                cnt[(Integer) line[link].get(i)] = cnt[link] + 1;

            }
        }

        System.out.println(cnt[person2]);
    }

}
