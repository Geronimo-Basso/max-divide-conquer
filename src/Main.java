import java.util.Arrays;

public class Main {
    public static int maxSubArrays( int[] list ){
        int max = 0;
        if( list.length <= 1 ){
            max = list[0];
        } else {
            int mid = list.length / 2; //O(1)
            int[] listLeft = Arrays.copyOfRange(list, 0, mid);//O(n/2)
            int[] listRight = Arrays.copyOfRange(list, mid, list.length); //O(n/2)
            listLeft = new int[]{maxSubArrays( listLeft )}; //
            listRight = new int[]{maxSubArrays( listRight )};
            int listaLeftMax = maxArray( listLeft );
            int listRightMax = maxArray( listRight );
            max = Math.max(listaLeftMax,listRightMax);
        }
        return max;
    }

    private static int maxArray(int[] list){ //O(n)
        int max = 0;
        for (int i = 0; i < list.length ; i++){
            if ( max < list[i] ){
                max = list[i];
            }
        }
        return max;
    }
    
    public static int maxWithoutSubArrays(int[] list){
        int max = 0;
        if( list.length <= 1 ){
            max = list[0];
        } else {
            int leftMax = 0,rightMax = 0;
            int mid = list.length / 2;
            int[] left = new int[mid];
            int[] right = new int[list.length - mid];
            for (int i = 0; i < mid; i++) {
                left[i] = list[i];
            }
            for (int i = mid; i < list.length; i++) {
                right[i - mid] = list[i];
            }
            left = new int[]{maxWithoutSubArrays(left)};
            right = new int[]{maxWithoutSubArrays(right)};
            leftMax = maxArray(left);
            rightMax = maxArray(right);
            max = Math.max(leftMax,rightMax);
        }

        return max;
    }
    
    public static void main(String[] args) {
        System.out.println("----------------- With the Sub Array method ---------------");
        int[] listEven = {1 ,2, 7, 5, 10, 25};
        int[] listOdd = {1 ,2, 7, 5, 10, 25 , 75};
        System.out.println("Max of the listEven: " + maxSubArrays(listEven));
        System.out.println("Max of the listOdd: " + maxSubArrays(listOdd));
        System.out.println("----------------- With the same Array ---------------");
        System.out.println("Max of the listEven: " + maxWithoutSubArrays(listEven));
        System.out.println("Max of the listOdd: " + maxWithoutSubArrays(listOdd));



    }
}