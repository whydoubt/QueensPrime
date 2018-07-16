public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Parameter must be provided for N");
            System.exit(1);
        }

        int N = 0;
        try {
            N = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Parameter for N must an integer");
            System.exit(1);

        }

        if (N < 2 || N > 36) {
            System.err.println("Only a value from 2 to 36 is supported");
            System.exit(1);
        }

        Queens q = new Queens(N);
        q.solve();
        q.report();
    }
}
