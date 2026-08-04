class Solution {
    public List<Integer> findMissingElements(int[] nums) {

        int max = nums[0];
        int min = nums[0];

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            if(nums[i] > max){
                max = nums[i];
            }
            if(nums[i] < min){
                min = nums[i];
            }
        }
       
        List<Integer> answer = new ArrayList<>();
        for (int i = min; i < max; i++) {
            if (!set.contains(i)) {
                answer.add(i);
            }
        }
        return answer;
    }
}