package exam.review.think_in_java.inner_class;

/**
 * Created by shanwu on 17-1-3.
 */
public class InnerTest {
    private String mContent = "hi";

    class B {
        private String mContent = "hi, B";

        class C {
            public void test() {
                System.out.println(InnerTest.this.mContent);
                System.out.println(B.this.mContent);
            }
        }
    }

    public static void main(String[] args) {
        new InnerTest().new B().new C().test();
    }
}
