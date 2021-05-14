import java.io.*;
import java.util.*;

public class Test implements Runnable {

    void solve() throws IOException {
        TreeSet<Pair> set = new TreeSet<>();
        set.add(new Pair(1, 1));
        set.add(new Pair(2, 2));
        set.add(new Pair(3, 3));
        set.add(new Pair(4, 4));
        set.add(new Pair(5, 5));
        set.add(new Pair(2, 6));

//        System.out.println(Math.ceil((double)-3/2));
//        System.out.println(Math.ceil((double) 3/2));
//        set.forEach(o -> System.out.println(o.x + " " + o.y));
//               System.out.println("**********************************");
//        set.remove(new Pair(2, 2));
//        set.forEach(o -> System.out.println(o.x + " " + o.y));
//        System.out.println("**********************************");
        set.tailSet(new Pair(2, 0)).forEach(o -> System.out.println(o.x + " " + o.y));
//        System.out.println(set.tailSet(new Pair(2, 3)).size());
//        System.out.println(set.ceiling(new Pair(2, 0)).y);
    }

    static class Pair implements Comparable<Pair> {
        int x, y;

        public Pair(int xx, int yy) {
            x = xx;
            y = yy;
        }

        @Override
        public int compareTo(Pair p) {
            if (p.x == x) return Integer.compare(y, p.y);
            return Integer
                    .compare(x, p.x);
        }
    }

    class Dsu {
        int[] parent;
        int[] size;
        long[] asphalt;
        ArrayDeque<int[]>[] edges;

        public Dsu(int n, long[] asphalt, ArrayDeque<int[]>[] edges) {
            this.edges = edges;
            this.asphalt = asphalt;
            parent = iArr(n);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            size = iArr(n);
            Arrays.fill(size, 1);
        }

        public int find(int a) {
            if (parent[a] == a)
                return a;
            return parent[a] = find(parent[a]);
        }

        public boolean join(int a, int b) {
            int parent1 = find(a), parent2 = find(b);
            if (parent1 == parent2)
                return false;
            if (size[parent1] < size[parent2]) {
                parent1 = parent1 ^ parent2 ^ (parent2 = parent1);
            }
            size[parent1] += size[parent2];
            asphalt[parent1] += asphalt[parent2];
            parent[parent2] = parent[parent1];
            for (int[] adj : edges[parent2]) {
                edges[parent1].add(adj);
            }
            edges[parent2].clear();
            return true;
        }
    }

    static class TMultiset<T extends Number> extends TreeMap<T, Integer> {
        private int size = 0;

        void add(T value) {
            Integer count = get(value);
            size++;
            if (count == null) {
                put(value, 1);
            } else {
                put(value, count + 1);
            }
        }

        @SuppressWarnings(value = "unchecked")
        @Override
        public Integer remove(Object key) {
            if (!containsKey(key)) {
                return null;
            }

            size--;

            Integer value = get(key);
            if (value > 1) {
                return put((T) key, value - 1);
            }

            return super.remove(key);
        }

        @java.lang.Override
        public int size() {
            return size;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new Test(), "1").start();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;

    @Override
    public void run() {
        try {
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    static class Reader {
        private byte[] buf = new byte[1024];
        private int index;
        private InputStream in;
        private int total;

        public Reader() {
            in = System.in;
        }

        public int scan() throws IOException {
            if (total < 0)
                throw new InputMismatchException();
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0)
                    return -1;
            }
            return buf[index++];
        }

        public int intNext() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else
                    throw new InputMismatchException();
            }
            return neg * integer;
        }

        public double doubleNext() throws IOException {
            double doub = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n) && n != '.') {
                if (n >= '0' && n <= '9') {
                    doub *= 10;
                    doub += n - '0';
                    n = scan();
                } else
                    throw new InputMismatchException();
            }
            if (n == '.') {
                n = scan();
                double temp = 1;
                while (!isWhiteSpace(n)) {
                    if (n >= '0' && n <= '9') {
                        temp /= 10;
                        doub += (n - '0') * temp;
                        n = scan();
                    } else
                        throw new InputMismatchException();
                }
            }
            return doub * neg;
        }

        public String read() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            while (!isWhiteSpace(n)) {
                sb.append((char) n);
                n = scan();
            }
            return sb.toString();
        }

        private boolean isWhiteSpace(int n) {
            if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
                return true;
            return false;
        }
    }

    static void shuffle(int[] aa) {
        int n = aa.length;
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
    }

    static void shuffle(int[][] aa) {
        int n = aa.length;
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int first = aa[i][0];
            int second = aa[i][1];
            aa[i][0] = aa[j][0];
            aa[i][1] = aa[j][1];
            aa[j][0] = first;
            aa[j][1] = second;
        }
    }

    // Gives strict lowerBound that previous number would be smaller than the target
    int lowerBound(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] >= val) {
                r = mid;
            } else
                l = mid + 1;
        }
        return l;
    }

    // Gives strict upperBound that next number would be greater than the target
    int upperBound(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (r + l + 1) >> 1;
            if (arr[mid] <= val) {
                l = mid;
            } else
                r = mid - 1;
        }
        return l;
    }

    static void print(Object object) {
        out.print(object);
    }

    static void println(Object object) {
        out.println(object);
    }

    static int[] iArr(int len) {
        return new int[len];
    }

    static long[] lArr(int len) {
        return new long[len];
    }

    static long min(long a, long b) {
        return Math.min(a, b);
    }

    static int min(int a, int b) {
        return Math.min(a, b);
    }

    static long max(Long a, Long b) {
        return Math.max(a, b);
    }

    static int max(int a, int b) {
        return Math.max(a, b);
    }

    static int ri() throws IOException {
        return read.intNext();
    }

    static long rl() throws IOException {
        return Long.parseLong(read.read());
    }

    static String rs() throws IOException {
        return read.read();
    }

    static char rc() throws IOException {
        return rs().charAt(0);
    }

    static double rd() throws IOException {
        return read.doubleNext();
    }
}
