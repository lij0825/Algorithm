class Solution {
    public String solution(String video_len, String pos, 
                           String op_start, String op_end, 
                           String[] commands) {
        
        int videoTotal = toSeconds(video_len);
        int posTotal = toSeconds(pos);
        int opStartTotal = toSeconds(op_start);
        int opEndTotal = toSeconds(op_end);


        if (posTotal >= opStartTotal && posTotal <= opEndTotal) {
            posTotal = opEndTotal;
        }

        for (String cmd : commands) {
            if (cmd.equals("prev")) {
                posTotal -= 10;
                if (posTotal < 0) {
                    posTotal = 0;
                }
            } else if (cmd.equals("next")) {
                posTotal += 10;
                if (posTotal > videoTotal) {
                    posTotal = videoTotal;
                }
            }

            if (posTotal >= opStartTotal && posTotal <= opEndTotal) {
                posTotal = opEndTotal;
            }
        }

        int finalMin = posTotal / 60;
        int finalSec = posTotal % 60;
        
        return String.format("%02d:%02d", finalMin, finalSec);
    }

    private int toSeconds(String timeStr) {
        String[] split = timeStr.split(":");
        int min = Integer.parseInt(split[0]);
        int sec = Integer.parseInt(split[1]);
        return min * 60 + sec;
    }
}