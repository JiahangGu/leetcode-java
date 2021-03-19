class Solution {
    public int leastInterval(char[] tasks, int n) {
        int []freq = new int[26];
        for (int i = 0;i < tasks.length;i++) {
            freq[tasks[i] - 'A']++;
        }
        int i = 0, time = 0;
        Arrays.sort(freq);
        while (freq[25] > 0) {
            i = 0;
            while (i <= n) {
                if (freq[25] == 0) {
                    break;
                }
                if (i < 26 && freq[25 - i] == 0) {
                    time += n - i + 1;
                    break;
                }
                if (i < 26 && freq[25 - i] > 0) {
                    freq[25 - i]--;
                }
                time++;
                i++;
            }
            Arrays.sort(freq);
        }
        return time;
    }
}