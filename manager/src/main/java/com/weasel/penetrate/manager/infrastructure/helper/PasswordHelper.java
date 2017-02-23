package com.weasel.penetrate.manager.infrastructure.helper;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


/**
 * @author Dylan
 * @time 2013-8-8
 */
public class PasswordHelper {

	private final static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();


	public static String createSalt(){

		return randomNumberGenerator.nextBytes()
				                    .toHex();
	}

	/**
	 *
	 * @param password
	 * @param credentialsSalt
	 * @param algorithn
	 * @param hashIterations
     * @return
     */
	public static String encrypt(String password,String credentialsSalt,AlgorithnEnum algorithn,int hashIterations){

		return new SimpleHash(
				    algorithn.name(),
					password,
					ByteSource.Util.bytes(credentialsSalt),
					hashIterations
		        ).toHex();
	}

	/**
	 *
	 * @param password
	 * @param credentialsSalt
	 * @param algorithn
     * @return
     */
	public static String encrypt(String password,String credentialsSalt,AlgorithnEnum algorithn){

		return encrypt(password,credentialsSalt,algorithn,1);
	}

	/**
	 *
	 * @param password
	 * @param credentialsSalt
     * @return
     */
	public static String encrypt(String password,String credentialsSalt){

		return encrypt(password,credentialsSalt,AlgorithnEnum.MD5,1);
	}

	public static enum  AlgorithnEnum{

		MD2("MD2"),MD5("MD5"),SHA512("SHA-512"),SHA384("SHA-384"),SHA256("SHA-256"),SHA1("SHA-1");

		private String name;

		AlgorithnEnum(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
