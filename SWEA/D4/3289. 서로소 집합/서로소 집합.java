import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i; // 초기에는 각 원소가 자신을 대표로 설정
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x; // 대표 원소를 찾음
        }
        return parent[x] = find(parent[x]); // 경로 압축
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x; // 두 집합을 합침
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            sb.append("#" + t + " ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            UnionFind uf = new UnionFind(n);
            int m = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int o = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                if (o == 0) {
                    uf.union(s, e);
                }
                if (o == 1) {
                    if (uf.find(s) == uf.find(e)) {
                        sb.append("1");
                    } else {
                        sb.append("0");
                    }
                }
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
