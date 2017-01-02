package exam.review.think_in_java.annotation;

/**
 * Created by shanwu on 17-1-2.
 */
@JSONClassKey(key = "user")
public class UserModel {
    @JSONFieldKey(key = "uid")
    private String uid;

    @JSONFieldKey(key = "first_name")
    private String firstName;

    @JSONFieldKey(key = "last_name")
    private String lastName;

    @JSONFieldKey(key = "cell")
    private String cellNum;

    @JSONFieldKey(key = "sex")
    private int sex;

    @JSONFieldKey(key = "height")
    private int height;

    @JSONFieldKey(key = "weight")
    private int weight;

    public UserModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUid() {
        return uid;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getCellNum(String num) {
        return cellNum;
    }

    public int getSex() {
        return sex;
    }

    public void setFirstName(String fname) {
        firstName = fname;
    }

    public void setLastName(String lname) {
        lastName = lname;
    }

    public void setUid(String id) {
        uid = id;
    }

    public void setWeight(int w) {
        weight = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    public void setCellNum(String num) {
        cellNum = num;
    }

    public void setSex(int sType) {
        sex = sType;
    }

    private UserModel(Builder builder) {
        uid = builder.uid;

        firstName = builder.firstName;

        lastName = builder.lastName;

        cellNum = builder.cellNum;

        sex = builder.sex;

        height = builder.height;

        weight = builder.weight;
    }

    public static class Builder {
        private String uid;

        private String firstName;

        private String lastName;

        private String cellNum;

        private int sex;

        private int height;

        private int weight;

        public Builder uid(String id) {
            uid = id;
            return this;
        }

        public Builder firstName(String f) {
            firstName = f;
            return this;
        }

        public Builder lastName(String l) {
            lastName = l;
            return this;
        }

        public Builder cell(String num) {
            cellNum = num;
            return this;
        }

        public Builder sex(int s) {
            sex = s;
            return this;
        }

        public Builder height(int h) {
            height = h;
            return this;
        }

        public Builder weight(int w) {
            weight = w;
            return this;
        }

        public UserModel build() {
            return new UserModel(this);
        }
    }
}
