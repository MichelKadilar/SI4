package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class OneTimePassword {

    static Random random = new Random();

    public static String generateOTP(int otpSize) throws NoSuchAlgorithmException {
        int leftLimit = 33; // 33 is ! character in ascii table
        int rightLimit = 126; // 126 is z character in ascii table

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(otpSize)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(generatedString.getBytes());

        // Convert the byte array to a hexadecimal string
        byte[] digest = md.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

}
