package com.liukang.tirrger.utils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) {
         // Hbase UTF8编码
         String content = "\\xE5\\x8A\\xA8\\xE6\\xBC\\xAB";
         char[] chars = content.toCharArray();
         StringBuffer sb = new StringBuffer();
         for (int i = 2; i < chars.length; i = i + 4) {
             // System.out.println(chars[i]);
             sb.append(chars[i]);
             // System.out.println(chars[i + 1]);
             sb.append(chars[i + 1]);
             }
         System.out.println(sb);
        String ouputStr = null;
        try {
            ouputStr = new String(Hex.decodeHex(sb.toString().toCharArray()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        System.out.println(ouputStr);
    }
}
