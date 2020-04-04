package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint;
	
	public static BigInteger hashOf(String entity) {		
		
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		
		// we use MD5 with 128 bits digest
		
		// compute the hash of the input 'entity'

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(entity.getBytes(StandardCharsets.UTF_8));

			// convert the hash into hex format
			String hex = toHex(digest);

			// convert the hex into BigInteger
			hashint = new BigInteger(hex, 16);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// return the BigInteger
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		// Task: compute the address size of MD5

		// get the digest length
		// int digestlen = hashint.bitLength();
		
		// compute the number of bits = digest length * 8
		// int numBits = digestlen * 8;
		
		// compute the address size = 2 ^ number of bits
		BigDecimal addressSize = BigDecimal.valueOf(Math.pow(2, bitSize()));
		
		// return the address size
		return addressSize.toBigInteger();
	}
	
	public static int bitSize() {
		
		int digestlen = 0;
		
		// find the digest length
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		digestlen = md.getDigestLength();

		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}
}