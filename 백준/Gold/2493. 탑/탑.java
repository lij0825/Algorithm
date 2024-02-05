

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] h = new int[n];
        int[] ans = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0); // 초기값

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && h[stack.peek()] < h[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                ans[i] = stack.peek() + 1;
            }

            stack.push(i);
        }

        System.out.println(Arrays.toString(ans).replaceAll("[,\\[\\]]", ""));
    }
}
