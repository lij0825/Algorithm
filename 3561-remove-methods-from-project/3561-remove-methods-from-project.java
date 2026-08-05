class Solution {

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        List<Integer>[] graph = new ArrayList[n];
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            graph[inv[0]].add(inv[1]);
            degree[inv[1]]++;
        }

        boolean[] visited = new boolean[n];
        int[] visitedDegree = new int[n];
        visited[k] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(k);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph[cur]) {
                visitedDegree[next]++;
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }

        boolean canRemove = true;
        for (int i = 0; i < n; i++) {
            if (visited[i] && visitedDegree[i] < degree[i]) {
                canRemove = false;
                break;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!canRemove || !visited[i]) {
                result.add(i);
            }
        }
        return result;

    }
}