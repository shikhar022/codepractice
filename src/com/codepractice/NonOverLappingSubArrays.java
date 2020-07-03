package edu.codepractice;

import java.util.Stack;

/*
*
* Find the max increasing sub array comprising of two max sub arrays in the input given of random integers
* For example [7,1,2,4,6,5,3,8,9,10] should return [1,2,4,6,8,9,10]
*
* */

public class NonOverLappingSubArrays {

    static int getSuperSubarray( int[] arr, int n){
        Stack<Integer> increasingStack = new Stack<>();
        int left = 0;
        int right = left+1;
        while(right < n){
            if(arr[right-1] > arr[right]){
                recordInterval(arr, left, right, increasingStack);
                left=right;
                right+=1;
            }else{
                right+=1;
            }
        }
        if(right>=arr.length){
            right = arr.length-1;
        }
        recordInterval(arr, left, right, increasingStack); // for remaining elements
        return increasingStack.size();
    }

    static void recordInterval(int arr[], int left, int right, Stack<Integer> stack){
        System.out.println("recording increasing sub Arr till now");
        System.out.println("left="+left+" right="+right);
        if(right-left <= 1 && arr[right] < arr[left]){ // consecutive elements with decreasing decent
            return;
        }

        for (int i=left; i<right; i++){
            if(stack.empty() || arr[i] > stack.peek()){
                System.out.println("pushing into stack :: " + arr[i]);
                stack.push(arr[i]);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Length of super increasing subArray :: " + getSuperSubarray(new int[]{1,2,3,4,6,5,8,9,10,7}, 10));
    }
}
