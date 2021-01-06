package com.xqoo.common.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5加密
 * 创建者 科帮网
 * 创建时间	2017年7月31日
 *
 */
public class MD5Util {

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname)) {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			} else {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
			}
		} catch (Exception exception) {
		}
		return resultString;
	}

	public static class FileMd5Util{
		public static final String KEY_MD5 = "MD5";
		public static final String CHARSET_ISO88591 = "ISO-8859-1";

		/**
		 * Get MD5 of one file:hex string,test OK!
		 *
		 * @param file
		 * @return
		 */
		public static String getFileMD5(File file) {
			if (!file.exists() || !file.isFile()) {
				return null;
			}
			MessageDigest digest = null;
			FileInputStream in = null;
			byte buffer[] = new byte[1024];
			int len;
			try {
				digest = MessageDigest.getInstance("MD5");
				in = new FileInputStream(file);
				while ((len = in.read(buffer, 0, 1024)) != -1) {
					digest.update(buffer, 0, len);
				}
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			BigInteger bigInt = new BigInteger(1, digest.digest());
			return bigInt.toString(16);
		}

		/***
		 * Get MD5 of one file！test ok!
		 *
		 * @param filepath
		 * @return
		 */
		public static String getFileMD5(String filepath) {
			File file = new File(filepath);
			return getFileMD5(file);
		}

		/**
		 * MD5 encrypt,test ok
		 *
		 * @param data
		 * @return byte[]
		 * @throws Exception
		 */
		public static byte[] encryptMD5(byte[] data) throws Exception {

			MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(data);
			return md5.digest();
		}

		public static byte[] encryptMD5(String data) throws Exception {
			return encryptMD5(data.getBytes(CHARSET_ISO88591));
		}
		/***
		 * compare two file by Md5
		 *
		 * @param file1
		 * @param file2
		 * @return
		 */
		public static boolean isSameMd5(File file1,File file2){
			String md5_1= FileMd5Util.getFileMD5(file1);
			String md5_2= FileMd5Util.getFileMD5(file2);
			return md5_1.equals(md5_2);
		}
		/***
		 * compare two file by Md5
		 *
		 * @param filepath1
		 * @param filepath2
		 * @return
		 */
		public static boolean isSameMd5(String filepath1,String filepath2){
			File file1=new File(filepath1);
			File file2=new File(filepath2);
			return isSameMd5(file1, file2);
		}

		/*public static void main(String[] args) {
			File file = new File("F:\\bookQr_SYS\\target\\bookQr\\files\\20170122\\2124b3f9-a4c8-4297-a182-6249010dcd72.mp4");
			String md5Str = getFileMD5(file);
			System.out.println(""+md5Str.length()+" "+md5Str);
		}*/
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
