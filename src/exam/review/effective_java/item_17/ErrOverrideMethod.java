package exam.review.effective_java.item_17;

import exam.review.effective_java.item_17.TestClass.Log;

/** 构造器绝不能调用可被覆盖的方法
 *  Created by shanwu on 17-1-16.
 */
public class ErrOverrideMethod {
    private final static String mFlag = "[Super] ";
    private String mMsg;

    static {
        new Log(ErrOverrideMethod.class, Log.STATIC_AREA);
    }

    private static Log mLog = new Log(ErrOverrideMethod.class, Log.STATIC_MEMBER);
    public static Log mPubLog = new Log(ErrOverrideMethod.class, Log.PUBLIC_MEMBER);
    public Log mLog3 = new Log(ErrOverrideMethod.class, Log.NONSTATIC_PUBLIC_MEMBER);


    public ErrOverrideMethod(String msg) {
        setData(msg);
        new Log(ErrOverrideMethod.class, Log.CONSTRUCTOR);
    }

    public void setData(String msg) {
        mMsg = msg;
    }
}
