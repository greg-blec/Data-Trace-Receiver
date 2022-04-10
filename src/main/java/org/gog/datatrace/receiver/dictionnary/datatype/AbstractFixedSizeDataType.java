package org.gog.datatrace.receiver.dictionnary.datatype;

public abstract class AbstractFixedSizeDataType extends AbstractDataType {

	public AbstractFixedSizeDataType(DataType type, int numberOfBytes, String displayFormat) {
		super(type, numberOfBytes, displayFormat);
	}
}
