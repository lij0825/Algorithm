


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); // 전봇대 개수
        H = new int[N]; // 전봇대 높이

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken()); // idx에 따른 전봇대 크기 저장
        }

        long[] leftDp = getDP(H);
        long[] rightDp = getDP(reverseH());
        int Q = Integer.parseInt(br.readLine()); // 명령 개수
        for (int i = 0; i < Q; i++) {
            int p = Integer.parseInt(br.readLine()) - 1; // 시작 전봇대
            sb.append(leftDp[p] + rightDp[N - p - 1]).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        // 출력
        System.out.print(sb.toString());
    }

    // dp로 모든 위치에서 최솟값 찾기
    // 근데 그냥 가까운거 바로바로 연결하는게 최솟값
    static long[] getDP(int[] Harr) {
        long[] dp = new long[N]; // dp배열
        Stack<int[]> s = new Stack<>(); // 스택에 {높이, 인덱스} 저장
        for (int i = 0; i < N; i++) { // 전봇대 전부
            while (!s.isEmpty() && s.peek()[0] < Harr[i]) { // 현재 높이가 전보다 높으면 계산할 필요 x
                s.pop(); // 계산할 필요 없는거 팝
            }
            if (!s.isEmpty()) {
            	long hh =  Math.abs(s.peek()[0] - Harr[i]);
            	long LL =  Math.abs(i - s.peek()[1]);
                dp[i] = hh * hh // 높이 차 제곱
                        + LL*LL// 인덱스 차 제곱
                        + dp[s.peek()[1]]; // 그 전까지의 합
            }
            s.push(new int[] { Harr[i], i }); // 스택이 비어 있으면 넣음 {높이, 인덱스}
        }
        return dp;
    }

    // 높이 배열 거꾸로
    static int[] reverseH() {
        int[] reversed = new int[N];
        for (int i = 0; i < N; i++) {
            reversed[i] = H[N - i - 1];
        }
        return reversed;
    }
}