import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int z, ans = Integer.MAX_VALUE;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        z = m > n ? m : n;
        board = new int[z][z];
        // -1 채우기
        for (int[] is : board) {
            Arrays.fill(is, -1);
        }
        // 입력 0 빈칸, 1 빨공, 2 파공, 3 구멍
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                String token = str[j];
                if (token.equals(".")) {
                    board[i][j] = 0;
                }
                if (token.equals("R")) {
                    board[i][j] = 1;
                }
                if (token.equals("B")) {
                    board[i][j] = 2;
                }
                if (token.equals("O")) {
                    board[i][j] = 3;
                }
            }
        }
        //
        rolling("", 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    // 굴리기
    static void rolling(String before, int depth) {

        int chkBall = checkball();
        // 최대값 갱신, 리턴
        if (chkBall == 1) {
            ans = Math.min(ans, depth);
            return;
        }
        // 리턴
        if (chkBall == -1 || depth == 10 || depth > ans) {
            return;
        }
        // 돌리기 전 배열 저장
        int[][] temp = new int[z][z];
        for (int i = 0; i < z; i++) {
            temp[i] = board[i].clone();
        }
        // 위쪽
        if (!before.equals("U")) {
            rolling(upside(), depth + 1);
            for (int i = 0; i < z; i++) {
                board[i] = temp[i].clone();
            }
        }
        // 아래쪽
        if (!before.equals("D")) {
            rolling(downside(), depth + 1);
            for (int i = 0; i < z; i++) {
                board[i] = temp[i].clone();
            }
        }
        // 오른쪽
        if (!before.equals("R")) {
            rolling(rightside(), depth + 1);
            for (int i = 0; i < z; i++) {
                board[i] = temp[i].clone();
            }
        }
        // 왼쪽
        if (!before.equals("L")) {
            rolling(leftside(), depth + 1);
            for (int i = 0; i < z; i++) {
                board[i] = temp[i].clone();
            }
        }
    }

    // 왼쪽으로 굴리기
    static void pushLeft() {
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < z; j++) {
                if (board[i][j] == -1 || board[i][j] == 3)
                    continue;
                if (board[i][j] == 1 || board[i][j] == 2) {
                    int startIdx = j;
                    while (true) {
                        if (board[i][startIdx - 1] == 0) {
                            swap(i, startIdx, startIdx - 1);
                            startIdx--;
                        } else if (board[i][startIdx - 1] == 3) {
                            board[i][startIdx] = 0;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    // 공 검사
    static int checkball() {
        int redcnt = 0, bluecnt = 0;
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < z; j++) {
                if (board[i][j] == 1) {
                    redcnt++;
                }
                if (board[i][j] == 2) {
                    bluecnt++;
                }
            }
        }
        if (bluecnt != 1) {
            return -1;
        } else if (redcnt != 1) {
            return 1;
        }
        return 0;
    }

    // 공 스왑하는 연산
    static void swap(int y, int x1, int x2) {
        int tmp = board[y][x1];
        board[y][x1] = board[y][x2];
        board[y][x2] = tmp;
    }

    // 위쪽
    static String upside() {
        spin();
        pushLeft();
        spin();
        spin();
        spin();

        return "U";
    }

    // 아래쪽
    static String downside() {
        spin();
        spin();
        spin();
        pushLeft();
        spin();

        return "D";
    }

    // 오른쪽
    static String rightside() {
        spin();
        spin();
        pushLeft();
        spin();
        spin();

        return "R";
    }

    // 왼쪽
    static String leftside() {
        pushLeft();
        return "L";
    }

    // 반시계 방향 90 도
    static void spin() {
        int[][] temp = new int[z][z];
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < z; j++) {
                temp[j][z - 1 - i] = board[i][j];
            }
        }
        for (int i = 0; i < z; i++) {
            board[i] = temp[i].clone();
        }
    }

}