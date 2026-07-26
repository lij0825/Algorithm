import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//view
public class Solution {

	static int[] apart;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {

			int N = Integer.parseInt(br.readLine());

			apart = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				apart[i] = Integer.parseInt(st.nextToken());
			}

			int ans = 0;
			for (int i = 2; i < N - 2; i++) {
				int maxx = max(i);
				if (apart[i] > maxx) {
					ans += (apart[i] - maxx);
				}
			}

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}

	static int max(int index) {

		return Math.max(Math.max(apart[index - 2], apart[index - 1]), Math.max(apart[index + 1], apart[index + 2]));

	}

}
