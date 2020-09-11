public class LCS {

    int[][] tabulation;

    public int findLCS(String s1, String s2){
        int result = 0;
        tabulation = new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){
                if(i==0 || j==0){
                    tabulation[i][j] = 0;
                }else if(s1.charAt(i-1) == s2.charAt(j-1)){ // base condition to check
                    tabulation[i][j] = 1 + tabulation[i-1][j-1];
                }else{
                    tabulation[i][j] = Integer.max(tabulation[i-1][j], tabulation[i][j-1]);
                }
            }
        }
        result = tabulation[s1.length()][s2.length()];
        return result;
    }

    String printLCS(String X, String Y, int m, int n) {
        int result = findLCS(X,Y);
        Stack<Character> q = new Stack<>();
        if(result>0){
            int i = m;
            int j = n;

            while(i!=0 && j!=0){
                if(X.charAt(i-1) == Y.charAt(j-1)){
                    q.push(X.charAt(i-1));
                    i--;
                    j--;
                }else {
                    if(tabulation[i-1][j] >= tabulation[i][j-1]){
                        i--;
                    }else{
                        j--;
                    }
                }
            }
        }
        StringBuilder str = new StringBuilder();
        while (!q.isEmpty()){
            str.append(q.pop());
        }
        return str.toString();
    }

    public static void main(String[] args) {
        LCS lcs = new LCS();
        String s1 = "long";
        String s2 = "strong";
        System.out.println(lcs.printLCS(s1, s2, s1.length(), s2.length()));
    }
}
