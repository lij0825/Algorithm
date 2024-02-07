

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.lang.Math;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//pq Comparator 재정의
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			if (Math.abs(o1) == Math.abs(o2)) {
				return o1 - o2;
			} else {
				return Math.abs(o1) - Math.abs(o2);
			}
		});

		int N = Integer.parseInt(br.readLine()); // 연산의 개수

		for (int i = 0; i < N; i++) {
			int nt = Integer.parseInt(br.readLine());
			if (nt == 0) { // 0 이면 조건 실행
				if (!pq.isEmpty()) {
					sb.append(pq.poll()).append("\n");
				} else if (pq.isEmpty()) {
					sb.append("0\n");
				}

			}
			if (nt != 0) { // 0 이 아니면 pq에 삽입
				pq.add(nt);
			}
		}

		System.out.println(sb);
	}
}