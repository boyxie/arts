package algorithms;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws Exception {
        byte[] bytes1 = Base64.getEncoder().encode("13857165377".getBytes());
        System.out.println(new String(bytes1));
        byte[] bytes2 = Base64.getDecoder().decode(new String(bytes1));
        System.out.println(new String(bytes2));


        //生成Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        //keyGenerator.init(128, new SecureRandom("seedseedseed".getBytes()));
        //使用上面这种初始化方法可以特定种子来生成密钥，这样加密后的密文是唯一固定的。
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        //Key转换
        Key key = new SecretKeySpec(keyBytes, "AES");

        //加密
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encodeResult = cipher.doFinal("13857165377".getBytes());
//        System.out.println("AESencode : " + Hex(encodeResult));

        //解密
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeResult = cipher.doFinal(encodeResult);
        System.out.println("AESdecode : " + new String(decodeResult));

        System.out.println(new Date(1539140640000L));

    }
}
