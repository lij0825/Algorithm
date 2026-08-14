class Solution {
    public int maximumLengthSubstring(String s) {

        HashMap<Character, Integer> map = new HashMap<>(); // 알파벳, 갯수 저장용
        char[] str = s.toCharArray();
        int N = str.length; // 배열 길이

        // 좌우 포인터
        int left = 0;
        int right = 0;
        int maxlen = 0; // 최대 길이

        while (right < N) {
            char rChar = str[right];
            map.put(rChar, map.getOrDefault(rChar, 0) + 1);

            while (map.get(rChar) > 2) {
                char lChar = str[left];
                map.put(lChar, map.get(lChar) - 1);
                left++;
            }

            maxlen = Math.max(maxlen, right - left + 1);

            right++;
        }

        return maxlen;
    }
}