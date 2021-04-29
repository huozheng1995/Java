package com.learn.unicode;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @description: 测试
 * @author: Huo
 * @create: 2019-10-25 09:31
 */
public class Base64{
    private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/' };

    private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
            60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
            -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
            38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
            -1, -1 };
    //查看任一个字符在Java中的表示
    @Test
    public void getUTF8(String str) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes("utf-8");
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(Integer.toHexString(bytes[i] & 0xff).toUpperCase() + " ");
        }
    }

    public static String encode(byte[] data) {
        System.out.println(data.length);
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            // 提取b1
            b1 = data[i++] & 0xff;
            if (i == len) { // len % 3 == 1
                // 向右无符号移动2位，保留b1的0-5位（前六位）
                sb.append(base64EncodeChars[b1 >>> 2]);
                // 保留b1的6-7位（后两位），其余位为0，然后向左移动4位，低位补0
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                // 添加两个等号（Base64规则）
                sb.append("==");
                System.out.println("1个字节：" + sb);
                // 跳出循环
                break;
            }
            // 提取b2
            b2 = data[i++] & 0xff;
            if (i == len) { // len % 3 == 2
                // 保留b1的0-5位（前六位），其余位为0
                sb.append(base64EncodeChars[b1 >>> 2]);
                // 保留b1的6-7位（后两位），其余位为0，然后向左移动4位，低位补0
                // 然后保留b2的0-3位（前四位），然后合并
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                // 添加两个等号（Base64规则）
                sb.append("=");
                System.out.println("2个字节：" + sb);
                // 跳出循环
                break;
            }
            // 提取b3
            b3 = data[i++] & 0xff;
            // 向右无符号移动2位，保留b1的0-5位（前六位）
            sb.append(base64EncodeChars[b1 >>> 2]);
            // 保留b1的6-7位（后两位），其余位为0，然后向左移动4位，低位补0
            // 然后保留b2的0-3位（前四位），然后合并
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            // 保留b2的4-7位（后四位），然后向右移2位，低位补0，
            // 然后保留b3的0-1位（前两位），然后合并
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            // 保留b3的2-7位（后六位）
            sb.append(base64EncodeChars[b3 & 0x3f]);
            System.out.println("3个字节：" + sb);
        }
        return sb.toString();
    }

    public static byte[] decode(String str) throws UnsupportedEncodingException {
        // 使用ISO8859-1搭配其他编码如UTF-8,GBK可以显示中文
        StringBuffer sb = new StringBuffer();
        // 获取ASCII码
        byte[] data = str.getBytes("US-ASCII");
        System.out.println("打印" + str + "：");
        for (int i = 0; i < data.length; i++) {
            System.out.println(Integer.toHexString(data[i] & 0xff).toUpperCase() + " ");
        }
        int len = data.length;
        int i = 0;
        int b1, b2, b3, b4;
        while (i < len) {
            do {
                b1 = base64DecodeChars[data[i++]];
            } while (i < len && b1 == -1);
            if (b1 == -1)
                break;
            do {
                b2 = base64DecodeChars[data[i++]];
            } while (i < len && b2 == -1);
            if (b2 == -1)
                break;
            // b1向左移2位，然后b2保留2-3位，再向右无符号移动4位，再合并
            sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));
            do {
                b3 = data[i++];
                if (b3 == 61) // 遇到了=号，结束，返回
                    return sb.toString().getBytes("ISO8859-1");
                b3 = base64DecodeChars[b3];
            } while (i < len && b3 == -1);
            if (b3 == -1)
                break;
            // 提取b2的4-7位（后四位），再向左移动4位，b3保留2-5位，再向右无符号移动2位
            sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
            do {
                b4 = data[i++];
                if (b4 == 61) // 遇到了=号，结束，返回
                    return sb.toString().getBytes("ISO8859-1");
                b4 = base64DecodeChars[b4];
            } while (i < len && b4 == -1);
            if (b4 == -1)
                break;
            // 提取b3的6-7位（最后两位），再向左移动6位，再取b4的2-7位（后六位），然后合并b4
            sb.append((char) (((b3 & 0x03) << 6) | (b4 & 0x3f)));
        }
        return sb.toString().getBytes("ISO8859-1");
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "张";
        System.out.println("编码前：" + s);
        String x = encode(s.getBytes());
        System.out.println("编码后：" + x);
        String x1 = new String(decode(x));
        System.out.println("解码后：" + x1);
    }


}
