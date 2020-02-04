public class test{

    public static void main(String []args){
        System.out.println("Hello");
        int[] input = new int[] {1,2,2,3,3,3,3,3,3,3};
        int result = binarySearch(input, 1);
        System.out.println(result);
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