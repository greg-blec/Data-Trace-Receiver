package org.gog.datatrace.receiver.dictionnary.datatype;

public class ByteDataType extends AbstractFixedSizeDataType {

	public ByteDataType(String displayFormat) {
		super(DataType.BYTE, 1, displayFormat);
	}

	@Override
	public Object getValue(byte[] bytes) {
		byte result = bytes[0];
		return result;
	}
}
