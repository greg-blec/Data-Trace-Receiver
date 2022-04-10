package org.gog.datatrace.receiver.dictionnary.datatype;

public class FloatDataType extends AbstractFixedSizeDataType {

	public FloatDataType(String displayFormat) {
		super(DataType.FLOAT, 4, displayFormat);
	}
	
	@Override
	public Object getValue(byte[] bytes) {
		float result =
			Float.intBitsToFloat(
				(bytes[3] << 24) & 0xff000000 |
				(bytes[2] << 16) & 0x00ff0000 |
				(bytes[1] <<  8) & 0x0000ff00 |
				(bytes[0] <<  0) & 0x000000ff ) ;
		if ( Float.isNaN(result)) result = 0.0f;
		return result;
	}
}
