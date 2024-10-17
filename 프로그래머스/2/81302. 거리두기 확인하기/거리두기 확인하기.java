import java.util.*;

class Solution {
    
    public int[] solution(String[][] places) {
        
        int[] ans = new int[5];
        
        for (int i = 0; i < 5; i++) {
            if (solve(places[i])) {
                ans[i] = 1;
            } else {
                ans[i] = 0;
            }
        }
        
        return ans;
    }
    
    static boolean solve(String[] places) {
        
        // board에 옮겨담기
        String[][] board = new String[5][5];
        
        for (int i = 0; i < 5; i++) {
            char[] temp = places[i].toCharArray();
            for (int j = 0; j < 5; j++) {
                board[i][j] = String.valueOf(temp[j]);
            }
        }
        
        // 거리두기 체크
        return check(board);
    }
    
    static boolean check(String[][] board) {
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        
        System.out.println();
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].equals("P")) {
                    if (!bfs(board, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    static boolean bfs(String[][] board, int y, int x) {
        
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        
        q.add(new int[]{y, x});
        visited[y][x] = true;
        
        while (!q.isEmpty()) {
            int[] yx = q.poll();
            int py = yx[0];
            int px = yx[1];
            
            for (int i = 0; i < 4; i++) {
                int ny = py + dy[i];
                int nx = px + dx[i];
                
                if (ny >= 0 && ny < 5 && nx >= 0 && nx < 5 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    
                    if (board[ny][nx].equals("X")) {
                        continue;
                    }
                    
                    if(Math.abs(y - ny) + Math.abs(x - nx) > 2){
                        continue;
                    }
                    
                    if (board[ny][nx].equals("P")) {
                        return false;
                    }
                    
                    if (board[ny][nx].equals("O")) {
                        q.add(new int[]{ny, nx});
                    }
                }
            }
        }
        return true;
    }
}
