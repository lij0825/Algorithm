import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");

			ArrayList<int[]> pl = new ArrayList<>();

			ArrayList<int[]> syx = new ArrayList<>();
			// 방크기
			int n = Integer.parseInt(br.readLine());
			// 맵 입력 , 사람 좌표 , 계단 좌표 저장
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						pl.add(new int[] { i, j });
					}
					if (map[i][j] > 1) {
						syx.add(new int[] { i, j, map[i][j] });
					}
				}
			}
			// 계단 1
			int firstsy = syx.get(0)[0];
			int firstsx = syx.get(0)[1];
			int firstval = syx.get(0)[2];
			// 계단 2
			int secondsy = syx.get(1)[0];
			int secondsx = syx.get(1)[1];
			int secondval = syx.get(1)[2];
			// 모든 조합
			int comNum = (int) Math.pow(2, pl.size());
			int[][] com = new int[comNum][pl.size()];
			Deque<Integer> dq = new ArrayDeque<>();
			for (int i = 0; i < comNum; i++) {

				String[] str = Integer.toBinaryString(i).split("");
				for (int j = 0; j < str.length; j++) {
					dq.add(Integer.parseInt(str[j]));
				}

				int dqSize = dq.size();
				if (dqSize < pl.size()) {
					for (int j = 0; j < pl.size() - dqSize; j++) {
						dq.addFirst(0);
					}
				}

				int idx = 0;
				while (!dq.isEmpty()) {
					com[i][idx++] = dq.pollFirst();
				}
			}
			//
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			PriorityQueue<Integer> pqFirst = new PriorityQueue<>();
			PriorityQueue<Integer> pqSecond = new PriorityQueue<>();
			for (int i = 0; i < comNum; i++) {
				pqFirst.clear();
				pqSecond.clear();
				for (int j = 0; j < pl.size(); j++) {
					if (com[i][j] == 0) {
						int y = pl.get(j)[0];
						int x = pl.get(j)[1];
						int len = Math.abs(y - firstsy) + Math.abs(x - firstsx);
						pqFirst.add(len);
					} else {
						int y = pl.get(j)[0];
						int x = pl.get(j)[1];
						int len = Math.abs(y - secondsy) + Math.abs(x - secondsx);
						pqSecond.add(len);
					}
				}
				int result = Math.max(time(pqFirst, firstval), time(pqSecond, secondval));
				min = Math.min(min, result);
			}
			sb.append(min).append("\n");
		} // 테케
		System.out.println(sb);
	}// 메인

	static int time(PriorityQueue<Integer> pq, int stairLength) {

		int time = 1;

		Queue<Integer> q = new ArrayDeque<>();

		while (!pq.isEmpty() || !q.isEmpty()) {
			while (!pq.isEmpty() && time >= pq.peek()) {
				if (q.size() == 3) {
					break;
				}
				pq.poll();
				q.add(stairLength + 1);
			}

			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int down = q.poll() - 1;
				if (down != 0) {
					q.add(down);
				} else if (!pq.isEmpty() && time >= pq.peek()) {
					pq.poll();
					q.add(stairLength);
				}
			}

			time += 1;
		}

		return time;
	}
}