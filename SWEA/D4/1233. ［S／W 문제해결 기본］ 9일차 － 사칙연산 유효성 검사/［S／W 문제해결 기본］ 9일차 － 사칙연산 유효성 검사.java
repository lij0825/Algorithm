

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc < 11; tc++) {

			int n = Integer.parseInt(br.readLine());
			int cnt = 1;
			sb.append("#").append(tc);
			for (int i = 0; i < n; i++) {
				String[] str = br.readLine().split(" ");
				if (str.length == 4) {
					if (!str[1].equals("+") && !str[1].equals("-") && !str[1].equals("*") && !str[1].equals("/")) {
						cnt += 1;
					}
				}
				if (str.length == 2) {
					if (str[1].equals("+") || str[1].equals("-") || str[1].equals("*") || str[1].equals("/")) {
						cnt += 1;

					}
				}
			}
			if (cnt == 1) {
				sb.append(" 1\n");
			} else {
				sb.append(" 0\n");
			}
		}
		System.out.println(sb);
	}
}
