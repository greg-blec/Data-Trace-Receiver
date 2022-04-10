package org.gog.datatrace.tester;

import java.util.LinkedList;
import java.util.List;

import org.gog.datatrace.receiver.*;
import org.gog.datatrace.receiver.dictionnary.Dictionnary;
import org.gog.datatrace.receiver.dictionnary.DictionnaryEntry;
import org.gog.datatrace.receiver.dictionnary.datatype.ArrayOf;
import org.gog.datatrace.receiver.dictionnary.datatype.BooleanDataType;
import org.gog.datatrace.receiver.dictionnary.datatype.ByteDataType;
import org.gog.datatrace.receiver.dictionnary.datatype.CharDataType;
import org.gog.datatrace.receiver.dictionnary.datatype.FloatDataType;
import org.gog.datatrace.receiver.dictionnary.datatype.IntDataType;
import org.gog.datatrace.receiver.dictionnary.datatype.LongDataType;
import org.gog.datatrace.receiver.dictionnary.datatype.StringDataType;
import org.gog.datatrace.receiver.dictionnary.datatype.UInt16DataType;
import org.gog.datatrace.receiver.dictionnary.datatype.UInt32DataType;
import org.gog.datatrace.receiver.dictionnary.datatype.UInt8DataType;
import org.gog.datatrace.receiver.dictionnary.datatype.UnsignedInt;
import org.gog.datatrace.receiver.dictionnary.datatype.UnsignedLong;
import org.gog.datatrace.receiver.formatting.MessageFormat;;

public class CompteurAmiSuperTracer {

	public static void main(String[] args) {

		Dictionnary dictionnary = new Dictionnary();
		dictionnary.setIgnoreUnknownEntryId(true);
		DictionnaryEntry entry = null;

		entry = dictionnary.createEntry("MAIN");
		entry.addDataDefinition("fm", new IntDataType("%08d"));
		entry.addDataDefinition("timings", new ArrayOf(new UInt16DataType("%5d"), 6));

		entry = dictionnary.createEntry("SYTM");
		entry.addDataDefinition("ps", new UInt8DataType("%1d"));

		entry = dictionnary.createEntry("SSST");
		entry.addDataDefinition("ope", new CharDataType("%s"));
		entry.addDataDefinition("total", new UInt32DataType("%010d"));
		entry.addDataDefinition("partials", new ArrayOf(new UInt32DataType("%010d"), 3));
		entry.addDataDefinition("cpar", new UInt8DataType("%1d"));
		entry.addDataDefinition("dspm", new UInt8DataType("%1d"));
		entry.addDataDefinition("cstp", new UInt16DataType("%05d"));
		entry.addDataDefinition("timings", new ArrayOf(new UInt16DataType("%5d"), 4));

		entry = dictionnary.createEntry("RSEN");
		entry.addDataDefinition("th", new BooleanDataType("%1s"));
		entry.addDataDefinition("tovf", new BooleanDataType("%1s"));
		entry.addDataDefinition("tbt", new UInt16DataType("%05d"));
		entry.addDataDefinition("atbt", new UInt16DataType("%05d"));
		entry.addDataDefinition("tovfc", new UInt8DataType("%02d"));
		entry.addDataDefinition("dbts", new FloatDataType("%1.6f"));
		entry.addDataDefinition("timings", new ArrayOf(new UInt32DataType("%5d"), 4));

		entry = dictionnary.createEntry("SPCS");
		entry.addDataDefinition("ncst", new UInt32DataType("%08d"));
		entry.addDataDefinition("tds", new FloatDataType("%1.6f"));
		entry.addDataDefinition("pc", new UInt8DataType("%02d"));
		entry.addDataDefinition("cbsh", new FloatDataType("%03.2f"));
		entry.addDataDefinition("cs", new FloatDataType("%3.2f"));
		entry.addDataDefinition("cpssp", new UInt16DataType("%5d"));
		entry.addDataDefinition("cossp", new UInt16DataType("%5d"));
		entry.addDataDefinition("tassp", new UInt16DataType("%5d"));
		entry.addDataDefinition("timings", new ArrayOf(new UInt16DataType("%5d"), 9));

		entry = dictionnary.createEntry("SPCP");
//		entry.addDataDefinition("cs", new BooleanDataType("%1s"));
		entry.addDataDefinition("ncpt", new UInt32DataType("%08d"));
		entry.addDataDefinition("cup", new UInt16DataType("%5d"));
		entry.addDataDefinition("tap", new UInt16DataType("%5d"));
		entry.addDataDefinition("tapd", new IntDataType("%+3d"));
		entry.addDataDefinition("dir", new CharDataType("%s"));
		entry.addDataDefinition("sto", new BooleanDataType("%1s"));
		entry.addDataDefinition("t0", new UnsignedLong("%s"));
		entry.addDataDefinition("mid", new UnsignedLong("%s"));
		entry.addDataDefinition("timings", new ArrayOf(new UInt16DataType("%5d"), 2));

		entry = dictionnary.createEntry("STCU");
		entry.addDataDefinition("ts", new UnsignedLong("%s"));
		entry.addDataDefinition("in", new UInt32DataType("%s"));
		entry.addDataDefinition("sto", new CharDataType("%s"));
		entry.addDataDefinition("tp", new UnsignedInt("%s"));
		entry.addDataDefinition("cp", new UnsignedInt("%s"));
		entry.addDataDefinition("md", new UnsignedLong("%s"));
		entry.addDataDefinition("sd", new CharDataType("%s"));
		entry.addDataDefinition("timings", new ArrayOf(new UInt16DataType("%5d"), 5));

		entry = dictionnary.createEntry("ODOM");
		entry.addDataDefinition("dm", new UInt8DataType("%s"));
		entry.addDataDefinition("cp", new UInt8DataType("%s"));
		entry.addDataDefinition("tok", new UnsignedInt("%s"));
		entry.addDataDefinition("toh", new UnsignedInt("%s"));
		entry.addDataDefinition("p1k", new UnsignedInt("%s"));
		entry.addDataDefinition("p1h", new UnsignedInt("%s"));
		entry.addDataDefinition("p2k", new UnsignedInt("%s"));
		entry.addDataDefinition("p2h", new UnsignedInt("%s"));
		entry.addDataDefinition("p3k", new UnsignedInt("%s"));
		entry.addDataDefinition("p3h", new UnsignedInt("%s"));
		entry.addDataDefinition("timings", new ArrayOf(new UInt16DataType("%5d"), 2));

		entry = dictionnary.createEntry("DRIN");
		entry.addDataDefinition("ubp", new BooleanDataType("%1s"));
		entry.addDataDefinition("ubpsm", new UnsignedLong("% 8d"));
		entry.addDataDefinition("ubpd", new UnsignedLong("% 5d"));
		entry.addDataDefinition("ubpp", new BooleanDataType("%1s"));

		entry = dictionnary.createEntry("TEXT", "%s");
		entry.addDataDefinition("t", new StringDataType(200, "%s"));

		dictionnary.getEntry("MAIN").setSkipDisplay(true);
		dictionnary.getEntry("SYTM").setSkipDisplay(false);
		dictionnary.getEntry("SSST").setSkipDisplay(false);
		dictionnary.getEntry("RSEN").setSkipDisplay(false);
		dictionnary.getEntry("SPCS").setSkipDisplay(false);
		dictionnary.getEntry("SPCP").setSkipDisplay(false);
		dictionnary.getEntry("STCU").setSkipDisplay(false);
		dictionnary.getEntry("ODOM").setSkipDisplay(false);
		dictionnary.getEntry("TEXT").setSkipDisplay(false);
		dictionnary.getEntry("DRIN").setSkipDisplay(false);

		Tracer tracer = Tracer.getTracer(dictionnary);

		List<MessageFormat> formatList = new LinkedList<>();
		int mode = 0;

		switch (mode) {
		case 0:
			formatList.add(MessageFormat.FORMATTED);
			break;
		case 1:
			formatList.add(MessageFormat.BINARY_TO_STRING);
			break;
		case 2:
			formatList.add(MessageFormat.SPLITED_BINARY);
			break;
		case 3:
			formatList.add(MessageFormat.SPLITED_HEX);
			break;
		default:
			break;
		}

		tracer.run("COM5", "USB2.0-Serial", formatList);
	}

}
