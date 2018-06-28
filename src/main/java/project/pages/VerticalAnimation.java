/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.pages;

import java.util.TimerTask;

/**
 *
 */
public class VerticalAnimation extends TimerTask {

	private final int ABSOLUTE_INCREMENT = 2;
	private static final int ANIMATION_PERIOD = 1000 / 60;
	public final GoalAnimation animated;
	private int verticalIncrement = this.ABSOLUTE_INCREMENT;
	private final int topLimit;
	private final int bottomLimit;

	public VerticalAnimation(GoalAnimation animated) {
		this.animated = animated;
		final int animatedImageHalfHeight = animated.goal.getHeight() / 2;
		this.topLimit = animatedImageHalfHeight;
		this.bottomLimit = animated.getDisplay().getHeight() - animatedImageHalfHeight;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.animated.goalY += this.verticalIncrement;
		if (this.animated.goalY > this.bottomLimit) {
			this.animated.goalY = this.bottomLimit;
			this.verticalIncrement = -this.ABSOLUTE_INCREMENT;
		} else {
			if (this.animated.goalY < this.topLimit) {
				this.animated.goalY = this.topLimit;
				this.verticalIncrement = this.ABSOLUTE_INCREMENT;
			}
		}
		this.animated.repaint();
	}

}
