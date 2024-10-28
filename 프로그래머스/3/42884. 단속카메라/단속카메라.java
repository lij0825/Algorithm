import java.util.*;

class Solution {
    
    public static int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        int min = -30001;
        
        for (int[] route : routes) {
            if (min < route[0]) {
                min = route[1];
                answer++;
            }
        }
        
        return answer;
    }
    
}
