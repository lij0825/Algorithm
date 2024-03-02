import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int d, w, k;
    static int[][] map;
    static int[][] copy;
    static int min;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[d][w];
            copy = new int[d][w];
            min = d;

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    copy[i][j] = map[i][j];
                }
            }

            if (lineChk()) {
                sb.append("#" + tc + " " + 0 + "\n");
                continue;
            }

            recursion(0, 0);
            sb.append("#" + tc + " " + min + "\n");
        }
        System.out.println(sb);
    }

    private static boolean lineChk() {
        for (int j = 0; j < w; j++) {
            int count = 1;
            int pre = copy[0][j];
            int max = 0;
            for (int i = 1; i < d; i++) {
                if (pre == copy[i][j])
                    count++;
                else
                    count = 1;

                pre = copy[i][j];
                max = Math.max(max, count);
            }
            if (max < k)
                return false;
        }
        return true;
    }

    private static void recursion(int cnt, int idx) {
        if (cnt >= min)
            return;
        if (idx == d) {
            if (lineChk())
                min = Math.min(min, cnt);
            return;
        }
        // 아무것도 안넣기
        recursion(cnt, idx + 1);
        // 0 넣기
        for (int i = 0; i < w; i++) {
            copy[idx][i] = 0;
        }
        recursion(cnt + 1, idx + 1);
        // 1넣기
        for (int i = 0; i < w; i++) {
            copy[idx][i] = 1;
        }
        recursion(cnt + 1, idx + 1);
        // 원본으로 되돌리기
        for (int i = 0; i < w; i++) {
            copy[idx][i] = map[idx][i];
        }
    }

}