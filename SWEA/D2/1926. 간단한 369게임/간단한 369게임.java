import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int cnt;

		for (int i = 1; i <= N; i++) {
			String[] num = String.valueOf(i).split("");

			cnt = 0;
			for (int j = 0; j < num.length; j++) {

				if (num[j].equals("3") || num[j].equals("6") || num[j].equals("9")) {
					cnt += 1;
				}
			}

			if (cnt != 0) {
				for (int k = 0; k < cnt; k++) {
					bw.write("-");
				}
			} else {
				for (int k = 0; k < num.length; k++) {
					bw.write(num[k]);
				}
			}
			bw.write(" ");
		}

		bw.flush();
		bw.close();
	}

}
