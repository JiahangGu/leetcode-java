//BFS
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int []degree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0;i < numCourses;i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0;i < prerequisites.length;i++) {
            degree[prerequisites[i][0]]++;
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for (int i = 0;i < numCourses;i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            visited++;
            int c = queue.poll();
            for (Integer t : edges.get(c)) {
                degree[t]--;
                if (degree[t] == 0) {
                    queue.offer(t);
                }
            }
        }
        return visited == numCourses;
    }
}

//DFS
class Solution {
    boolean flag;
    int []visited;
    List<List<Integer>> edges;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        edges = new ArrayList<>();
        for (int i = 0;i < numCourses;i++) {
            edges.add(new ArrayList<>());
        }
        flag = true;
        for (int []info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0;i < numCourses && flag;i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return flag;
    }

    private void dfs(int i) {
        visited[i] = 1;
        for (int x : edges.get(i)) {
            if (visited[x] == 1) {
                flag = false;
                return;
            }
            if (visited[x] == 0) {
                dfs(x);
            }
        }
        visited[i] = 2;
    }
}