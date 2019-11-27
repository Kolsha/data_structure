package exam.review.effective_java.item_16;

/**
 * Created by shanwu on 17-1-16.
 */
public class ErrDemoRecordSum extends DemoSum {
    public int mAccessNum = 0;

    public ErrDemoRecordSum(int n) {
        super(n);
    }

    public ErrDemoRecordSum() {
        super();
    }

    @Override
    public int add(int n) {
        mAccessNum++;
        return super.add(n);
    }

    @Override
    public int addAll(int... n) {
        mAccessNum +=n.length;
        return super.addAll(n);
    }

    public int getAccessCount() {
        return mAccessNum;
    }
}
