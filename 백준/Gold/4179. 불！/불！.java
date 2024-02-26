import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
    static int r, c;
    static char[][] maze;
    static Queue<int[]> fireq = new ArrayDeque<>();
    static Queue<int[]> jihoonq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        maze = new char[r][c];
        for (int i = 0; i < r; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        if (!escape()) {
            System.out.println("IMPOSSIBLE");
        }
    }

    static boolean escape() {
        int[][] fire = new int[r][c];
        int[][] jihoon = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maze[i][j] == 'F') {
                    fireq.add(new int[] { i, j, 0 });
                    fire[i][j] = 1;
                } else if (maze[i][j] == 'J') {
                    jihoonq.add(new int[] { i, j, 0 });
                    jihoon[i][j] = 1;
                }
            }
        }

        while (!fireq.isEmpty() || !jihoonq.isEmpty()) {
            int fireSize = fireq.size();
            for (int i = 0; i < fireSize; i++) {
                int[] yxt = fireq.poll();
                int y = yxt[0];
                int x = yxt[1];
                int time = yxt[2];

                for (int j = 0; j < 4; j++) {
                    int ny = y + dy[j];
                    int nx = x + dx[j];

                    if (ny >= 0 && nx >= 0 && ny < r && nx < c
                            && maze[ny][nx] == '.' && fire[ny][nx] == 0) {
                        fire[ny][nx] = time + 1;
                        fireq.add(new int[] { ny, nx, time + 1 });
                    }
                }
            }

            int size = jihoonq.size();
            for (int i = 0; i < size; i++) {
                int[] current = jihoonq.poll();
                int y = current[0];
                int x = current[1];
                int time = current[2];

                for (int j = 0; j < 4; j++) {
                    int ny = y + dy[j];
                    int nx = x + dx[j];

                    if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
                        System.out.println(time + 1);
                        return true;
                    }

                    if (ny >= 0 && nx >= 0 && ny < r && nx < c
                            && maze[ny][nx] == '.' && jihoon[ny][nx] == 0
                            && (fire[ny][nx] == 0 || fire[ny][nx] > time + 1)) {
                        jihoon[ny][nx] = time + 1;
                        jihoonq.add(new int[] { ny, nx, time + 1 });
                    }
                }
            }
        }

        return false;
    }
}
