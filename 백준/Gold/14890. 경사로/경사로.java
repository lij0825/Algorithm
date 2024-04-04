import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, l;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            if (check(i, 0, 0)) {
                cnt++;
            }
            if (check(0, i, 1)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static boolean check(int x, int y, int flag) {
        int[] height = new int[n + 1];
        boolean[] visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (flag == 0) {
                height[i] = map[x][i];
            } else {
                height[i] = map[i][y];
            }
        }

        for (int i = 1; i < n; i++) {
            if (height[i] == height[i + 1]) {
                continue;
            } else if (height[i] - 1 == height[i + 1]) {
                for (int j = i + 1; j < i + 1 + l; j++) {
                    if (j > n) {
                        return false;
                    }
                    if (visit[j]) {
                        return false;
                    }
                    if (height[i + 1] != height[j]) {
                        return false;
                    }
                    visit[j] = true;
                }
            } else if (height[i] + 1 == height[i + 1]) {
                for (int j = i; j > i - l; j--) {
                    if (j < 1) {
                        return false;
                    }
                    if (visit[j]) {
                        return false;
                    }
                    if (height[i] != height[j]) {
                        return false;
                    }
                    visit[j] = true;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}