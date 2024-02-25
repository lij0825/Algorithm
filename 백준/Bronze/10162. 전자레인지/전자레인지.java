
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int time, count, a = 0, b = 0, c = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        time = Integer.parseInt(br.readLine());

        a = time / 300;
        time -= a * 300;
        b = time / 60;
        time -= b * 60;
        c = time / 10;
        time -= c * 10;
        if (time != 0) {
            System.out.println(-1);
        } else {
            System.out.println(a + " " + b + " " + c);
        }
    }
}
