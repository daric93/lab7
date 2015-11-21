package signiture;

import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by darya on 17.11.15.
 */
public class SignatureTest {

    @Test
    public void testMakeSignature() throws Exception {
        assertThat(Signature.makeSignature(BigInteger.valueOf(2), BigInteger.valueOf(5), BigInteger.valueOf(91))).isEqualTo(BigInteger.valueOf(32));
        assertThat(Signature.makeSignature(BigInteger.valueOf(18), BigInteger.valueOf(5), BigInteger.valueOf(91))).isEqualTo(BigInteger.valueOf(44));
    }

    @Test
    public void testCheckSignature() throws Exception {
        assertThat(Signature.checkSignature(BigInteger.valueOf(2), BigInteger.valueOf(29), BigInteger.valueOf(91), BigInteger.valueOf(32))).isTrue();
    }
}