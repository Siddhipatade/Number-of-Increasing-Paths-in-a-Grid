import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[][] grid = getInputMatrix();
        int count = countPaths(grid);
        System.out.println("Number of strictly increasing paths: " + count);
        showOutputPath(grid);
    }

    public static int[][] getInputMatrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int m = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int n = scanner.nextInt();

        int[][] grid = new int[m][n];
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        return grid;
    }

    public static void showOutputPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        List<List<int[]>> paths = new ArrayList<>();

        int[][] dp = new int[m][n];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, 1));

        int[][] cellList = new int[m * n][2];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int index = i * n + j;
                cellList[index][0] = i;
                cellList[index][1] = j;
            }
        }
        Arrays.sort(cellList, (a, b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);

        for (int[] cell : cellList) {
            int i = cell[0], j = cell[1];
            for (int[] d : directions) {
                int currI = i + d[0], currJ = j + d[1];
                if (0 <= currI && currI < m && 0 <= currJ && currJ < n && grid[currI][currJ] > grid[i][j]) {
                    dp[currI][currJ] += dp[i][j];
                    dp[currI][currJ] %= 1_000_000_007;
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int[] currCell = new int[]{i, j};
                List<int[]> path = new ArrayList<>();
                generatePath(grid, dp, currCell, path);
                paths.add(path);
            }
        }

        System.out.println("\nOutput Paths:");
        for (List<int[]> path : paths) {
            for (int[] cell : path) {
                System.out.print("(" + cell[0] + ", " + cell[1] + ") ");
            }
            System.out.println();
        }
    }

    public static void generatePath(int[][] grid, int[][] dp, int[] currCell, List<int[]> path) {
        int i = currCell[0];
        int j = currCell[1];
        path.add(currCell);

        if (dp[i][j] == 1) {
            return;
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] d : directions) {
            int currI = i + d[0], currJ = j + d[1];
            if (0 <= currI && currI < grid.length && 0 <= currJ && currJ < grid[0].length
                    && grid[currI][currJ] > grid[i][j] && dp[currI][currJ] == dp[i][j] - 1) {
                generatePath(grid, dp, new int[]{currI, currJ}, path);
                break;
            }
        }
    }

    public static int countPaths(int[][] grid) {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = grid.length, n = grid[0].length;
        int mod = 1_000_000_007;

        int[][] dp = new int[m][n];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, 1));

        int[][] cellList = new int[m * n][2];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int index = i * n + j;
                cellList[index][0] = i;
                cellList[index][1] = j;
            }
        }
        Arrays.sort(cellList, (a, b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);

        for (int[] cell : cellList) {
            int i = cell[0], j = cell[1];
            for (int[] d : directions) {
                int currI = i + d[0], currJ = j + d[1];
                if (0 <= currI && currI < m && 0 <= currJ && currJ < n
                        && grid[currI][currJ] > grid[i][j]) {
                    dp[currI][currJ] += dp[i][j];
                    dp[currI][currJ] %= mod;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                answer += dp[i][j];
                answer %= mod;
            }
        }
        return answer;
    }
}
