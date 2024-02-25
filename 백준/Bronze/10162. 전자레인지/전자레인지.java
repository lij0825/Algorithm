import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int time, count, a = 0, b = 0, c = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        time = Integer.parseInt(br.readLine());
        count = Integer.MAX_VALUE;
        oven(0, 0, 0, 0, 0);
        if (count == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(a + " " + b + " " + c);
    }

    static void oven(int t, int cnt, int aCnt, int bCnt, int cCnt) {
        if (t > time) {
            return;
        }
        if (cnt > count) {
            return;
        }
        if (t == time) {
            count = cnt;
            a = aCnt;
            b = bCnt;
            c = cCnt;
            return;
        }

        oven(t + 300, cnt + 1, aCnt + 1, bCnt, cCnt);
        oven(t + 60, cnt + 1, aCnt, bCnt + 1, cCnt);
        oven(t + 10, cnt + 1, aCnt, bCnt, cCnt + 1);
    }
}
