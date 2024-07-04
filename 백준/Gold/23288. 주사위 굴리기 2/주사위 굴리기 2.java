import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	// (3,1) 이 밑면
	static int[][] dice_map = {
		{0, 2, 0},
		{4, 1, 3},
		{0, 5, 0},
		{0, 6, 0}
	};

	// 처음 방향 동쪽 (1: 동, 2: 서, 3: 남, 4: 북)
	static int dir = 0, N, M, score = 0;
	static int[][] board; // 점수판
	static Dice dice = new Dice(0, 0);

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]); // 세로
		M = Integer.parseInt(input[1]); // 가로
		int K = Integer.parseInt(input[2]); // 이동 횟수

		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(input[j]);
			}
		}

		for (int i = 0; i < K; i++) {
			roll();
		}
		System.out.println(score);
	}

	// 방향 정하기
	static int dice_dir(int A, int B) {
		// 오른쪽으로 90도
		if (A > B) {
			return (dir + 1) % 4;
		}
		// 왼쪽으로 90도
		if (A < B) {
			return (dir + 3) % 4;
		}
		return dir;
	}

	// 점수판에 따라 점수 더하기
	static void sum_score() {
		boolean[][] visited = new boolean[N][M];
		int[] dy = {1, -1, 0, 0}, dx = {0, 0, 1, -1};
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(dice.y, dice.x));
		visited[dice.y][dice.x] = true;
		score += board[dice.y][dice.x];

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
					continue;
				}
				if (visited[ny][nx]) {
					continue;
				}
				if (board[ny][nx] != board[dice.y][dice.x]) {
					continue;
				}
				score += board[ny][nx];
				visited[ny][nx] = true;
				q.add(new Point(ny, nx));
			}
		}
	}

	static void roll() {
		int nextY = dice.y, nextX = dice.x;
		switch (dir) {
			case 0:
				nextX++;
				break; // 동
			case 1:
				nextY++;
				break; // 남
			case 2:
				nextX--;
				break; // 서
			case 3:
				nextY--;
				break; // 북
		}

		// 경계 조건 처리
		if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) {
			dir = (dir + 2) % 4; // 방향 반대로
			nextY = dice.y;
			nextX = dice.x;
			switch (dir) {
				case 0:
					nextX++;
					break; // 동
				case 1:
					nextY++;
					break; // 남
				case 2:
					nextX--;
					break; // 서
				case 3:
					nextY--;
					break; // 북
			}
		}

		// 주사위 위치 업데이트
		dice.y = nextY;
		dice.x = nextX;

		// 주사위 면 변환
		change_dice_map();

		// 방향 업데이트
		dir = dice_dir(dice_map[3][1], board[dice.y][dice.x]);

		// 점수 계산
		sum_score();
	}

	static void change_dice_map() {
		switch (dir) {
			// 동
			case 0:
				int temp = dice_map[1][2];
				dice_map[1][2] = dice_map[1][1];
				dice_map[1][1] = dice_map[1][0];
				dice_map[1][0] = dice_map[3][1];
				dice_map[3][1] = temp;
				break;
			// 서
			case 2:
				temp = dice_map[1][0];
				dice_map[1][0] = dice_map[1][1];
				dice_map[1][1] = dice_map[1][2];
				dice_map[1][2] = dice_map[3][1];
				dice_map[3][1] = temp;
				break;
			// 남
			case 1:
				temp = dice_map[3][1];
				dice_map[3][1] = dice_map[2][1];
				dice_map[2][1] = dice_map[1][1];
				dice_map[1][1] = dice_map[0][1];
				dice_map[0][1] = temp;
				break;
			// 북
			case 3:
				temp = dice_map[0][1];
				dice_map[0][1] = dice_map[1][1];
				dice_map[1][1] = dice_map[2][1];
				dice_map[2][1] = dice_map[3][1];
				dice_map[3][1] = temp;
				break;
		}
	}

	static class Dice {
		int y, x;

		Dice(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}