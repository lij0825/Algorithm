

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 이인준
 * 
 *         왼쪽부터 1자리씩이 모두 소수이기때문에 높은 자릿수부터 소수검사하면 보다 적게 검사할수있다
 */
public class Main {
	// 소수들 저장용
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		getResult(0, n);
		System.out.println(sb);
	}

	// n 자릿수 까지 검사해서 소수인지 판별
	public static void getResult(int output, int n) {
		if (n == 0) { // 자릿수 다 검사
			if (isPrime(output)) { // 소수일시
				sb.append(output).append("\n"); // sb에 저장
			}
			return;
		}
		for (int i = 0; i < 10; i++) {
			int next = output * 10 + i; //
			if (isPrime(next)) {
				getResult(next, n - 1);
			}
		}
	}

	// 소수 확인
	public static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}

		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

}