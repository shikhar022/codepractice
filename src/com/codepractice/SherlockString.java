package com.codepractice;

import java.util.HashMap;
import java.util.Map;

public class SherlockString {
    private static int alternatingSequence(String s){
        int deleted = 0;
        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i+1) == s.charAt(i)){
                deleted+=1;
            }
        }
        return deleted;
    }

    private static String isValid(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(!freqMap.containsKey(s.charAt(i))){
                freqMap.put(s.charAt(i), 1);
            }else{
                freqMap.put(s.charAt(i), freqMap.get(s.charAt(i))+1);
            }
        }

        Integer minCount = freqMap.get(s.charAt(s.length()-1));
        Integer maxCount = freqMap.get(s.charAt(s.length()-1));
        Map<Integer,Integer> countOfFreq = new HashMap<>();
        for(Map.Entry<Character,Integer> freq : freqMap.entrySet()){
            int value = freq.getValue();
            if(countOfFreq.containsKey(value)){
                countOfFreq.put(value, countOfFreq.get(value)+1);
            }else{
                countOfFreq.put(value, 1);
            }

            if(value < minCount){ // replace minCount if current entry set value is smaller than minCount
                minCount = value;
            }
            if(value > maxCount){ // replace maxCount if current entry set value is greater than maxCount
                maxCount = value;
            }
        }

        if(countOfFreq.size()==1){
            return "YES";
        }else if(countOfFreq.size()==2){
            if(countOfFreq.get(maxCount)==1 && maxCount - minCount == 1){ // aaabbbcccc -> [3:2,4:1]
                return "YES";
            } else if(countOfFreq.get(minCount)==1 && minCount==1){ // aaabbbc -> {1:1,3:2}
                return "YES";
            }else{
                return "NO";
            }
        }else {
            return "NO";
        }
    }


    public static void main(String[] args) {
        System.out.println(isValid("abbac"));
    }

}
