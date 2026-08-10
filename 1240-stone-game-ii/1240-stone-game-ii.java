class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // 1. Suffix Sum (뒤에서부터의 누적합) 계산
        int[] sumStones = new int[n];
        sumStones[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sumStones[i] = piles[i] + sumStones[i + 1];
        }

        // 2. DP 테이블 선언
        int[][] dp = new int[n + 1][n + 1];

        // 3. DP 테이블 채우기
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                if (i + 2 * m >= n) {
                    // 남은 돌을 전부 가져갈 수 있는 경우
                    dp[i][m] = sumStones[i];
                } else {
                    // X를 1부터 2*m까지 시도하며 최댓값 탐색
                    int maxStones = 0;
                    for (int x = 1; x <= 2 * m; x++) {
                        int currentScore = sumStones[i] - dp[i + x][Math.max(m, x)];
                        maxStones = Math.max(maxStones, currentScore);
                    }
                    dp[i][m] = maxStones;
                }
            }
        }

        // 4. 시작 상태(i=0, M=1)의 최적 결과 반환
        return dp[0][1];
    }
}