import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int half = N / 2;

		String[] str = br.readLine().split("");

		Queue<String> cut = new ArrayDeque<>();

		int cnt_s = 0;
		int cnt_k = 0;
		int ans = 0;
		for (int i = 0; i < N; i++) {
			if (i < half) {
				cut.add(str[i]);
				if (str[i].equals("s")) {
					cnt_s++;
				} else {
					cnt_k++;
				}
			} else {
				if (cnt_s != cnt_k) {
					if (cut.poll().equals("s")) {
						cnt_s--;
					} else {
						cnt_k--;
					}
					cut.add(str[i]);
					if (str[i].equals("s")) {
						cnt_s++;
					} else {
						cnt_k++;
					}
				} else {
					ans = i;
					break;
				}
			}
		}

		if (ans == half) {
			System.out.println(1);
			System.out.println(ans);
		} else {
			System.out.println(2);
			System.out.println((ans - half) + " " + ans);
		}
	}
}