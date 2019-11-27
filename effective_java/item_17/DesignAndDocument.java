package exam.review.effective_java.item_17;

/**
 * Created by shanwu on 17-1-16.
 */
public class DesignAndDocument {

    public static void main(String[] args) {
        TestClass test = new TestClass("hiii"); // 构造器绝不能调用可被覆盖的方法
    }
}
