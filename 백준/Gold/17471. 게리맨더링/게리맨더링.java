import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

/**
 * Main_17471_게리맨더링
 */
public class Main {
    static ArrayList[] relation;
    static ArrayList[] copyrelation;
    static int[] parents;
    static int n;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 구역 개수
        n = Integer.parseInt(br.readLine());
        // 구역 인구수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] section = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            section[i] = Integer.parseInt(st.nextToken());
        }
        // 구역 관계
        relation = new ArrayList[n + 1];
        copyrelation = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            relation[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < temp; j++) {
                int e = Integer.parseInt(st.nextToken());
                relation[i].add(e);
            }
        }

        ArrayList<String> com = new ArrayList<>();
        for (int i = 1; i < 1 << n; i++) {
            for (int j = 1; j < n; j++) {
                if ((i & j) > 0 && !com.contains(Integer.toBinaryString(i))) {
                    com.add(Integer.toBinaryString(i));
                }
            }
        }

        ArrayList<Integer> redteam = new ArrayList<>();
        ArrayList<Integer> blueteam = new ArrayList<>();
        for (int i = 0; i < com.size(); i++) {
            for (int j = 0; j <= n; j++) {
                copyrelation[j] = new ArrayList<>(relation[j]);
            }
            int personRed = 0;
            int personBlue = 0;
            String[] str = com.get(i).split("");
            for (int j = 1; j <= str.length; j++) {
                if (str[j - 1].equals("1")) {
                    redteam.add(j);
                }
            }
            for (int j = 1; j <= n; j++) {
                if (!redteam.contains(j)) {
                    blueteam.add(j);
                }
            }

            if (redteam.size() == 0 || blueteam.size() == 0) {
                continue;
            }

            for (int j = 0; j < redteam.size(); j++) {
                int k = copyrelation[redteam.get(j)].size() - 1;
                while (!copyrelation[redteam.get(j)].isEmpty()) {
                    if (!redteam.contains(copyrelation[redteam.get(j)].get(k))) {
                        copyrelation[redteam.get(j)].remove(k);
                    }
                    k--;
                    if (k < 0) {
                        break;
                    }
                }
            }

            for (int j = 0; j < blueteam.size(); j++) {
                int k = copyrelation[blueteam.get(j)].size() - 1;
                while (!copyrelation[blueteam.get(j)].isEmpty()) {
                    if (!blueteam.contains(copyrelation[blueteam.get(j)].get(k))) {
                        copyrelation[blueteam.get(j)].remove(k);
                    }
                    k--;
                    if (k < 0) {
                        break;
                    }
                }
            }

            boolean canRed = true;

            for (int j = 0; j < redteam.size() - 1; j++) {
                for (int k = j + 1; k < redteam.size(); k++) {
                    visited = new boolean[n + 1];
                    if (!dfs(redteam.get(j), redteam.get(k))) {
                        canRed = false;
                    }
                }
            }

            boolean canBlue = true;
            for (int j = 0; j < blueteam.size() - 1; j++) {
                for (int k = j + 1; k < blueteam.size(); k++) {
                    visited = new boolean[n + 1];
                    if (!dfs(blueteam.get(j), blueteam.get(k))) {
                        canBlue = false;
                    }
                }
            }
            if (canRed && canBlue) {
                for (int j = 0; j < redteam.size(); j++) {
                    personRed += section[redteam.get(j)];
                }
                for (int j = 0; j < blueteam.size(); j++) {
                    personBlue += section[blueteam.get(j)];
                }
                min = Math.min(min, Math.abs(personRed - personBlue));
            }

            redteam.clear();
            blueteam.clear();
        }

        System.out.println(min < Integer.MAX_VALUE ? min : -1);
    }

    public static boolean dfs(int s, int e) {
        if (!copyrelation[s].isEmpty()) {
            for (int i = 0; i < copyrelation[s].size(); i++) {
                int ss = (int) copyrelation[s].get(i);
                if (!visited[ss]) {
                    visited[ss] = true; // 방문한 노드로 체크
                    if (ss == e || dfs(ss, e)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}