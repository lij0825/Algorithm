import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 접시의 개수 입력
		int N = Integer.parseInt(st.nextToken());
		// 접시 배열 선언
		int[] input = new int[N];
		// 각 접시의 상태 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		// 접시를 포개는 최대 횟수 출력
		System.out.println(Math.max(go(input), go(reverseArray(input))));
	}

	// 주어진 배열로 접시를 포개는 함수
	public static int go(int[] inp) {
		Stack<Integer> s = new Stack<>();
		// 각 접시 상태에 따라 스택에 쌓음
		for (int i : inp) {
			if (!s.isEmpty() && s.peek() == 1 && i == 1) {
				// 연속된 1 상태의 접시가 있으면 포개어 2 상태로 변경
				s.pop();
				s.push(2);
			} else {
				s.push(i);
			}
		}
		// 마지막 접시 상태가 1이 아니면 1 상태의 접시 추가
		if (s.peek() != 1) {
			s.push(1);
		}
		// 포개는 횟수와 관련된 변수 초기화
		int prv = 0, ret = 1;
		// 스택을 순회하며 접시 포개는 횟수 갱신
		for (int idx = 0; idx < s.size(); idx++) {
			int i = s.get(idx);
			if (i != 1)
				continue;
			int cnt = idx - prv;
			if (cnt != 0) {
				ret = Math.max(ret, 1 << (int) (Math.log(cnt) / Math.log(2)) + 1);
			}
			prv = idx;
		}
		return ret; // 최대 접시 포개는 횟수 반환
	}

	// 배열을 역순으로 만드는 함수
	public static int[] reverseArray(int[] arr) {
		int[] reversedArray = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			reversedArray[i] = arr[arr.length - 1 - i];
		}
		return reversedArray; // 역순으로 된 배열 반환
	}
}