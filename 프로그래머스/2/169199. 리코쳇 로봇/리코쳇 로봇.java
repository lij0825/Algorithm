import java.util.*;

class Solution {
    
    static Pair r = new Pair(-1, -1); // 출발지
    static Pair g = new Pair(-1, -1); // 목적지
    static int[] dy = {0, 0, 1, -1}, dx = {1, -1, 0, 0}; // 방향
    static String[][] board;
    static int[][] visited;
    static int N, M, answer = -1;
    
    public int solution(String[] input) {
        
        N = input.length; // 세로
        M = input[0].split("").length; // 가로
            
        board = new String[N][M];
        visited = new int[N][M];
        
        for(int i = 0;i < N;i++){
             for(int j = 0;j < M;j++){
                 board[i][j] = input[i].split("")[j];
                 // 출발지 초기화
                 if(board[i][j].equals("R")){
                     r.y = i;
                     r.x = j;
                 }
                 // 목적지 초기화
                 if(board[i][j].equals("G")){
                     g.y = i;
                     g.x = j;
                 }
             }
        }
        
        // 못가는 경우
        if(!check()){
            return answer;
        }
        
        answer = bfs() - 1;
        return answer;
    }
    
    static int bfs(){
        
        Queue<Pair> q = new LinkedList<>();
        q.add(r);
        visited[r.y][r.x] = 1;
        
        while(!q.isEmpty()){
            Pair p = q.poll();
            int py = p.y;
            int px = p.x;
            
            if(board[py][px].equals("G")){
                return visited[py][px];
            }
            
            for(int i = 0;i < 4;i++){
                int ny = py + dy[i];
                int nx = px + dx[i];
                
                // 이동할수 없을 때까지 직진
                while(isIn(ny, nx) && !board[ny][nx].equals("D")){
                    ny += dy[i];
                    nx += dx[i];
                }
                
                ny -= dy[i];
                nx -= dx[i];
                
                // 방문 체크
                if(visited[ny][nx] != 0){
                    continue;
                }
                
                q.add(new Pair(ny,nx));
                visited[ny][nx] = visited[py][px] + 1;
            }
            
        }
        
        return 0;
    }
    
    // 못가는 경우 검사
    static boolean check(){
        
        int cnt = 0;
        
        for(int i = 0;i < 4;i++){
            int ny = g.y + dy[i];
            int nx = g.x + dx[i];
            if(!isIn(ny, nx) || board[ny][nx].equals("D")){
                cnt++;
            }
        }
        
        // 전부 막혀있거나 멈출 수 없을 때
        if(cnt == 4 || cnt == 0){
            return false;
        }
        
        return true;
    }
    
    // 범위 검사
    static boolean isIn(int y, int x){
        return !(y < 0 || x < 0 || y >= N || x >= M);
    }
    
    // 좌표
    static class Pair{
        int y, x;
        
        public Pair(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
}