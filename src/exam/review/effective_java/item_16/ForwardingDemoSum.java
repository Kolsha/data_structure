package exam.review.effective_java.item_16;

/**
 * Created by shanwu on 17-1-16.
 */
public class ForwardingDemoSum implements Sum {
    private Sum mSumObject;
    public ForwardingDemoSum(Sum o) {
        mSumObject = o;
    }

    @Override
    public int add(int n) {
        return mSumObject.add(n);
    }

    @Override
    public int addAll(int... n) {
        return mSumObject.addAll(n);
    }
}
