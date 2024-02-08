

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n + 1];

        dp[0] = dp[2] = 1;

        for (int i = 4; i <= n; i += 2) {
            for (int j = 0; j < i; j += 2) {
                dp[i] = (dp[i] + (dp[j] * dp[i - 2 - j])) % 987654321;
            }
        }

        System.out.println(dp[n]);
    }
}
