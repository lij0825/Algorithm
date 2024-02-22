import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int v, e;
    static Edge[] edgeList;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        edgeList = new Edge[e + 1];

        // 연결정보 저장
        edgeList[0] = new Edge(0, 0, 0);
        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edgeList); // 가중치 순서 정렬

        make(); // 부모 노드 만들기

        int sum = 0;
        int cnt = 0;
        for (Edge edge : edgeList) {
            int from = edge.from;
            int to = edge.to;
            int weight = edge.weight;
            if (!union(from, to)) {
                continue;
            }
            sum += weight;
            cnt += 1;
            if (cnt == v - 1) {
                break;
            }
        }

        System.out.println(sum);
    }

    public static void make() {
        parents = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            parents[i] = i;
        }
    }

    public static int find(int x) {
        if (parents[x] != x) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parents[y] = x;
            return true;
        }
        return false;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }

    }

}
