package exam.review.think_in_java.inner_class;

/**
 * Created by shanwu on 17-1-3.
 */
public class InterviewQuestion {
    private String mContent = "hi";

    class B {
        private String mContent = "hi, B";

        class C {
            private String mContent = "hi, C";

            public void test() {
                System.out.println(InterviewQuestion.this.mContent);
            }
        }
    }

    public static void main(String[] args) {
        InterviewQuestion.B.C c = new InterviewQuestion().new B().new C();
        c.test();

    }
}
