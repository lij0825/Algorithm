import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<int[]> list = new ArrayList<>();
	static int count1;
	static int count2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine()); // 꼭지점
		int[] p1 = new int[2];

		Stack<Integer> s = new Stack<>();

		st = new StringTokenizer(br.readLine()); // 첫 좌표
		p1[0] = Integer.parseInt(st.nextToken());
		p1[1] = Integer.parseInt(st.nextToken());

		for (int i = 1; i < n; i++) {
			int[] p2 = new int[2];
			st = new StringTokenizer(br.readLine());
			p2[0] = Integer.parseInt(st.nextToken());
			p2[1] = Integer.parseInt(st.nextToken());
			if (p1[0] == p2[0]) { // x가 같을때
				if ((p1[1] < 0 && p2[1] > 0)) { // 위로 올라오는 모양
					s.push(p1[0]);
				}
				if (p1[1] > 0 && p2[1] < 0) { // 아래로 내려가는 모양
					if (s.isEmpty()) {
						s.push(p1[0]);
					} else {
						int x = s.pop();
						int x1 = x < p1[0] ? x : p1[0];
						int x2 = x > p1[0] ? x : p1[0];
						list.add(new int[] { x1, x2 });
					}
				}
			}
			p1 = p2;
		}

		if (s.size() == 1) {
			int x = s.pop();
			int x1 = x < p1[0] ? x : p1[0];
			int x2 = x > p1[0] ? x : p1[0];
			list.add(new int[] { x1, x2 });
		} else if (s.size() == 2) {
			int s1 = s.pop();
			int s2 = s.pop();
			int x1 = s1 < s2 ? s1 : s2;
			int x2 = s1 > s2 ? s1 : s2;
			list.add(new int[] { x1, x2 });
		}

		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		if (list.size() == 1) {
			System.out.println(1 + " " + 1);
			return;
		}

		int count1 = 0;
		int count2 = list.size();

		Stack<int[]> countStack = new Stack<>();

		for (int[] is : list) {
			if (countStack.isEmpty()) {
				countStack.push(is);
				count1++;
			} else {
				if (countStack.peek()[1] > is[0]) {
					count2--;
				}
				while (!countStack.isEmpty() && countStack.peek()[1] < is[0]) {
					countStack.pop();
					if (countStack.isEmpty()) {
						count1++;
					}
				}
				countStack.push(is);
			}
		}
		System.out.println(count1 + " " + count2);
	}
}