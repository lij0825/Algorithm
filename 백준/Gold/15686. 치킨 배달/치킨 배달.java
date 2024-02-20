import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 0은 빈 칸, 1은 집, 2는 치킨집
 * 
 * @author 이인준
 */

class Main {
    static int[][] city;
    static boolean[] visited;
    static int n, m, ans;
    static ArrayList<Integer> way = new ArrayList<Integer>();
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> home = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        city = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 2) {
                    chicken.add(new int[] { i, j }); // 치킨 좌표 저장
                }
                if (city[i][j] == 1) {
                    home.add(new int[] { i, j }); // 집 좌표 저장
                }
            }
        }
        ans = Integer.MAX_VALUE;
        visited = new boolean[chicken.size()];
        DFS(0, 0);
        bw.write(ans + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static void DFS(int start, int cnt) {
        if (cnt == m) { // 치킨집을 m개 골랐으면
            int res = 0;
            // 모든 집에서 모든 치킨집에 대해 최소 거리 구하기
            for (int i = 0; i < home.size(); i++) {
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        int distance = Math.abs(home.get(i)[0] - chicken.get(j)[0])
                                + Math.abs(home.get(i)[1] - chicken.get(j)[1]);
                        temp = Math.min(temp, distance); // 가장 적은 거리로 갱신
                    }
                }
                res += temp;
            }
            ans = Math.min(ans, res); // 집들의 치킨거리 최소값
            return;
        }
        // 조합 뽑음
        for (int i = start; i < chicken.size(); i++) {
            visited[i] = true;
            DFS(i + 1, cnt + 1);
            visited[i] = false;
        }
    }
}