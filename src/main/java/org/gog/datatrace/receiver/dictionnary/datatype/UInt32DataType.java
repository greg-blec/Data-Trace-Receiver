package org.gog.datatrace.receiver.dictionnary.datatype;

public class UInt32DataType extends AbstractFixedSizeDataType {

	public UInt32DataType(String displayFormat) {
		super(DataType.UINT32, 4, displayFormat);
	}
	
	@Override
	public Object getValue(byte[] bytes) {
		int result = 
			(0x0000000000000000 << 32) & 0x0000000000000000 |
			(bytes[3]           << 24) & 0x00000000ff000000 |
			(bytes[2]           << 16) & 0x0000000000ff0000 |
			(bytes[1]           <<  8) & 0x000000000000ff00 |
			(bytes[0]           <<  0) & 0x00000000000000ff ;
		return result;
	}
}
