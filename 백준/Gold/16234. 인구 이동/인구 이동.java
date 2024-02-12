
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N, L, R;
    static int[][] people;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        people = new int[N][N];

        // 각 칸의 인구 수 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 상, 하, 좌, 우
        int[] dy = { 1, 0, -1, 0 };
        int[] dx = { 0, 1, 0, -1 };

        int day = 0;
        while (true) {
            int stop = 0; // 이동 안하면 0
            visited = new boolean[N][N];

            // 모든 칸을 방문하면서 연합 형성 시도
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        stop += bfs(i, j, dy, dx);
                    }
                }
            }

            // 모든 연합 형성 시도 후 변경이 없으면 종료
            if (stop == 0) {
                break;
            }

            // 하루가 지남
            day++;
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(day);
        System.out.println(sb.toString());
    }

    private static int bfs(int x, int y, int[] dy, int[] dx) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { x, y });

        ArrayList<int[]> union = new ArrayList<>(); // 최대로 연합하면 N*N

        // BFS를 통해 연합 형성
        int sum = 0; // 연합의 인구 수 합산
        int depth = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];

            // 현재 위치를 연합에 추가
            union.add(new int[] { a, b });
            sum += people[union.get(depth)[0]][union.get(depth)[1]];
            depth++;
            // 상, 하, 좌, 우 탐색
            for (int i = 0; i < 4; i++) {
                int na = a + dx[i];
                int nb = b + dy[i];

                // 범위를 벗어나거나 이미 방문한 경우 무시
                if (na >= N || nb >= N || nb < 0 || na < 0 || visited[na][nb]) {
                    continue;
                }

                // 조건에 맞는 경우 연합에 추가
                if (R >= Math.abs(people[a][b] - people[na][nb]) && Math.abs(people[a][b] - people[na][nb]) >= L) {
                    visited[na][nb] = true;
                    queue.add(new int[] { na, nb });
                }
            }
        }

        // 연합 크기가 1 이하면 연합 실패
        if (union.size() <= 1) {
            return 0;
        }

        // 새로운 인구 수 계산 및 적용
        int result = sum / union.size();
        for (int i = 0; i < union.size(); i++) {
            people[union.get(i)[0]][union.get(i)[1]] = result;
        }

        // 연합 형성 성공
        return 1;
    }
}
