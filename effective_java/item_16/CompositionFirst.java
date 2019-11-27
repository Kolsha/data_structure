package exam.review.effective_java.item_16;

/**
 * Created by shanwu on 17-1-16.
 */
public class CompositionFirst {

    public static void main(String[] args) {
        DemoSum sum = new DemoSum(0);
        System.out.println("correct: " + sum.addAll(1, 2, 3, 4, 5));

        ErrDemoRecordSum rSum = new ErrDemoRecordSum();
        System.out.println("error: " + rSum.addAll(1, 2, 3, 4, 5) + ", access count: " + rSum.getAccessCount());

        WrapperDemoSum wrSum = new WrapperDemoSum(new DemoSum()); // Design Pattern - Decorator Design Pattern
        System.out.println("correct: " + wrSum.addAll(1, 2, 3, 4, 5) + ", access count: " + wrSum.getAccessCount());

    }
}
