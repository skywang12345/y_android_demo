package cn.skw.droidlib.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5校验码生成工具
 *
 * @author sky wang
 */
public class Md5Util {

	/**
	 * 生成md5校验码
	 * @param srcContent 需要加密的数据
	 * @return 加密后的md5校验码。出错则返回null。
	 */
	public static String makeMd5Sum(byte[] srcContent){
		if(srcContent == null)
			return null;
		
		String strDes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(srcContent);
			strDes = bytes2HexString(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		
		return strDes;
	}
	
	/**
	 * bytes2Hex方法
	 * @param byteArray
	 * @return
	 */
	private static String bytes2HexString(byte[] byteArray){
		
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++)
		{
			if(byteArray[i] >= 0 && byteArray[i] < 16)
			{
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}
	
}
