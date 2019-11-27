package exam.review.effective_java.item_16;

/**
 * Created by shanwu on 17-1-16.
 */
public class DemoSum implements Sum {
    private int mSum = 0;

    public DemoSum(int s) {
        mSum = s;
    }

    public DemoSum() {
        mSum = 0;
    }

    @Override
    public int add(int num) {
        mSum += num;
        return mSum;
    }

    @Override
    public int addAll(int... nums) {
        for (int n : nums) {
            add(n);
        }
        return mSum;
    }

}
