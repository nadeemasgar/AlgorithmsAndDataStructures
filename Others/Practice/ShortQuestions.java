package Others.Practice;

import java.util.*;

public class ShortQuestions {
   public static void main(String[] args) {

   }

   boolean areTheyEqual(int[] array_a, int[] array_b) {
      // Write your code here
      if (array_a.length != array_b.length)
         return false;
      Arrays.sort(array_a);
      Arrays.sort(array_b);

      for (int i = 0; i < array_a.length; i++) {
         if (array_a[i] != array_b[i])
            return false;
      }
      return true;
   }

    int[] countSubarraysMaxPoss(int[] arr) {
        // Write your code here

        //First step is to find the max on both sides
        Stack<Integer> stack = new Stack<>();
        int len = arr.length;
        int[] left = new int[len], right = new int[len];

        for(int i = len - 1; i >= 0; i--){
            int curr = arr[i];
            while(!stack.isEmpty() && arr[stack.peek()] < curr) stack.pop();
            right[i] = (stack.isEmpty())? len: stack.peek();
            stack.push(i);
        }

        //We do similar thing for left
        stack.clear();
        for(int i = 0; i < len; i++){
            int curr = arr[i];
            while(!stack.isEmpty() && arr[stack.peek()] < curr) stack.pop();
            left[i] = (stack.isEmpty())? -1: stack.peek();
            stack.push(i);
        }

        //Compute the answer for every index
        int[] ans = new int[len];

        for(int i = 0; i < len; i++){
            int leftMax = left[i], rightMax = right[i];
            leftMax = i - leftMax;
            rightMax = rightMax - i;
            //Current number will be included twice
            ans[i] = leftMax + rightMax - 1;
        }
        return ans;
    }
}