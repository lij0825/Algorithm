import java.util.*;
import java.io.*;

class Main {

    // 0 부터 7 까지 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int maxSum = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[4][4];
        ArrayList<Fish> fishes = new ArrayList<>();

        //
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                Fish f = new Fish();
                f.id = Integer.parseInt(st.nextToken());
                f.dir = Integer.parseInt(st.nextToken()) - 1;
                f.x = i;
                f.y = j;

                fishes.add(f);
                arr[i][j] = f.id;
            }
        }

        // 번호순 정렬
        Collections.sort(fishes, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.id - o2.id;
            }

        });

        // 상어는 (0, 0) 물고기를 먹고 시작하며 위치는 -1 로 표현
        Fish f = fishes.get(arr[0][0] - 1);
        Shark shark = new Shark(0, 0, f.dir, f.id);
        f.isAlive = false;
        arr[0][0] = -1;

        //
        dfs(arr, shark, fishes);

        System.out.println(maxSum);
    }

    //
    static void dfs(int[][] arr, Shark shark, ArrayList<Fish> fishes) {
        // 잡아먹은 양의 최대값 구하기
        if (maxSum < shark.eatSum) {
            maxSum = shark.eatSum;
        }

        // 모든 물고기 순서대로 이동
        for (Fish e : fishes) {
            moveFish(e, arr, fishes);
        }
        // 상어 몇칸 갈지
        for (int dist = 1; dist < 4; dist++) {
            int nx = shark.x + dx[shark.dir] * dist;
            int ny = shark.y + dy[shark.dir] * dist;
            // 범위 안쪽이고 물고기 있으면
            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > 0) {
                // 물고기 잡아먹고 dfs
                int[][] arrCopies = copyArr(arr);
                ArrayList<Fish> fishCopies = copyFishes(fishes);
                arrCopies[shark.x][shark.y] = 0;
                Fish f = fishCopies.get(arr[nx][ny] - 1);
                Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum + f.id);
                f.isAlive = false;
                arrCopies[f.x][f.y] = -1;
                // 재귀
                dfs(arrCopies, newShark, fishCopies);
            }
        }
    }

    // 물고기 옮기기
    static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        // 죽었으면 리턴
        if (fish.isAlive == false) {
            return;
        }
        // 8방 탐색
        for (int i = 0; i < 8; i++) {
            int nextDir = (fish.dir + i) % 8;
            int nx = fish.x + dx[nextDir];
            int ny = fish.y + dy[nextDir];
            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > -1) {
                arr[fish.x][fish.y] = 0;
                if (arr[nx][ny] == 0) {
                    fish.x = nx;
                    fish.y = ny;
                } else {
                    Fish temp = fishes.get(arr[nx][ny] - 1);
                    temp.x = fish.x;
                    temp.y = fish.y;
                    arr[fish.x][fish.y] = temp.id;
                    fish.x = nx;
                    fish.y = ny;
                }
                arr[nx][ny] = fish.id;
                fish.dir = nextDir;
                return;
            }
        }
    }

    // 리스트 복사
    static int[][] copyArr(int[][] arr) {
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        return temp;
    }

    static ArrayList<Fish> copyFishes(ArrayList<Fish> fishes) {
        ArrayList<Fish> temp = new ArrayList<>();
        for (Fish f : fishes) {
            temp.add(new Fish(f.x, f.y, f.id, f.dir, f.isAlive));
        }
        return temp;
    }

    static class Shark {
        int x, y, dir, eatSum;

        Shark() {
        }

        Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }

    static class Fish {
        int x, y, id, dir;
        boolean isAlive = true;

        Fish() {
        }

        Fish(int x, int y, int id, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }
}