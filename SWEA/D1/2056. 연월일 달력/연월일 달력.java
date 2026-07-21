import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//연월일 달력
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			char[] input = br.readLine().toCharArray();

			if (!isPossible(input)) {
				bw.write("#" + t + " -1\n");
			} else {

				String year = new String(input, 0, 4);
				String month = new String(input, 4, 2);
				String day = new String(input, 6, 2);

				bw.write("#" + t + " " + year + "/" + month + "/" + day + "\n");
			}

		}

		br.close();
		bw.flush();
		bw.close();
	}

	static boolean isPossible(char[] input) {
		int month = (input[4] - '0') * 10 + input[5] - '0';
		int day = (input[6] - '0') * 10 + input[7] - '0';

		int[] daysOfMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month < 1 || month > 12) {
			return false;
		}

		if (day < 1 || day > daysOfMonth[month]) {
			return false;
		}

		return true;
	}

}
