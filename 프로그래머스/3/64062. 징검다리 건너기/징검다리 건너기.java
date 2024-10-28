import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        
        int[] copy = stones.clone();
        Arrays.sort(copy);
        
        int min = 1;
        int max = copy[copy.length - 1];
        
        while (min <= max) {
            int mid = (min + max) / 2;

            if (check(stones, k, mid)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return min;
    }
    
    public static boolean check(int[] stones, int k, int mid) {
        int jump = 0;

        for (int i = 0; i < stones.length; i++) {
            
            if (stones[i] <= mid ) {
                jump++;
            } else {
                jump = 0;
            }

            if (jump >= k) {
                return false;
            }
        }

        return true;
    }
}
