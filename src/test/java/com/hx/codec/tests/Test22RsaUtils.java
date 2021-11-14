package com.hx.codec.tests;

import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.Base64CodecUtils;
import com.hx.codec.utils.RsaCodecUtils;
import org.junit.Test;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Map;

/**
 * Test22RsaUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-14 11:41
 */
public class Test22RsaUtils extends Test00BaseTests {

    @Test
    public void test01RsaUsage() throws Exception {

        Map<String, Object> keyPair = RsaCodecUtils.genKeyPair();
        String publicKey = new String(Base64CodecUtils.encodeBase64(((RSAPublicKey) (keyPair.get(RsaCodecUtils.PUBLIC_KEY))).getEncoded()));
        String privateKey = new String(Base64CodecUtils.encodeBase64(((RSAPrivateKey) (keyPair.get(RsaCodecUtils.PRIVATE_KEY))).getEncoded()));

        byte[] entity01 = "Hello Jerry".getBytes();
        byte[] entity01Encoded = RsaCodecUtils.encryptByPrivateKey(entity01, privateKey);
        byte[] entity01Decoded = RsaCodecUtils.decryptByPublicKey(entity01Encoded, publicKey);
        AssertUtils.state(Arrays.equals(entity01, entity01Decoded), " unexpected value ");

        byte[] entity02 = "Hello Jerry".getBytes();
        byte[] entity02Encoded = RsaCodecUtils.encryptByPublicKey(entity02, publicKey);
        byte[] entity02Decoded = RsaCodecUtils.decryptByPrivateKey(entity02Encoded, privateKey);
        AssertUtils.state(Arrays.equals(entity02, entity02Decoded), " unexpected value ");

    }

}
