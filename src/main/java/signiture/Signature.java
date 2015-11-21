package signiture;

import java.math.BigInteger;

/**
 * Created by darya on 12.11.15.
 */
public class Signature {
    static BigInteger makeSignature(BigInteger data, BigInteger secretKey, BigInteger n) {
        return data.modPow(secretKey, n);
    }

    static boolean checkSignature(BigInteger data, BigInteger openKey, BigInteger n, BigInteger signature) {
        BigInteger x = signature.modPow(openKey, n);
        return data.equals(x);
    }
}
