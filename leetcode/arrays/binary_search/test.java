import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class test {

    public static void main(String []args){
        System.out.println("Hello");
        int[][] input = new int[][] {
            {1, 2},
            {0, 1},
            {2, 4},
            {3, 9},
        };
        Comparator<int[]> comp = new Comparator<>() {
            @Override
            public int compare(int[] small, int[] large) {
                // return (large[0]*large[0]+large[1]*large[1]) - (small[0]*small[0]+small[1]*small[1]); // large -> small
                return (small[0]*small[0]+small[1]*small[1]) - (large[0]*large[0]+large[1]*large[1]); // small -> large
            }
        };
        Arrays.sort(input, comp);
        for(int[] pair: input) {
            System.out.print(Arrays.toString(pair));
        }

        int[] o1 = {1, 2};
        int[] o2 = {2, 4};

        int[] nums = {5, 6, 2, 4, 1, 3};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        PriorityQueue<int[]> queue = new PriorityQueue<>(comp);
        for(int[] pair: input) {
            queue.offer(pair);
        }

        for(int[] pair: queue) {
            System.out.print(Arrays.toString(pair));
        }
        
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