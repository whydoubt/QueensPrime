public class App {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Queens q = new Queens(N);
        q.solve();
        q.reportAlgebraic();
    }
}
