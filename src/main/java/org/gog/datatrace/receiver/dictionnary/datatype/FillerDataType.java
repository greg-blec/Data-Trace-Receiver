package org.gog.datatrace.receiver.dictionnary.datatype;

public class FillerDataType extends AbstractFreeSizeDataType {

	public FillerDataType(int numberOfBytes) {
		super(DataType.FILLER, numberOfBytes, null);
	}
	
	@Override
	public Object getValue(byte[] bytes) {
		return null;
	}
}
