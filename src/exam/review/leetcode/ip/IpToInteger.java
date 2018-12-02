
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpToInteger {
    private static final String IP_PATTERN = "^(\\s)*(\\d){1,3}(\\s)*\\.(\\s)*(\\d){1,3}(\\s)*\\.(\\s)*(\\d){1,3}(\\s)*\\.(\\s)*(\\d){1,3}(\\s)*$";

    public static boolean checkIpAddress(String ipAddr) {
        if (ipAddr == null || ipAddr.length() == 0) {
            return false;
        }

        Pattern pattern = Pattern.compile(IP_PATTERN);
        Matcher matcher = pattern.matcher(ipAddr);
        return matcher.matches();
    }

    public static long convertIpSegToLong(String ipSeg) {
        long ipSegLongResult = Long.parseLong(ipSeg.trim());

        if (ipSegLongResult < 0 || ipSegLongResult > 255)
            throw new IllegalArgumentException("Ip segment is invalid");

        return ipSegLongResult;
    }

    public static long covertIpToLong(String ip) {
        if (!checkIpAddress(ip)) {
            throw new IllegalArgumentException("IP not validated!");
        }

        long result = 0;

        String[] ipStringArray = ip.split("\\.");

        for (int i = ipStringArray.length; i > 0; i--) {
            long ipSegment = convertIpSegToLong(ipStringArray[ipStringArray.length - i]);
            long shiftedIpSegment = ipSegment << ((i - 1) * 8);
            //Use Bitwise OR to add each shifted segment to result
            result |= shiftedIpSegment;
        }

        return result;
    }

    public static void main(String[] args) {
        Long data = covertIpToLong("172.168.5.1");
        System.out.println(data.toString());

        data = covertIpToLong(".168.5.1");
        System.out.println(data.toString());
    }
}