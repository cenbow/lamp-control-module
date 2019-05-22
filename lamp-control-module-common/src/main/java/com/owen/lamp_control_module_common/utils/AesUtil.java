package com.owen.lamp_control_module_common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * <p>Title: AesUtil</p>
 * <p>Description: aes加密</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/18 14:43
 */
public class AesUtil {

    /**
     * 初始向量
     * AES 为16bytes. DES 为8bytes
     */
    public static final String VIPARA = "CB3EC842D7C69578";

    /**
     * 编码方式
     */
    public static final String ENCODING = "UTF-8";

    /**
     * 加密
     *
     * @param content 明文
     * @param aesKey  秘钥 长度16位
     * @return 结果
     */
    public static String encrypt(String content, String aesKey) {
        //加密方式： AES128(CBC/PKCS5Padding) + Base64, 私钥：aabbccddeeffgghh
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
            //实例化加密类，参数为加密方式，要写全
            //PKCS5Padding比PKCS7Padding效率高，PKCS7Padding可支持IOS加解密
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //初始化，此方法可以采用三种方式，按加密算法要求来添加。（1）无第三个参数（2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)（3）采用此代码中的IVParameterSpec
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            //加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64, HEX, UUE,7bit等等。此处看服务器需要什么编码方式
            byte[] encryptedData = cipher.doFinal(content.getBytes(ENCODING));
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 解密
     *
     * @param content 密文
     * @param aesKey  秘钥 长度16位
     * @return 结果
     */
    public static String decrypt(String content, String aesKey) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] byteMi = decoder.decode(content);
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //与加密时不同MODE:Cipher.DECRYPT_MODE
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * md5 加密
     *
     * @param s
     * @return 结果
     */
    public static String md5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        final char[] hexDigits = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(hexDigits[(bytes[i] >> 4) & 0x0f]);
            ret.append(hexDigits[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    /**
     * 测试
     *
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception {
//        String str = "123456";
//        String aesKey = "1234560123456789";
//        String encode = AesUtil.encrypt(str, aesKey);
//        System.out.println(encode);
//        String decode = AesUtil.decrypt(encode, aesKey);
//        System.out.println(decode);
//    }
}
