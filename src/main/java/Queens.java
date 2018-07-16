public class Queens {
    private int N;
    private boolean attempted = false;
    private boolean solved = false;
    private boolean[][] toConsider;
    private int[] current;

    Queens(int N) {
        this.N = N;
        current = new int [N];
        toConsider = new boolean [N][N];
    }

    public void solve() {
        attempted = true;
        int i;
        int row = 0;
        // Ignore about half of possible solutions, as they are
        // mirrors of solutions already tested.
        for (i = 0; i < (N+1)/2; i++) {
            toConsider[row][i] = true;
        }
        for (; i < N; i++) {
            toConsider[row][i] = false;
        }

        for (i = 0; i < N; i++) {
            if (toConsider[row][i]) {
                current[row] = i;
                if (consider(row + 1)) {
                    solved = true;
                    break;
                }
            }
        }
    }

    private boolean consider(int row) {
        int i;

        // initialize set to consider to true
        for (i = 0; i < N; i++) {
            toConsider[row][i] = true;
        }
        for (i = 0; i < row; i++) {
            // remove occupied columns
            toConsider[row][current[i]] = false;
            // remove occupied diagonals
            int diagColumn1 = current[i] + (row - i);
            int diagColumn2 = current[i] - (row - i);
            if (diagColumn1 < N) {
                toConsider[row][diagColumn1] = false;
            }
            if (diagColumn2 >= 0) {
                toConsider[row][diagColumn2] = false;
            }
        }
        // remove cases where two queens already lie on the slope
        slopeCheck: for (i = 0; i < N; i++) {
            if (toConsider[row][i]) {
                for (int rowPrevious1 = 0; rowPrevious1 < row; rowPrevious1++) {
                    int rowDelta = row - rowPrevious1;
                    int colDelta = i - current[rowPrevious1];
                    for (int rowPrevious2 = 0; rowPrevious2 < rowPrevious1; rowPrevious2++) {
                        int product1 = rowDelta * (i - current[rowPrevious2]);
                        int product2 = (row - rowPrevious2) * colDelta;
                        if (product1 == product2) {
                            toConsider[row][i] = false;
                            continue slopeCheck;
                        }
                    }
                }
            }
        }

        for (i = 0; i < N; i++) {
            if (toConsider[row][i]) {
                current[row] = i;
                if (row + 1 == N || consider(row + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void report() {
        if (!attempted) {
            System.out.println("Warning: no attempt to solve was made.");
            return;
        }

        System.out.format("For a %dx%d grid, placing %d queens, where\n", N, N, N);
        System.out.println("  no two queens share a row,");
        System.out.println("  no two queens share a column,");
        System.out.println("  no two queens share a diagonal, and");
        System.out.println("  no three queens share a sloped line,");

        if (!solved) {
            System.out.println("  NO solution was found.");
            return;
        } else {
            System.out.println("  a solution WAS found.");
        }

        System.out.println();
        for (int i = 0; i < N; i++) {
            if (current[i] < 26) {
                System.out.format(" %c%d", 'a' + current[i], i+1);
            } else {
                System.out.format(" %c%c%d", 'a' + current[i]/26 - 1, 'a' + current[i]%26, i+1);
            }
        }
        System.out.println();

        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((current[N - i - 1] == j) ? "Q" : "-");
            }
            System.out.println();
        }
    }
}
