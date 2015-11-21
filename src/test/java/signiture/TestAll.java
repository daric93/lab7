package signiture;

import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by darya on 21.11.15.
 */
public class TestAll {
    @Test
    public void encodeTest() throws IOException {
        Key key = RSA.generateKey();
        BigInteger data = BigInteger.valueOf(111111);
        BigInteger signature = Signature.makeSignature(data, key.getSecretKey(), key.getN());
        assertThat(Signature.checkSignature(data, key.getOpenKey(), key.getN(), signature)).isTrue();
    }
}
