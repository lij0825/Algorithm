import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    static Stack<Integer>[] s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        s = new Stack[n + 1];
        for (int i = 0; i < n; i++) {
            s[i] = new Stack<>();
        }
        int[] Line = new int[n];
        int[] press = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Line[i] = Integer.parseInt(st.nextToken());
            press[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (s[Line[i]].isEmpty()) {
                s[Line[i]].add(press[i]);
                cnt += 1;
                continue;
            }
            if (s[Line[i]].peek() < press[i]) {
                s[Line[i]].add(press[i]);
                cnt += 1;
            }
            if (s[Line[i]].peek() > press[i]) {
                if (s[Line[i]].peek() == press[i]) {
                    i--;
                    continue;
                } else {
                    s[Line[i]].pop();
                    cnt += 1;
                    i--;
                }
            }
        }
        System.out.println(cnt);
    }
}