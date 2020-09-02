public class utilbox {
    public static prettyPrintArray(int[][] arr){
        for(int i = 0; i < arr.length; i ++){
            for(int j = 0; j < arr[i].length; j ++){
                System.out.print(" "+arr[i][j]);
            }
            System.out.println();
        }
    }
    public static int[] reverseArr(int[] arr){
        int[] newArr = new int[arr.length];
        for(int i = 0; i < arr.length; i ++){
            newArr[arr.length - i - 1] = arr[i];
        }
        return newArr;
    }
    public static void main(String[] args) {
        
    }
}