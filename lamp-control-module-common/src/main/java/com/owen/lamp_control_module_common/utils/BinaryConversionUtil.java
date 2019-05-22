package com.owen.lamp_control_module_common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: BinaryConversionUtil</p>
 * <p>Description: 进制转换</p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/18 14:47
 */
public class BinaryConversionUtil {

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return 结果
     */
    public static byte[] StringtoBytes(String str) {
        if (str == null || "".equals(str.trim())) {
            return new byte[0];
        }
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }

    /**
     * 数组转换成十六进制字符串
     *
     * @param bArray
     * @return 结果 HexString
     */
    public static String bytesToHexString(byte[] bArray) {
        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
        for (byte aBArray : bArray) {
            sTemp = Integer.toHexString(0xFF & aBArray);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 转换十六进制字符串为字节数组
     *
     * @param hexString the hex string
     * @return 结果 byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 逢单数位补0
     *
     * @param str
     * @return 结果
     */
    public static String changeAddSpace(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str.length() % 2 != 0) {
            stringBuilder.append("0");
        }
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    /**
     * 将10进制转换为十六进制 逢单数位补0
     *
     * @param str
     * @return 结果
     */
    public static String decimalToHex(String str) {
        str = Integer.toHexString(Integer.parseInt(str.trim()));
        return changeAddSpace(str);
    }

    /**
     * 将10进制转换为十六进制 逢单数位补0
     *
     * @param data
     * @return 结果
     */
    public static String decimalToHex(int data) {
        String str = Integer.toHexString(data);
        return changeAddSpace(str);
    }

    /**
     * 将16进制转换为十进制 逢单数位补0
     *
     * @param hex
     * @return 结果
     */
    public static String hexToDecimal(String hex) {
        int decimal = Integer.parseInt(hex, 16);
        return changeAddSpace(decimal + "");
    }

    /**
     * 将10进制转换为十六进制 两字节 FFFF
     *
     * @param str 字符串十进制参数
     * @return 结果
     */
    public static String decimalToHexFF(String str) {
        if ("".equals(str) || str == null) {
            return "";
        }
        return decimalToHexFF(Integer.valueOf(str));
    }

    /**
     * 将10进制转换为十六进制 两字节 FFFF
     *
     * @param decimal 十进制参数
     * @return 结果
     */
    public static String decimalToHexFF(int decimal) {
        String str = Integer.toHexString(decimal);
        str = changeAddSpace(str);
        if (str.length() < 4) {
            str = "00" + str;
        }
        return str;
    }

    /**
     * 构建校验位
     *
     * @param head
     * @param responseStatus
     * @param command
     * @param timeStamp
     * @param data
     * @return 结果
     */
    public static String checkHexSum(String head, String responseStatus, String command, String timeStamp, String id, String data) {
        return checkHexSum(head + responseStatus + command + decimalToHexFF(data.length() / 2) + timeStamp + decimalToHexFF(id) + data);
    }

//    public static void main(String[] args) {
//        String str = "D5C8D5C800F00004120B1E0A390B05FFFFFFFFB813AB13AB";
//        System.out.println(str);
//        System.out.println(checkHexSum("D5C8D5C800F00004120B1E0A390B05FFFFFFFF"));
//        System.out.println(checkHexSum("D5C8D5C8",
//                "0",
//                "0F0",
//                "120B1E0A390B05",
//                "",
//                "FFFFFFFF"));
//    }

    /**
     * 进行累加校验
     *
     * @param date 在校验尾和校验位之前的数据
     * @return 结果
     */
    public static String checkHexSum(String date) {
        String[] strings = new String[date.length() / 2];
        int flag = 0;
        for (int i = 0; i < date.length(); i += 2) {
            String s = date.substring(i, i + 2);
            int tempInt = Integer.parseInt(s, 16);
            strings[flag] = String.valueOf(tempInt);
            flag++;
        }
        // 将字符串转为 int
        int sum = 0x0;
        for (String string : strings) {
            sum += Integer.parseInt(string);
        }
        String temp = Integer.toHexString(sum);
        return temp.substring(temp.length() - 2).toUpperCase();
    }

    /**
     * 获取时间 16进制数
     *
     * @return 结果
     */
    public static String timeHex() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yy.MM.dd.HH.mm.ss.uu");
        String msg = ft.format(dNow);
        String[] strings = msg.split("\\.");
        for (int i = 0; i < strings.length; i++) {
            StringBuilder temp = new StringBuilder(Integer.toHexString(Integer.parseInt(strings[i])));
            if (temp.length() == 1) {
                temp.insert(0, "0");
            }
            strings[i] = temp.toString();
        }
        return arrayToString(strings).replace(" ", "");
    }

    private static String arrayToString(String[] var0) {
        if (var0 == null) {
            return null;
        } else {
            StringBuilder var1 = new StringBuilder();
            for (String aVar0 : var0) {
                var1.append(aVar0 != null ? aVar0.replaceAll("\\s", "") : "").append(" ");
            }
            return var1.toString();
        }
    }

    /**
     * 将 char 类型 转换为 byte 类型
     *
     * @param c char
     * @return 结果 byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}
