package org.gog.datatrace.receiver.tools;

import org.gog.datatrace.receiver.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteArrayTool {
	
	private static final Logger logger = LoggerFactory.getLogger(ByteArrayTool.class);

	/**
	 * Copy a sub set of the bytes from src to dst. The copied subset start fron index srcPos in src and is copied from index 0 to dst.
	 * the number of bytes copied is length.
	 * @param src The source array of bytes to copy from
	 * @param srcPos The starting point in the src array
	 * @param dst The destination array of bytes
	 * @param length The length of bytes to copy
	 * 
	 * @return the total number of bytes read. It can be less than length.
	 */
	public static int arrayCopyByteToByte(byte[] src, int srcPos, byte[] dst, int length) {
		int srcTotalLength = src.length - 3;
		int localSrcPos = srcPos;
//		int i = 0;

//		logger.info("srcPos: {} - length: {} - srcLength: {} - localPos: {}", srcPos, length, srcTotalLength, localSrcPos);

		for(int i = 0 ; i < length ; i++) {
			localSrcPos = srcPos + i;
//			logger.info("localSrcPos: {} - i: {}", localSrcPos, i);
			
			if ( localSrcPos >= srcTotalLength ) {
				break;
			}
			
			dst[i] = src[localSrcPos];
		}

//		while( i < length && localSrcPos < srcTotalLength ) {
////			logger.info("localSrcPos: {} - i: {}", localSrcPos, i);
//			localSrcPos += i;
//			dst[i] = src[localSrcPos];
//			i++;
//		}

		return length;
		
//		logger.info("src: {} - srcPos: {} - dst: {} - length: {} - srcLength: {} - localPos: {}", src, srcPos, dst, length, srcTotalLength, localSrcPos);
//		
//		for(int i = 0 ; i < length ; i++) {
//			localSrcPos += i;
//			logger.info("localSrcPos: {} - i: {}", localSrcPos, i);
//			dst[i] = src[srcPos + i];
//		}
		
//		return length;
	}

	public static void arrayCopyByteToChar(byte[] src, int srcPos, char[] dst, int length) {
		for(int i = 0 ; i < length ; i++) {
			dst[i] = (char)src[srcPos + i];
		}
	}

	public static void arrayConvertByteToHex(byte[] src, String[] dst) {
		int length = src.length;
		for(int i = 0 ; i < length ; i++) {
			dst[i] =  String.format("%02X", src[i]);
		}
	}

	public static String arrayConvertByteToHex(byte[] src) {
		StringBuffer buffer = new StringBuffer();
		int length = src.length;
		for(int i = 0 ; i < length ; i++) {
			buffer.append(String.format("%02X", src[i]));
		}
		return buffer.toString();
	}

}
