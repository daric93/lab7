package signiture;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by darya on 12.11.15.
 */

public class RSA {
    static Key generateKey() {
        BigInteger p = BigInteger.probablePrime(1024, new Random());
        BigInteger q = BigInteger.probablePrime(1024, new Random());
        BigInteger n = p.multiply(q);
        BigInteger eulerFunc = eulerFunc(p, q);
        BigInteger openKey = openKey(eulerFunc, n);
        BigInteger secretKey = reciprocalNumber(openKey, eulerFunc);
        return new Key(openKey, secretKey, n);
    }

    static BigInteger reciprocalNumber(BigInteger openKey, BigInteger eulerFunc) {
        BigInteger n = eulerFunc;
        BigInteger m = openKey;
        BigInteger q;
        BigInteger r;
        BigInteger b2 = BigInteger.ZERO;
        BigInteger b1 = BigInteger.ONE;
        BigInteger bi;
        while (!m.equals(BigInteger.ZERO)) {
            r = n.mod(m);
            q = n.divide(m);
            bi = b2.subtract(q.multiply(b1));
            b2 = b1;
            b1 = bi;
            n = m;
            m = r;
        }
        if (b2.compareTo(BigInteger.ZERO) == -1)
            return b2.add(eulerFunc);
        return b2;
    }

    static boolean isRelativePrime(BigInteger n, BigInteger m) {
        while (!m.equals(BigInteger.ZERO)) {
            BigInteger temp = n.mod(m);
            n = m;
            m = temp;
        }
        return n.equals(BigInteger.ONE);
    }

    static BigInteger openKey(BigInteger eulerFunc, BigInteger n) {
        for (BigInteger i = n.divide(BigInteger.valueOf(2)); i.compareTo(BigInteger.ONE) > 0; i = i.subtract(BigInteger.ONE)) {
            if (isRelativePrime(eulerFunc, i)) {
                return i;
            }
        }
        throw new RuntimeException("Cannot find open key");
    }

    static BigInteger eulerFunc(BigInteger p, BigInteger q) {
        return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    }
}
