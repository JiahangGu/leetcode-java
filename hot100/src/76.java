/**
 * 最初的滑动窗口思想，使用指针l，r维护一个窗口，如果窗口中的字符没有包含给定字符则r右移，如果包含则在满足条件的情况下记录当前解并
 * 右移l，直到再次不包含，此时记录的解为当前的最短字符串，遍历完成时即可得到全局的最短字符串。时间复杂度为O(|s| * C)，
 * C为t集合大小
 */
class Solution {
    public String minWindow(String s, String t) {
        Map<Integer, Integer> temp = new HashMap<>();
        for (int i = 0;i < t.length();i++) {
            int idx = t.charAt(i) - '0';
            temp.put(idx, temp.getOrDefault(idx, 0) + 1);
        }
        Map<Integer, Integer> windows = new HashMap<>();
        int l = 0, r = 0, ansL = -1, ansR = -1;
        int min = 100010;
        while (r <= s.length()) {
            while (l < r) {
                if (r - l + 1 < min) {
                    ansL = l;
                    ansR = r;
                    min = r - l + 1;
                }
                int lIdx = s.charAt(l) - '0';
                if (temp)
                windows.put(lIdx, windows.get(lIdx) - 1);
                l++;
            }
            if (r == s.length()) break;
            int idx = s.charAt(r) - '0';
            windows.put(idx, windows.getOrDefault(idx, 0) + 1);
            r++;
        }
        if (ansL == -1) {
            return "";
        }
        return s.substring(ansL, ansR);
    }

    private boolean contain(Map<Integer, Integer> window, Map<Integer, Integer> temp) {
        for (Integer key : temp.keySet()) {
            if (window.getOrDefault(key, 0) < temp.get(key)) {
                return false;
            }
        }
        return true;
    }
}

/**
 * 对于上述解法虽然是O(n)，但是常数项比较大，存在优化空间。例如在给定字符串XXXXXABXXX中查找AB，则此前出现的5个X是无用的，
 * 可以直接移动而无需判断是否包含。只有在窗口中遇到了t中所含的字符才需要进行判断。运行时间有150ms减少为30ms
 */
class Solution {
    public String minWindow(String s, String t) {
        Map<Integer, Integer> temp = new HashMap<>();
        for (int i = 0;i < t.length();i++) {
            int idx = t.charAt(i) - '0';
            temp.put(idx, temp.getOrDefault(idx, 0) + 1);
        }
        Map<Integer, Integer> windows = new HashMap<>();
        int l = 0, r = 0, ansL = -1, ansR = -1;
        int min = 100010;
        int valid = 0;
        while (r < s.length()) {
            int idx = s.charAt(r) - '0';
            r++;
            if (temp.containsKey(idx)) {
                windows.put(idx, windows.getOrDefault(idx, 0) + 1);
                if (windows.get(idx).equals(temp.get(idx))) {
                    valid++;
                }
            }
            while (valid == temp.size()) {
                if (r - l < min) {
                    min = r - l;
                    ansL = l;
                    ansR = r;
                }
                int lIdx = s.charAt(l) - '0';
                l++;
                if (temp.containsKey(lIdx)) {
                    if (windows.get(lIdx).equals(temp.get(lIdx))) {
                        valid--;
                    }
                    windows.put(lIdx, windows.get(lIdx) - 1);
                }
            }
        }
        if (ansL == -1) {
            return "";
        }
        return s.substring(ansL, ansR);
    }
}