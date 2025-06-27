import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static final Set<Integer> set = new HashSet<>();
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int x = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;

            switch (cmd) {
                case "add":    add(x);    break;
                case "remove": remove(x); break;
                case "check":  check(x);  break;
                case "toggle": toggle(x); break;
                case "all":    all();     break;
                case "empty":  empty();   break;
            }
        }
        System.out.print(sb.toString());
    }

    public static void add(int x)      { set.add(x); }
    public static void remove(int x)   { set.remove(x); }
    public static void check(int x)    { sb.append(set.contains(x) ? "1\n" : "0\n"); }
    public static void toggle(int x)   { if (set.contains(x)) set.remove(x); else set.add(x); }
    public static void all()           { set.clear(); for (int i = 1; i <= 20; i++) set.add(i); }
    public static void empty()         { set.clear(); }
}