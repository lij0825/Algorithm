import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int v, e;
    static Edge[] edgeList;
    static long[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());

            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            edgeList = new Edge[e + 1];

            // 연결정보 저장
            edgeList[0] = new Edge(0, 0, 0);
            for (int i = 1; i <= e; i++) {
                st = new StringTokenizer(br.readLine());
                long from = Integer.parseInt(st.nextToken());
                long to = Integer.parseInt(st.nextToken());
                long weight = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(from, to, weight);
            }

            Arrays.sort(edgeList); // 가중치 순서 정렬

            make(); // 부모 노드 만들기

            long sum = 0;
            int cnt = 0;
            for (Edge edge : edgeList) {
                long from = edge.from;
                long to = edge.to;
                long weight = edge.weight;
                if (!union(from, to)) {
                    continue;
                }
                sum += weight;
                cnt += 1;
                if (cnt == v - 1) {
                    break;
                }
            }

            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

    public static void make() {
        parents = new long[v + 1];
        for (long i = 0; i <= v; i++) {
            parents[(int) i] = i;
        }
    }

    public static long find(long x) {
        if (parents[(int) x] != x) {
            parents[(int) x] = find(parents[(int) x]);
        }
        return parents[(int) x];
    }

    public static boolean union(long x, long y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parents[(int) y] = x;
            return true;
        }
        return false;
    }

    static class Edge implements Comparable<Edge> {
        long from, to, weight;

        public Edge(long from, long to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }

    }

}
