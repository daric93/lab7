package signiture;

import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by darya on 17.11.15.
 */
public class RSATest {
    @Test
    public void testReciprocalNumber() throws Exception {
        assertThat(RSA.reciprocalNumber(BigInteger.valueOf(5), BigInteger.valueOf(72))).isEqualByComparingTo(BigInteger.valueOf(29));
    }

    @Test
    public void testIsRelativePrime() throws Exception {
        assertTrue(RSA.isRelativePrime(BigInteger.valueOf(72), BigInteger.valueOf(5)));
        assertFalse(RSA.isRelativePrime(BigInteger.valueOf(36), BigInteger.valueOf(18)));
    }

    @Test
    public void testOpenKey() throws Exception {
        BigInteger actual = RSA.openKey(BigInteger.valueOf(72), BigInteger.valueOf(91));
        assertThat(RSA.isRelativePrime(BigInteger.valueOf(72), actual)).isTrue();
        assertThat(actual).isLessThan(BigInteger.valueOf(91)).isGreaterThan(BigInteger.ZERO);
    }

    @Test
    public void testEulerFunc() throws Exception {
        assertThat(RSA.eulerFunc(BigInteger.valueOf(3), BigInteger.valueOf(5))).isEqualByComparingTo(BigInteger.valueOf(8));
    }
}