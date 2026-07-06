class Solution {
    public int solution(int[][] triangle){
        
        int height = triangle.length; // 삼각형 높이 
        if(height == 1){ //높이가 1이면 바로 반환
            return triangle[0][0];
        }
        
        int[][] sum = new int[height][]; // 합 저장용
        for(int i = height-1;i >= 0;i--){
            sum[i] = new int[i+1];
        }
        
        sum[height-1] = triangle[height-1]; // 마지막줄 복사
        
        for(int i = height-1;i >= 0;i--){ // 아래부터 연산                
            for(int j = 0;j < i;j++){
                if(sum[i][j] < sum[i][j+1]){ // 크기 비교 후 합
                    sum[i-1][j] = triangle[i-1][j] + sum[i][j+1];
                } else {
                    sum[i-1][j] = triangle[i-1][j] + sum[i][j];
                }    
            }
        }
        
        return sum[0][0];
    }
}