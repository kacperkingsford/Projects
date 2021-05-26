public class Main {
    public static int solution(int N) {
        int count = 0; //number of digits of number
        int K = N;
        while(K != 0){
            count++;
            K = K/10;
        }
        boolean isNegative = false;
        int currmax = Integer.MIN_VALUE;
        if(N<0){
            N*=-1;
            isNegative = true;
            currmax = Integer.MAX_VALUE;
        }
        String pom = Integer.toString(N);
        for(int i =0;i<count;i++){
            if(pom.charAt(i)=='5'){
                StringBuilder sb = new StringBuilder(pom);
                sb.deleteCharAt(i);
                String res = sb.toString();
                int newNumber = Integer.parseInt(res);
                if(!isNegative && newNumber>currmax){
                    currmax = newNumber;
                }
                else if(isNegative && newNumber<currmax){
                    currmax = newNumber;
                }
            }
        }
        return isNegative? -currmax:currmax;
    }
    public static void main(String[] args) {
        System.out.println(solution(-5000));
    }
}
