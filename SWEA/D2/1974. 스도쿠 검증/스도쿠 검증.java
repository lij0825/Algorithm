import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		int[][] board = new int[9][9];

		boolean flag;

		Set<Integer> check = new HashSet<Integer>();

		for (int t = 1; t <= T; t++) {

			flag = true;
			for (int i = 0; i < 9; i++) {
				String[] nums = br.readLine().split(" ");
				for (int j = 0; j < 9; j++) {
					board[i][j] = Integer.parseInt(nums[j]);
				}
			}

			// line check
			for (int i = 0; i < 9; i++) {
				check.clear();
				for (int j = 0; j < 9; j++) {
					check.add(board[i][j]);
				}
				if (check.size() != 9) {
					flag = false;
				}
			}
			for (int i = 0; i < 9; i++) {
				check.clear();
				for (int j = 0; j < 9; j++) {
					check.add(board[j][i]);
				}
				if (check.size() != 9) {
					flag = false;
				}
			}
			// box check
			for (int i = 0; i < 9; i += 3) {
				for (int j = 0; j < 9; j += 3) {
					check.clear();
					for (int k = i; k < i + 3; k++) {
						for (int l = j; l < j + 3; l++) {
							check.add(board[k][l]);
						}
					}
					if (check.size() != 9) {
						flag = false;
					}
				}
			}

			if (flag) {
				bw.write("#" + t + " 1\n");
			} else {
				bw.write("#" + t + " 0\n");
			}

		}

		bw.flush();
		bw.close();

	}

}
