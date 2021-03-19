//按照身高降序排列，以及序号升序，则每次将新的元素插入对应的序号即可，因为对前面的人不产生影响。
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                else {
                    return o2[0] - o1[0];
                }
            }
        });
        List<int []> ans = new ArrayList<>();
        for (int []p : people) {
            ans.add(p[1], p);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}