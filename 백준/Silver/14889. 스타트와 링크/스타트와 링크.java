

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int m;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        m = n / 2;
        dfs(0, new int[0], new int[0]);
        System.out.println(ans);
    }

    static void dfs(int depth, int[] arrS, int[] arrL) {
        if (depth == n) {
            if (arrS.length == arrL.length) {
                int sumS = 0, sumL = 0;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        sumS += arr[arrS[i]][arrS[j]];
                        sumL += arr[arrL[i]][arrL[j]];
                    }
                }
                ans = Math.min(ans, Math.abs(sumS - sumL));
            }
            return;
        }
        // 초기화
        int[] copyS = new int[arrS.length + 1];
        int[] copyL = new int[arrL.length + 1];
        // alst 복사해서 newAlst에 넣음
        System.arraycopy(arrS, 0, copyS, 0, arrS.length);
        copyS[arrS.length] = depth;
        // blst 복사해서 newBlst에 넣음
        System.arraycopy(arrL, 0, copyL, 0, arrL.length);
        copyL[arrL.length] = depth;

        dfs(depth + 1, copyS, arrL);
        dfs(depth + 1, arrS, copyL);
    }
}
