//排序做法
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String c : strs) {
            char[] cs = c.toCharArray();
            Arrays.sort(cs);
            String key = new String(cs);
            List list = map.getOrDefault(key, new ArrayList<>());
            list.add(c);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}

//统计字符次数做法
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String c : strs) {
            int []cnt = new int[26];
            for (int i = 0;i < c.length();i++) {
                cnt[c.charAt(i) - 'a']++;
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0;i < 26;i++) {
                stringBuffer.append((char) ('a' + i));
                stringBuffer.append(cnt[i]);
            }
            String key = stringBuffer.toString();
            List list = map.getOrDefault(key, new ArrayList<>());
            list.add(c);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}