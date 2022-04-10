package org.gog.datatrace.receiver.dictionnary.datatype;

public class UInt16DataType extends AbstractFixedSizeDataType {

	public UInt16DataType(String displayFormat) {
		super(DataType.UINT16, 2, displayFormat);
	}
	
	@Override
	public Object getValue(byte[] bytes) {
		int result = 
			(0x00000000 << 16) & 0x00ff0000 |
			(bytes[1]   <<  8) & 0x0000ff00 |
			(bytes[0]   <<  0) & 0x000000ff ;
		return result;
	}
}
