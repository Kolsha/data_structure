package exam.review.think_in_java.annotation;

import java.lang.reflect.Field;

/**
 * Created by shanwu on 17-1-2.
 */
public class JsonModelUtil {
    public static String toJSONString(Object object, Class<?> modelClass, boolean isInternal, boolean isLast) {
        JSONClassKey classKey = modelClass.getAnnotation(JSONClassKey.class);
        if (classKey == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(classKey.key());
            sb.append("\": {");

            Field[] fieldArray = modelClass.getDeclaredFields();
            final int size = fieldArray.length;
            for (int i = 0; i < size; i++) {
                Field temp = fieldArray[i];
                temp.setAccessible(true);
                Object fieldValue = null;
                try {
                    fieldValue = temp.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                JSONFieldKey fieldKey = temp.getAnnotation(JSONFieldKey.class);
                sb.append("\"");
                sb.append(fieldKey.key());
                sb.append("\": ");
                sb.append("\"");
                sb.append(fieldValue);
                if (i == size - 1) {
                    sb.append("\"");
                } else {
                    sb.append("\", ");
                }
            }

            sb.append("}");

            if (isInternal && isLast) {
                return sb.toString();
            } else if (isInternal && !isLast) {
                return sb.toString() + " ,";
            } else {
                return "{ " + sb.toString() + " }";
            }
        }
    }


    public static void main(String[] args) {
        UserModel.Builder builder = new UserModel.Builder();
        UserModel user = builder.cell("12345678").firstName("shanwu").lastName("sung").sex(1).height(170).build();
        System.out.println(toJSONString(user, UserModel.class, false, false));


    }
}
