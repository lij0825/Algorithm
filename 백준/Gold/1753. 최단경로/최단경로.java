import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Edge>> node = new ArrayList<>();
    static int[] value;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, e, k;
        int u, v, w; // u에서 v로 가는 가중치 w

        n = Integer.parseInt(st.nextToken()); // 정점 개수
        e = Integer.parseInt(st.nextToken()); // 간선 개수
        k = Integer.parseInt(br.readLine()); // 시작 노드 번호

        for (int i = 0; i <= n; i++) {
            node.add(new ArrayList<>());
        }

        value = new int[n + 1];

        Arrays.fill(value, INF);

        value[k] = 0; // 시작노드 설정
        // 우선 순위 큐 재설정
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.x));
        pq.add(new Pair(0, k));

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            node.get(u).add(new Edge(v, w));
        }

        while (!pq.isEmpty()) {
            int x = pq.peek().x;
            int y = pq.poll().y;

            for (int i = 0; i < node.get(y).size(); i++) {
                int V = node.get(y).get(i).to;
                int W = node.get(y).get(i).weight;

                if (x + W < value[V]) {
                    value[V] = x + W;
                    pq.add(new Pair(x + W, V));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (value[i] == INF) {
                sb.append("INF").append("\n");
            } else {
                sb.append(value[i]).append("\n");
            }
        }

        System.out.print(sb.toString());
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
