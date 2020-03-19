package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class FourSumIII {
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        int count = 0;
        List<Integer> lst1 = new ArrayList<>();
        List<Integer> lst2 = new ArrayList<>();

        for (int i : B) {

            for (int j : A) {
                lst1.add(i + j);
            }
        }

        for (int i : C) {
            for (int j : lst1) {
                lst2.add(i + j);
            }
        }

        for (int i : lst2) {

            for (int j : D) {
                if (i + j == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int[] A = new int[]{
                1, 2
        };
        int[] B = new int[]{
                -2, -1
        };
        int[] C = new int[]{
                -1, 2
        };
        int[] D = new int[]{
                0, 2
        };
        System.out.println(fourSumCount(A, B, C, D));
    }
}
