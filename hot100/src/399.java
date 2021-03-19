class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double []ans = new double[queries.size()];
        int n = equations.size();
        UnionSet unionSet = new UnionSet(2 * n);
        Map<String, Integer> map = new HashMap<>();
        int idx = 0;
        for (int i = 0;i < n;i++) {
            List<String> tmp = equations.get(i);
            String a = tmp.get(0), b = tmp.get(1);
            if (!map.containsKey(a)) {
                map.put(a, idx++);
            }
            if (!map.containsKey(b)) {
                map.put(b, idx++);
            }
            unionSet.union(map.get(a), map.get(b), values[i]);
        }
        for (int i = 0;i < queries.size();i++) {
            List<String> tmp = queries.get(i);
            String a = tmp.get(0), b = tmp.get(1);
            if (!map.containsKey(a) || !map.containsKey(b)) {
                ans[i] = -1.0d;
            }
            else {
                ans[i] = unionSet.connect(map.get(a), map.get(b));
            }
        }
        return ans;
    }

    class UnionSet {
        int []parent;
        double []weight;

        public UnionSet(int n) {
            parent = new int[n];
            weight = new double[n];
            for (int i = 0;i < n;i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        private int find(int x) {
            if (parent[x] != x) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        private void union(int x, int y, double val) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return;
            }
            parent[px] = py;
            weight[px] = weight[y] * val / weight[x];
        }

        private double connect(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px != py) {
                return -1.0d;
            }
            else {
                return weight[x] / weight[y];
            }
        }
    }
}