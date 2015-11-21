package signiture;

import java.math.BigInteger;

/**
 * Created by darya on 12.11.15.
 */
public class Key {
    private final BigInteger openKey;
    private final BigInteger secretKey;
    private final BigInteger n;

    public Key(BigInteger openKey, BigInteger secretKey, BigInteger n) {
        this.openKey = openKey;
        this.secretKey = secretKey;
        this.n = n;
    }

    public BigInteger getOpenKey() {
        return openKey;
    }

    public BigInteger getSecretKey() {
        return secretKey;
    }

    public BigInteger getN() {
        return n;
    }
}
