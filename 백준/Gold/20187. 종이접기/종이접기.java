

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * D: 가로 중심선을 중심으로 반으로 접되 윗 면이 아랫 면을 덮도록 접음
 * U: 가로 중심선을 중심으로 반으로 접되 아랫 면이 윗 면을 덮도록 접음
 * R: 세로 중심선을 중심으로 반으로 접되 왼쪽 면이 오른쪽 면을 덮도록 접음
 * L: 세로 중심선을 중심으로 반으로 접되 오른쪽 면이 왼쪽 면을 덮도록 접음
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int k = Integer.parseInt(br.readLine());
        int n = (int) Math.pow(2, k);
        int[][] paper = new int[n][n];
        st = new StringTokenizer(br.readLine());
        Deque<String> q = new ArrayDeque<String>();
        for (int i = 0; i < 2 * k; i++) {
            q.add(st.nextToken());
        }
        q.addFirst(q.pollLast());
        while (!q.isEmpty()) {
            if (!q.peekLast().equals(q.peekFirst())) {
                break;
            } else {
                q.pollLast();
            }
        }
        int[][] ans = new int[2][2];
        int point = Integer.parseInt(br.readLine());
        String info1 = q.peekFirst();
        String info2 = q.peekLast();
        //
        if (info1.equals("L") && info2.equals("D")
                || info1.equals("D") && info2.equals("L")) {
            if (point == 0) {
                ans = new int[][] { { 2, 3, }, { 0, 1 } };
            }
            if (point == 1) {
                ans = new int[][] { { 3, 2, }, { 1, 0 } };
            }
            if (point == 2) {
                ans = new int[][] { { 0, 1, }, { 2, 3 } };
            }
            if (point == 3) {
                ans = new int[][] { { 1, 0, }, { 3, 2 } };
            }
        }
        //
        if (info1.equals("L") && info2.equals("U")
                || info1.equals("U") && info2.equals("L")) {
            if (point == 0) {
                ans = new int[][] { { 0, 1, }, { 2, 3 } };
            }
            if (point == 1) {
                ans = new int[][] { { 1, 0, }, { 3, 2 } };
            }
            if (point == 2) {
                ans = new int[][] { { 2, 3, }, { 0, 1 } };
            }
            if (point == 3) {
                ans = new int[][] { { 3, 2, }, { 1, 0 } };
            }
        }
        if (info1.equals("R") && info2.equals("D")
                || info1.equals("D") && info2.equals("R")) {
            if (point == 0) {
                ans = new int[][] { { 3, 2, }, { 1, 0 } };
            }
            if (point == 1) {
                ans = new int[][] { { 2, 3, }, { 0, 1 } };
            }
            if (point == 2) {
                ans = new int[][] { { 1, 0, }, { 3, 2 } };
            }
            if (point == 3) {
                ans = new int[][] { { 0, 1, }, { 2, 3 } };
            }
        }
        if (info1.equals("R") && info2.equals("U")
                || info1.equals("U") && info2.equals("R")) {
            if (point == 0) {
                ans = new int[][] { { 1, 0, }, { 3, 2 } };
            }
            if (point == 1) {
                ans = new int[][] { { 0, 1, }, { 2, 3 } };
            }
            if (point == 2) {
                ans = new int[][] { { 3, 2, }, { 1, 0 } };
            }
            if (point == 3) {
                ans = new int[][] { { 2, 3, }, { 0, 1 } };
            }
        }
        for (int i = 0; i < n; i += 2) {
            for (int j = 0; j < n; j += 2) {
                paper[i][j] = ans[0][0];
                paper[i][j + 1] = ans[0][1];
                paper[i + 1][j] = ans[1][0];
                paper[i + 1][j + 1] = ans[1][1];
            }
        }
        for (int[] is : paper) {
            System.out.println(Arrays.toString(is).replaceAll("[,\\[\\]]", ""));
        }
    }
}
