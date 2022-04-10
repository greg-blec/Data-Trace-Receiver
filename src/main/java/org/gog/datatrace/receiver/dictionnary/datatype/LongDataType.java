package org.gog.datatrace.receiver.dictionnary.datatype;

public class LongDataType extends AbstractFixedSizeDataType {

	public LongDataType(String displayFormat) {
		super(DataType.LONG, 4, displayFormat);
	}
	
	@Override
	public Object getValue(byte[] bytes) {
		int result = 
			(bytes[3]<<24) & 0xff000000 |
			(bytes[2]<<16) & 0x00ff0000 |
			(bytes[1]<< 8) & 0x0000ff00 |
			(bytes[0]<< 0) & 0x000000ff ;
		return result;
	}
}
