import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(String[][] bt) {
        
        int[][] pt = new int[bt.length][2];
        
        for(int i = 0;i< bt.length;i++){
            int start = chgTime(bt[i][0]);
            int end = chgTime(bt[i][1])+10;
            pt[i][0] = start;
            pt[i][1] = end;
        }
        
        Arrays.sort(pt, (a,b) -> a[0] - b[0]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int[] time : pt) {
            if (!pq.isEmpty() && pq.peek() <= time[0]) {
                pq.poll();
            }
            pq.add(time[1]); 
        }
        
        return pq.size();

    }
    
    private int chgTime(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return hour * 60 + minute;
    }
}