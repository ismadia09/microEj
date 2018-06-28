package project.model;

import ej.widget.model.DefaultBoundedRangeModel;

/**
 * A bounded range model with a value that can be incremented in a circular way.
 */
public class CircularBoundedRangeModel extends DefaultBoundedRangeModel {

	/**
	 * Percentage of the increase.
	 */
	private static final int INCREMENT_PERCENTAGE = 1;

	public CircularBoundedRangeModel(int minimum, int maximum, int initialValue) {
		super(minimum, maximum, initialValue);
	}

	@Override
	public synchronized void setValue(int value) {
		super.setValue(value);
	}

	@Override
	public synchronized void setMaximum(int maximum) {
		super.setMaximum(maximum);
	}

	@Override
	public synchronized void setMinimum(int minimum) {
		super.setMinimum(minimum);
	}

	/**
	 * Increments the value. If the value has reached the maximum, sets it to the minimum.
	 */
	public synchronized void incrementValue() {
		int value = getValue();
		int maximum = getMaximum();
		int minimum = getMinimum();
		if (value == maximum) {
			setValue(minimum);
		} else {
			setValue(value + INCREMENT_PERCENTAGE * (maximum - minimum) / 100);
		}
	}
}