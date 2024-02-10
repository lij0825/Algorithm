

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = findMin();
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb.toString());
    }

    public static int findMin() {
        int ans = Integer.MAX_VALUE;
        // 나올수있는 모든 조합 다 해봄
        for (int i = 1; i < (1 << n); i++) {
            int[] setA = new int[n];
            int[] setB = new int[n];
            int sizeA = 0, sizeB = 0;
            // 재료가 음식A, 음식B둘중 무슨 음식을 만드는지
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    setA[sizeA++] = j;
                } else {
                    setB[sizeB++] = j;
                }
            }

            int ta = 0, tb = 0;
            for (int k = 0; k < sizeA; k++) {
                for (int l = 0; l < sizeA; l++) {
                    ta += arr[setA[k]][setA[l]];
                }
            }

            for (int k = 0; k < sizeB; k++) {
                for (int l = 0; l < sizeB; l++) {
                    tb += arr[setB[k]][setB[l]];
                }
            }
            // 차이 최소값
            ans = Math.min(ans, Math.abs(ta - tb));
        }

        return ans;
    }
}
