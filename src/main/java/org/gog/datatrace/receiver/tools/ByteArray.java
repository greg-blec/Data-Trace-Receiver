package org.gog.datatrace.receiver.tools;

import java.io.ByteArrayInputStream;

/**
 * A simple wrapper around a byte array, with a start position and
 * count of bytes.
 *
 * @author  John Mani
 */

public class ByteArray {
    private byte[] bytes; // the byte array
    private int start;	  // start position
    private int count;	  // count of bytes

    /**
     * Constructor
     *
     * @param	b	the byte array to wrap
     */
    public ByteArray(byte[] b) {
	bytes = b;
	this.start = 0;
	this.count = b.length;
    }


    /**
     * Returns the internal byte array. Note that this is a live
     * reference to the actual data, not a copy.
     *
     * @return	the wrapped byte array
     */
    public byte[] getBytes() {
    	return bytes;
    }

    /**
     * Returns the start position
     *
     * @return	the start position
     */
    public int getStart() {
    	return start;
    }

    /**
     * Returns the count of bytes
     *
     * @return	the number of bytes
     */
    public int getCount() {
    	return count;
    }

    /**
     * Returns a ByteArrayInputStream.
     *
     * @return	the ByteArrayInputStream
     */
    public ByteArrayInputStream toByteArrayInputStream() {
    	return new ByteArrayInputStream(bytes, start, count);
    }

}
