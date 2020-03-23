import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class test {

    public static void main(String []args){
        
    }
    
    
    private static int binarySearch(int[] input, int target) {
        int lo = 0, hi = input.length - 1;
        while(lo < hi) {
            int mid = (lo + hi)/2;
            if(input[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        
        return (input[lo] == target)? lo: -1;
    }
}