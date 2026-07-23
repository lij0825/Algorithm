
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//유치원생은 쉽게 푸는 문제
public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int X = Integer.parseInt(br.readLine());
			if (X == 1) { // 1일때
				bw.write("0\n");
			} else if (X % 2 == 0) { // 짝수
				for (int i = 0; i < X / 2; i++) {
					bw.write("8");
				}
				bw.write("\n");
			} else { // 홀수
				bw.write("4");
				for (int i = 0; i < X / 2; i++) {
					bw.write("8");
				}
				bw.write("\n");
			}
		}

		br.close();
		bw.flush();
		bw.close();
	}

}
