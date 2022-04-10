package org.gog.datatrace.receiver;

import org.gog.datatrace.receiver.tools.ByteArrayTool;

public class BinaryBuffer {

	private byte[] byteBuffer;
	private char[] charBuffer;
	
	private int currentBufferPosition = 0;
	private int size = 0;

	public static BinaryBuffer getInstance(byte[] buffer) {
		return new BinaryBuffer(buffer);
	}

	private BinaryBuffer(byte[] buffer) {
		super();
		setNewBuffer(buffer);
	}
	
	public byte[] readNext(int length) {
		byte[] nextBytes = new byte[length];
		ByteArrayTool.arrayCopyByteToByte(byteBuffer, currentBufferPosition, nextBytes, length);
		currentBufferPosition += length;
		return nextBytes;
	}
	
	public byte[] read(int length) {
		byte[] nextBytes = new byte[length];
		ByteArrayTool.arrayCopyByteToByte(byteBuffer, currentBufferPosition, nextBytes, length);
		return nextBytes;
	}

	public void skipNBytes(int length) {
		currentBufferPosition += length;
	}

	public void setNewBuffer(byte[] buffer) {
		byteBuffer = buffer;
		charBuffer = new char[byteBuffer.length];
		ByteArrayTool.arrayCopyByteToChar(byteBuffer, 0, charBuffer, byteBuffer.length);
		currentBufferPosition = 0;
		size = byteBuffer.length;
	}

	public int getSize() {
		return size;
	}

}


