package org.gog.datatrace.tester;

import org.gog.datatrace.receiver.Tracer;

public class RawTextTracer {

	public static void main(String[] args) {
		Tracer tracer = Tracer.getTracer();
		tracer.run("COM11", "ft232r");
	}

}
