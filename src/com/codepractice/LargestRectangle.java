package edu.codepractice;

import java.util.Stack;

/*
* Complete the function largestRectangle int the editor below.
* It should return an integer representing the largest rectangle that can be formed
* within the bounds of consecutive buildings. largestRectangle has the following parameter(s):
* h: an array of integers representing building heights
*
* */

public class LargestRectangle {

    public static int largestRect(int[] h){
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> s = new Stack<>();

        int max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < h.length)
        {
            System.out.println("i="+i);
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || h[s.peek()] <= h[i]){
                System.out.println("Pushed into stack");
                s.push(i++);
            }else {
                tp = s.peek();  // store the top index
                s.pop();  // pop the top

                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = h[tp] * (s.empty() ? i : i - s.peek() - 1);

                // update max area, if needed
                if (max_area < area_with_top)
                    max_area = area_with_top;

                System.out.println("area_with_top = "+ area_with_top);
                System.out.println("max_area = "+ max_area);
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        System.out.println("=======================================");
        while (!s.empty())
        {
            tp = s.peek();
            s.pop();
            area_with_top = h[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
                max_area = area_with_top;
            System.out.println("area_with_top = "+ area_with_top);
            System.out.println("max_area = "+ max_area);
        }

        return max_area;
    }

    public static void main(String[] args) {
        System.out.println(largestRect(new int[]{3,2,3,4,5,6,7,4,5,1}));
    }

}
