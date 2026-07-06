import java.io.*;
import java.util.*;

class Solution {
    
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    List<int[]> moveLog = new ArrayList<>();
    
    public int[][] solution(int n) throws Exception {
        
    
        hanoi(n, 1, 2, 3);
        
        bw.write(moveLog.size());
        bw.flush();
        bw.close();
    
        int[][] answer = new int[moveLog.size()][2];
        for (int i = 0; i < moveLog.size(); i++) {
            answer[i] = moveLog.get(i);
        }
        
        return answer;
    }
    
    private void hanoi(int n, int first, int second, int third) {
        
        if (n == 1) {
            moveLog.add(new int[]{first, third});
            return;
        }
        
        hanoi(n - 1, first, third, second);
        
        moveLog.add(new int[]{first, third});
        
        hanoi(n - 1, second, first, third);
    }
}