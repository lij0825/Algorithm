
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//여행
public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {

			Set<String> dir = new HashSet<>();
			String[] input = br.readLine().split("");
			for (int i = 0; i < input.length; i++) {
				dir.add(input[i]);
			}

			boolean flag = (dir.contains("S") == dir.contains("N") && dir.contains("W") == dir.contains("E"));

			if (flag) {
				bw.write("Yes\n");
			} else {
				bw.write("No\n");
			}
		}

		bw.flush();
		bw.close();

	}

}
