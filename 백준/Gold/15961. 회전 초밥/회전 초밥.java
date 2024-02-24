
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, d, k, c, arr[], sushi[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[d + 1]; // 무슨 초밥 먹었는지 기억
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(slide());
    }

    private static int slide() {
        int total = 0, max = 0;
        for (int i = 0; i < k; i++) {
            total = sushi[arr[i]] == 0 ? total + 1 : total; // 안먹은거면 종류 +1
            sushi[arr[i]]++; // 먹음
        }
        max = sushi[c] == 0 ? total + 1 : total; // 쿠폰번호 안먹은거면 +1
        for (int i = 1; i <= n; i++) {
            if (max <= total) {
                max = sushi[c] == 0 ? total + 1 : total;
            }
            sushi[arr[i - 1]]--; // 해당 초밥 먹은 횟수 하나 뺌
            total = sushi[arr[i - 1]] == 0 ? total - 1 : total; // 0 이면 종류 -1
            total = sushi[arr[(i + k - 1) % n]] == 0 ? total + 1 : total; // 다음꺼 먹을거니까 0 이면 종류 +1
            sushi[arr[(i + k - 1) % n]]++; // 다음꺼 하나 먹음
        }
        return max;
    }
}