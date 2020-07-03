package edu.codepractice;

public class ReverseInChunks {
    public static void main(String[] args) {
        String[] arr = new String[]{"sas", "srk", "hrx", "pqrs", "rpd", "trs", "klj", "jhg", "qqq", "aaa"};
        reverseInChunks(arr, 3);
    }

    private static void reverseInChunks(String[] arr, int chunkLength) {
        String[] reversedList = new String[arr.length];
        int chunks = (arr.length / chunkLength);
        if (arr.length % chunkLength > 0) {
            chunks += 1;
        }

        int index = 0;
        for (int i = 1; i <= chunks; i++) {
            int j = ((i * chunkLength) - 1);
            int count = 0;
            while (count < chunkLength) {
                if (j < arr.length) {
                    reversedList[index] = arr[j];
                    index++;
                }
                count++;
                j--;
            }
        }

        for (String str : reversedList) {
            System.out.println(str);
        }
    }

}
