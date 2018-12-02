import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

public class IpToIntegerUT {
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
        IpToInteger.covertIpToLong("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullString() {
        IpToInteger.covertIpToLong(null);
    }

    @Test
    public void testNormalInput() {
        Assert.assertEquals(Long.valueOf(2896692481L), IpToInteger.covertIpToLong("172.168.5.1"));
    }

    @Test
    public void testCasesWithSpace() {
        Assert.assertEquals(Long.valueOf(2896692481L), IpToInteger.covertIpToLong("172. 168 .5 . 1"));
        Assert.assertEquals(Long.valueOf(2896692481L), IpToInteger.covertIpToLong(" 172.168.5.1 "));
        Assert.assertEquals(Long.valueOf(2896692481L), IpToInteger.covertIpToLong(" 172 .168 .5 . 1 "));
        Assert.assertEquals(Long.valueOf(2896692481L), IpToInteger.covertIpToLong("   172 . 168 .5 . 1  "));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCasesWithWrongSpaces() {
        IpToInteger.covertIpToLong("1 72.1 68.5.1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIpv4AddrInvalidate0(){
        IpToInteger.covertIpToLong("256.23.4.1");
    }
}