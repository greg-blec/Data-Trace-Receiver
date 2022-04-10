package org.gog.datatrace.receiver.dictionnary.datatype;


/**
 * @author gog
 *
 */
public abstract class AbstractDataType {

	
	/**
	 * The data type correponding to this 
	 */
	private DataType type;
	private int numberOfBytes;
	private String displayFormat;
	
	protected  AbstractDataType() {
	}
	
	public AbstractDataType(DataType type, int numberOfBytes, String displayFormat) {
		this.type = type;
		this.numberOfBytes = numberOfBytes;
		this.displayFormat = displayFormat;
	}
	
	public abstract Object getValue(byte bytes[]);
	
	public DataType getType() {
		return type;
	}

	public int getNumberOfBytes() {
		return numberOfBytes;
	}

	public String getDisplayFormat() {
		return displayFormat;
	}

	protected void setType(DataType type) {
		this.type = type;
	}

	protected void setNumberOfBytes(int numberOfBytes) {
		this.numberOfBytes = numberOfBytes;
	}

	protected void setDisplayFormat(String displayFormat) {
		this.displayFormat = displayFormat;
	}
	
	
}
