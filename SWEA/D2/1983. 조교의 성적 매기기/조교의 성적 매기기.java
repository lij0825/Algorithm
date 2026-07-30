import java.util.Scanner;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (!sc.hasNextInt()) return;
        
        int T = sc.nextInt();
        
        String[] grades = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt(); 
            int K = sc.nextInt(); 

            int[] scores = new int[N];
            int targetScore = 0;

            for (int i = 0; i < N; i++) {
                int mid = sc.nextInt();
                int fin = sc.nextInt();
                int asg = sc.nextInt();
                
                scores[i] = (mid * 35) + (fin * 45) + (asg * 20);
                
                if (i == K - 1) {
                    targetScore = scores[i];
                }
            }

            Arrays.sort(scores);

            int rank = 0; 
            for (int i = 0; i < N; i++) {
                if (scores[i] == targetScore) {
                    rank = N - 1 - i; 
                    break;
                }
            }

            int gradeIndex = rank / (N / 10);
            
            System.out.println("#" + t + " " + grades[gradeIndex]);
        }
        sc.close();
    }
}