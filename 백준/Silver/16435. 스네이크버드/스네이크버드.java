import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] H = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < H.length; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(H);
        for (int i = 0; i < H.length; i++) {
            if (L >= H[i]) {
                L++;
            } else {
                break;
            }
        }
        System.out.println(L);
    }
}
