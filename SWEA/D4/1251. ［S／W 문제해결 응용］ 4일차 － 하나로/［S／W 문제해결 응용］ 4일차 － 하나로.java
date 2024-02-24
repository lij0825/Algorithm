
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 이인준
 * 
 *         (E * L^2)
 */
public class Solution {
    static int n;
    static double e;
    static int[] islandX, islandY, relation;
    static boolean[] visited;
    static Queue<Integer> q;
    static ArrayList<islandCost> costInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine()); // 테케수
        for (int t = 1; t <= tc; t++) {
            // 섬의 수
            n = Integer.parseInt(br.readLine());
            // 섬 x좌표
            islandX = new int[n];
            visited = new boolean[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                islandX[i] = Integer.parseInt(st.nextToken());
            }
            // 섬 y좌표
            islandY = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                islandY[i] = Integer.parseInt(st.nextToken());
            }
            // 세율
            e = Double.parseDouble(br.readLine());

            costInfo = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    costInfo.add(new islandCost(i, j, cost(i, j)));
                }
            }

            costInfo.sort(new islandCostComparator());
            // System.out.println(costInfo);

            relation = new int[n];
            for (int i = 0; i < n; i++) {
                relation[i] = i;
            }

            double cost = 0;
            int cnt = 0;
            int depth = 0;
            while (cnt != n - 1) {
                int a = costInfo.get(depth).a;
                int b = costInfo.get(depth).b;
                double abcost = costInfo.get(depth).cost;
                if (check(a, b)) {
                    cost += abcost;
                    cnt += 1;
                }
                depth += 1;
            }
            sb.append("#").append(t).append(" ").append(Math.round(cost)).append("\n");
        }
        System.out.println(sb);
    }

    // 비용
    static double cost(int a, int b) {
        double row = Math.abs(islandX[a] - islandX[b]);
        double col = Math.abs(islandY[a] - islandY[b]);
        return e * (row * row + col * col);
    }

    static boolean check(int a, int b) {
        int islandX = find(a);
        int islandY = find(b);

        if (islandX == islandY) {
            return false;
        }

        union(islandX, islandY);
        return true;
    }

    static int find(int x) {
        if (relation[x] == x) {
            return x;
        }
        return relation[x] = find(relation[x]);
    }

    static void union(int a, int b) {
        relation[a] = b;
    }

}
//

//
class islandCost {
    int a, b;
    double cost;

    public islandCost(int a, int b, double cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "islandCost [a=" + a + ", b=" + b + ", cost=" + cost + "]" + "\n";
    }

}

//
class islandCostComparator implements Comparator<islandCost> {
    @Override
    public int compare(islandCost o1, islandCost o2) {
        return Double.compare(o1.cost, o2.cost);
    }
}