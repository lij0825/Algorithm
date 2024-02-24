import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int ans = 0; // max로 최대값 찾기
    static int outCnt = 0; // outCnt가 3이 되면 0으로 바꿔주고 다음 이닝 계산
    static ArrayList<int[]> playerCombie = new ArrayList<>(); // 선수 순서

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); // 이닝수

        int[][] player = new int[n][9]; // [이닝 0 - n][선수 번호]
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<Integer> q = new ArrayDeque<>(); // 선수 인덱스 순서대로
        Queue<Integer> base = new ArrayDeque<>(); // 누적으로 더해주고 4 되면 score+1하고 삭제
        int score = 0;
        int[] playerNum = new int[8];
        int player1 = 0; // 1번
        for (int j = 1; j < 9; j++) {
            playerNum[j - 1] = j;
        }
        playerCombie(playerNum);
        for (int j = 0; j < playerCombie.size(); j++) { // 선수 세우기
            // 선수 인덱스 순서 저장 1번 4번으로 고정
            for (int k = 0; k < playerCombie.get(j).length; k++) {
                if (k == 3) {
                    q.add(player1);
                }
                q.add(playerCombie.get(j)[k]);
            }
            int i = 0; // 이닝
            int sum = 0;
            while (true) {
                int idx = q.poll();
                q.add(idx);
                int act = player[i][idx];
                int bsize = base.size();
                if (act == 0) { // 아웃이면
                    outCnt += 1; // 아웃카운트 +1
                    if (outCnt == 3) { // 3아웃 되면
                        outCnt = 0;
                        i += 1;
                        base.clear();
                        if (i == n) { // 마지막 이닝까지 끝나면
                            break;
                        }
                        continue;
                    }
                } else if (act == 4) {
                    sum += bsize + 1;
                    base.clear();
                } else {
                    if (!base.isEmpty()) {
                        for (int k = 0; k < bsize; k++) {
                            int home = base.poll() + act;
                            if (home >= 4) {
                                sum++;
                            } else {
                                base.add(home);
                            }
                        }
                    }
                    base.add(act);
                }
            }
            outCnt = 0;
            q.clear();
            base.clear();
            score = Math.max(score, sum);
        }
        System.out.println(score);
    }

    // 선수가 설수있는 모든 조합 뽑기
    static void playerCombie(int[] playerNum) {
        do {
            playerCombie.add(playerNum.clone());
        } while (np(playerNum));
    }

    static boolean np(int[] p) {
        int n = p.length;
        int i = n - 1;
        while (i > 0 && p[i - 1] >= p[i]) {
            --i;
        }
        if (i == 0) {
            return false;
        }
        int j = n - 1;
        while (p[i - 1] >= p[j]) {
            --j;
        }
        swap(p, i - 1, j);
        int k = n - 1;
        while (i < k) {
            swap(p, i++, k--);
        }
        return true;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}