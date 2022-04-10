package org.gog.datatrace.receiver;

import java.io.UnsupportedEncodingException;

public class BinaryBufferParser {

	private byte[] byteBuffer;
	private char[] charBuffer;
	
	private int currentBufferPosition = 0;
	
	private BinaryBufferParser(byte[] buffer) {
		super();
		setNewBuffer(buffer);
	}
	
	public static BinaryBufferParser getInstance(byte[] buffer) {
		return new BinaryBufferParser(buffer);
	}
	
	public void setNewBuffer(byte[] buffer) {
		byteBuffer = buffer;
		charBuffer = new char[byteBuffer.length];
		arrayCopyByteToChar(byteBuffer, 0, charBuffer, byteBuffer.length);
		currentBufferPosition = 0;		
	}

	public void skipNBytes(int numberOfBytesToSkip) {
		currentBufferPosition += numberOfBytesToSkip;
	}
	
	public String getString(int length) {
		byte[] resultByte = new byte[length];
		
		arrayCopyByteToByte(byteBuffer, currentBufferPosition, resultByte, length);

		String result;
		try {
			result = new String(resultByte, "ASCII");
			currentBufferPosition += length;
		} catch (UnsupportedEncodingException e) {
			result = "N/A";
		}
				
		return result;
	}
	
	public byte getByte() {
		byte result = 
				byteBuffer[currentBufferPosition];
		currentBufferPosition += 1;
		return result;
	}

	public char getChar() {
		char result = 
				(char)byteBuffer[currentBufferPosition];
		currentBufferPosition += 1;
		return result;
	}

	public int getUInt8() {
		int result = 
			(0x00000000 << 8)						& 0x0000ff00 |
			(byteBuffer[currentBufferPosition]<< 0)	& 0x000000ff ;
		currentBufferPosition += 1;
		return result;
	}

	public int getUInt16() {
		int result = 
			(0x00000000<< 16)							& 0x00ff0000 |
			(byteBuffer[currentBufferPosition + 1]<< 8)	& 0x0000ff00 |
			(byteBuffer[currentBufferPosition + 0]<< 0)	& 0x000000ff ;
		currentBufferPosition += 2;
		
		return result;
	}

//	public int getIntOn1Byte() {
//		int result = 
//				byteBuffer[currentBufferPosition];
//		currentBufferPosition += 1;
//		return result;
//	}

	public short getInt() {
		short result = 
				byteBuffer[currentBufferPosition];
		currentBufferPosition += 2;
		return result;
	}

	public int getLong() {
		int result = 
				(byteBuffer[currentBufferPosition + 3]<<24)&0xff000000 |
				(byteBuffer[currentBufferPosition + 2]<<16)&0x00ff0000 |
				(byteBuffer[currentBufferPosition + 1]<< 8)&0x0000ff00 |
				(byteBuffer[currentBufferPosition + 0]<< 0)&0x000000ff ;
		currentBufferPosition += 4;
		return result;
	}

	public float getFloat() {
		float result =
				Float.intBitsToFloat(
				(byteBuffer[currentBufferPosition + 3]<<24)&0xff000000 |
				(byteBuffer[currentBufferPosition + 2]<<16)&0x00ff0000 |
				(byteBuffer[currentBufferPosition + 1]<< 8)&0x0000ff00 |
				(byteBuffer[currentBufferPosition + 0]<< 0)&0x000000ff ) ;
		currentBufferPosition += 4;
		return result;
	}

	/**
	 * Copy a sub set of the bytes from src to dst. The copied subset start fron index srcPos in src and is copied from index 0 to dst.
	 * the number of bytes copied is length.
	 * @param src The source array of bytes to copy from
	 * @param srcPos The starting point in the src array
	 * @param dst The destination array of bytes
	 * @param length The length of bytes to copy
	 */
	public static void arrayCopyByteToByte(byte[] src, int srcPos, byte[] dst, int length) {
		for(int i = 0 ; i < length ; i++) {
			dst[i] = src[srcPos + i];
		}
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
}


