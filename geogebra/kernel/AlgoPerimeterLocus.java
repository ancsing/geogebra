/* 
 GeoGebra - Dynamic Mathematics for Everyone
 http://www.geogebra.org

 This file is part of GeoGebra.

 This program is free software; you can redistribute it and/or modify it 
 under the terms of the GNU General Public License as published by 
 the Free Software Foundation.
 
 */

package geogebra.kernel;

import java.util.ArrayList;

/**
 * Adapted from AlgoPerimeterPoly
 */
public class AlgoPerimeterLocus extends AlgoElement {

	// Take a polygon as input
	private GeoLocus locus;

	// Output is a GeoNumeric (= a number)
	private GeoNumeric circum;

	AlgoPerimeterLocus(Construction cons, String label, GeoLocus locus) {
		this(cons, locus);
		circum.setLabel(label);
	}

	AlgoPerimeterLocus(Construction cons, GeoLocus locus) {
		super(cons);
		this.locus = locus;

		circum = new GeoNumeric(cons);
		setInputOutput();
		compute();
	}

	public String getClassName() {
		return "AlgoPerimeterLocus";
	}

	protected void setInputOutput() {
		input = new GeoElement[1];
		input[0] = locus;

		output = new GeoElement[1];
		output[0] = circum;
		setDependencies();
	}

	/**
	 * Compute circumference by adding up the length of its segemnts.
	 */
	protected final void compute() {
		if (!locus.isDefined()) {
			circum.setUndefined();
			return;
		}
				
		ArrayList<MyPoint> points = locus.getPoints();
		
		if (points.size() < 2) {
			circum.setUndefined();
			return;			
		}
		
		MyPoint lastPoint = points.get(0);
		
		double length = 0;
		for (int i = 1; i < points.size(); i++) {
			MyPoint pt = points.get(i);
			length = length + pt.distance(lastPoint);
			lastPoint = pt;
		}
		circum.setValue(length);
	}

	/**
	 * Get the GeoPolygon's circumference.
	 * 
	 * @return circumference
	 */
	GeoNumeric getResult() {
		return circum;
	}	
}
