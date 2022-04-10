package org.gog.datatrace.receiver.dictionnary.datatype;

public class UInt8DataType extends AbstractFixedSizeDataType {

	public UInt8DataType(String displayFormat) {
		super(DataType.UINT8, 1, displayFormat);
	}

	@Override
	public Object getValue(byte[] bytes) {
		int result = 
			(0x00000000 << 8) & 0x0000ff00 |
			(bytes[0]   << 0) & 0x000000ff ;
		return result;
	}
}
