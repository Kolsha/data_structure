package exam.review.effective_java.item_17;

/**
 * Created by shanwu on 17-1-16.
 */
public class TestClass extends ErrOverrideMethod {

    static {
        new Log(TestClass.class, Log.STATIC_AREA);
    }

    private static Log mLog = new Log(TestClass.class, Log.STATIC_MEMBER);
    public Log mLog2 = new Log(TestClass.class, Log.NONSTATIC_PUBLIC_MEMBER);

    public TestClass(String msg) {
        super(msg);
        new Log(TestClass.class, Log.CONSTRUCTOR);
    }

    @Override
    public void setData(String msg) {
        System.out.println(msg);
        super.setData(msg);
    }

    public static class Log {
        public final static String NONSTATIC_PUBLIC_MEMBER = "non static public member";
        public final static String PUBLIC_MEMBER = "static public member";
        public final static String STATIC_MEMBER = "static private member";
        public final static String STATIC_AREA = "static area";
        public final static String CONSTRUCTOR = "constructor";

        public Log(Class<?> cls, String msg) {
            System.out.println("=== [" + cls.getSimpleName() + "] " + msg + " finish initialization" + " ===");
        }
    }
}
