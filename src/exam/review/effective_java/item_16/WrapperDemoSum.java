package exam.review.effective_java.item_16;

/**
 * Created by shanwu on 17-1-16.
 */
public class WrapperDemoSum extends ForwardingDemoSum {
    public int mAccessNum = 0;

    public WrapperDemoSum(Sum o) {
        super(o);
    }

    @Override
    public int add(int n) {
        mAccessNum++;
        return super.add(n);
    }

    @Override
    public int addAll(int... n) {
        mAccessNum += n.length;
        return super.addAll(n);
    }

    public int getAccessCount() {
        return mAccessNum;
    }
}
