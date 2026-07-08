import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
	static char[] cards;
	static int max;
	static int targetCnt;
	static HashSet<Integer>[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			cards = st.nextToken().toCharArray();
			targetCnt = Integer.parseInt(st.nextToken());
			max = 0;

			visited = new HashSet[targetCnt + 1];
			for (int i = 0; i <= targetCnt; i++) {
				visited[i] = new HashSet<>();
			}

			dfs(0);

			bw.write("#" + t + " " + max + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static void dfs(int currentCnt) {
		int currentNum = getNumFromCards();

		if (currentCnt == targetCnt) {
			max = Math.max(max, currentNum);
			return;
		}

		for (int i = 0; i < cards.length - 1; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				swap(i, j);

				int nextNum = getNumFromCards();

				if (!visited[currentCnt + 1].contains(nextNum)) {
					visited[currentCnt + 1].add(nextNum); // 방문 기록
					dfs(currentCnt + 1);
				}

				swap(i, j);
			}
		}
	}

	static void swap(int i, int j) {
		char temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}

	static int getNumFromCards() {
		int num = 0;
		for (int i = 0; i < cards.length; i++) {
			num = num * 10 + (cards[i] - '0');
		}
		return num;
	}
}