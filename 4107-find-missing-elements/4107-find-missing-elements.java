class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        list.sort((a, b) -> a - b);
        int max = list.get(nums.length - 1);
        int min = list.get(0);
        List<Integer> answer = new ArrayList<>();
        for (int i = min + 1; i < max; i++) {
            if (!list.contains(i)) {
                answer.add(i);
            }
        }
        return answer;
    }
}