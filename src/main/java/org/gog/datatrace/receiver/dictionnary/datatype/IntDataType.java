package org.gog.datatrace.receiver.dictionnary.datatype;

public class IntDataType extends AbstractFixedSizeDataType {

	public IntDataType(String displayFormat) {
		super(DataType.INT, 2, displayFormat);
	}

	@Override
	public Object getValue(byte[] bytes) {
		int result = 
			(bytes[1]<< 8) & 0x0000ff00 |
			(bytes[0]<< 0) & 0x000000ff ;
		return result;
	}
}
