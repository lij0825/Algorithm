import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			st = new StringTokenizer(br.readLine());

			double sum = 0;
			for (int i = 0; i < 10; i++) {
				sum += Integer.parseInt(st.nextToken());
			}

			sum /= 10;
			bw.write("#" + tc + " " + String.valueOf(Math.round(sum)) + "\n");

		}

		bw.flush();
		bw.close();
	}

}
