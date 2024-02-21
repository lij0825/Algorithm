

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] relation = new ArrayList[N];
        boolean[] visited;
        for (int i = 0; i < N; i++) {
            relation[i] = new ArrayList();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            relation[s].add(e);
            relation[e].add(s);
        }
        // for (ArrayList<Integer> el : relation) {
        // System.out.println(el);
        // }
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            int cnt = 0;
            if (!visited[i]) {
                if (dfs(relation, i, visited, cnt)) {
                    System.out.println(1);
                    System.exit(0);
                }
            }
        }
        System.out.println(0);
    }

    static boolean dfs(ArrayList<Integer>[] relation, int i, boolean[] visited, int cnt) {
        if (cnt == 4) {
            return true;
        }
        visited[i] = true;
        for (int j = 0; j < relation[i].size(); j++) {
            if (!visited[relation[i].get(j)]) {
                if (dfs(relation, relation[i].get(j), visited, cnt + 1)) {
                    return true;
                }
            }
        }
        visited[i] = false;
        return false;
    }
}

// 5 5
// 0 1
// 1 3
// 1 2
// 2 3
// 3 4

// 5 5
// 0 1
// 0 2
// 2 3
// 2 4
// 3 4
