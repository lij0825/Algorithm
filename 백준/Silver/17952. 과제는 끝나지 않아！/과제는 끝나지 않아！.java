

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int iswork = 0, a = 0, t = 0, score = 0;
        Stack<int[]> s = new Stack<int[]>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            iswork = Integer.parseInt(st.nextToken());
            if (iswork == 1) {
                a = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken()) - 1;
                if (t < 1) {
                    score += a;
                } else {
                    s.add(new int[] { a, t });
                }
            } else if (!s.isEmpty() && iswork == 0) {
                int[] at = s.pop();
                at[1] -= 1;
                if (at[1] < 1) {
                    score += at[0];
                } else {
                    s.add(at);
                }
            }
        }
        System.out.println(score);
    }
}
