package com.codepractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxSubSetArray {

    static int getMinimumCost(int k, int[] c) {
        Arrays.sort(c);
        Map<Integer, Integer> minKCosts = new HashMap<>();
        int cost = 0;
        int loop = (k/c.length) + (k%c.length);
        int weight = 0;
        int seed = 0;
        while(loop>0){
            for(int i = 0; i < k; i++){
                int index = i+(weight>1 ? seed : 0);
                if(seed==0){
                    index = c.length-index-1;
                }
                System.out.println("index="+index);
                if(index<c.length){
                    int kthCostTillNow = minKCosts.get(i)!=null ? minKCosts.get(i) : 0;

                    int weightedCost = (weight+1) * c[index];
                    System.out.println(":::::::::::::::::::::::::::");
                    System.out.println("w=" + (weight+1) + ", cost=" + c[index]);
                    System.out.println(weightedCost);
                    System.out.println(":::::::::::::::::::::::::::");
                    kthCostTillNow += weightedCost;
                    c[index] = 0;
                    minKCosts.put(i,  kthCostTillNow);
                }else{
                    break;
                }
            }
            seed += 3;
            weight += 1;
            loop -= 1;
        }

        for(Integer key : minKCosts.keySet()){
            //System.out.println(key + " :: " + minKCosts.get(key));
            cost += minKCosts.get(key);
        }

        return cost;
    }

    private static int[] reverseSorted(int[] c) {
        int left = 0;
        int right = c.length-1;
        while (left < right){
            int temp = c[left];
            c[left] = c[right];
            c[right] = temp;
            left++;
            right--;
        }
        return c;
    }

    public static void main(String[] args) {
        int k = 3;
        int[] c = {1, 3, 5, 7, 9};
        System.out.println(getMinimumCost(k, c));
    }

}
