import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//DNA 수열
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문자열 길이
			char[] input = st.nextToken().toCharArray(); // AT.CG 문자열

			int answer = 0;
			for (int i = 0; i < N; i++) {
				int Acount = 0, Tcount = 0, Ccount = 0, Gcount = 0;
				for (int j = i; j < N; j++) {
					if (input[j] == 'A') {
						Acount++;
					} else if (input[j] == 'T') {
						Tcount++;
					} else if (input[j] == 'C') {
						Ccount++;
					} else if (input[j] == 'G') {
						Gcount++;
					}
					if (Acount == Tcount && Ccount == Gcount) {
						answer++;
					}
				}
			}

			bw.write(answer + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}

}
