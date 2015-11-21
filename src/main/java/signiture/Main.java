package signiture;

import fr.cryptohash.RIPEMD;

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by darya on 12.11.15.
 */
public class Main {
    static void writeKey(String secretKey, String openKey, String n) throws IOException {
        try (FileWriter writer = new FileWriter(new File("data.key"))) {
            writer.write(secretKey + "\n" + openKey + "\n" + n);
        }
    }

    static void writeSignature(String signature, String openKey, String n) throws IOException {
        try (FileWriter writer = new FileWriter(new File("data.sig"))) {
            writer.write(signature + "\n" + openKey + "\n" + n);
        }
    }

    static String[] readSignature() throws IOException {
        String[] str = new String[3];
        try (BufferedReader reader = new BufferedReader(new FileReader("data.sig"))) {
            for (int i = 0; i < 3; i++) {
                str[i] = reader.readLine();
            }
            return str;
        }
    }

    static void readData(RIPEMD ripemd) throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream("data"))) {
            while (in.read() != -1) {
                ripemd.update((byte) in.read());
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("1 - make signature;\n2 - check signature");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("make signature");
                Key key = RSA.generateKey();
                writeKey(key.getSecretKey().toString(16), key.getOpenKey().toString(16), key.getN().toString(16));
                BigInteger hash = getHash();
                BigInteger signature = Signature.makeSignature(hash, key.getSecretKey(), key.getN());
                writeSignature(signature.toString(16), key.getOpenKey().toString(16), key.getN().toString(16));
                break;
            case 2:
                String[] strings = readSignature();
                BigInteger data = getHash();
                BigInteger openKey = new BigInteger(strings[1], 16);
                BigInteger n = new BigInteger(strings[2], 16);
                BigInteger signature1 = new BigInteger(strings[0], 16);
                boolean b = Signature.checkSignature(data, openKey, n, signature1);
                System.out.println(b);
                break;
            default:
                System.out.print("wrong input");
                break;
        }
    }

    private static BigInteger getHash() throws IOException {
        RIPEMD ripemd = new RIPEMD();
        readData(ripemd);
        return new BigInteger(1, ripemd.digest());
    }
}
