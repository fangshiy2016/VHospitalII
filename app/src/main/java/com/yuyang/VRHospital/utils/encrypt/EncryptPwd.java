package com.yuyang.VRHospital.utils.encrypt;

public class EncryptPwd {

	public synchronized static String encryptPassword(String pwd) {
		try {
			pwd = MD5.toMD5(Base64.getBase64(pwd)).substring(7, 27);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return pwd;
	}
}
