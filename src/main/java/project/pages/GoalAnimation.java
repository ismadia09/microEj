/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.pages;

import java.io.IOException;
import java.util.Timer;

import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.util.EventHandler;

/**
 *
 */
public class GoalAnimation extends Displayable {

	private static final int ANIMATION_PERIOD = 1000 / 60; // in ms - 60 frames per second

	public final int goalX;
	public int goalY;
	public Image goal;

	/**
	 * @param display
	 */
	public GoalAnimation(Display display) {
		super(display);
		// Starts at the center of the screen.
		this.goalX = display.getWidth() / 2;
		this.goalY = display.getHeight() / 2;

		try {
			this.goal = Image.createImage("/images/cage.png");
		} catch (IOException e) {
			throw new AssertionError(e);
		}

		this.show();
	}

	@Override
	public void paint(GraphicsContext g) {
		final int DISPLAY_WIDTH = getDisplay().getWidth();
		final int DISPLAY_HEIGHT = getDisplay().getHeight();

		// draw the image
		g.drawImage(this.goal, this.goalX, this.goalY, GraphicsContext.HCENTER | GraphicsContext.VCENTER);

	}

	@Override
	public EventHandler getController() {
		// TODO Auto-generated method stub
		return null;
	}

	public void animate() {
		VerticalAnimation animator = new VerticalAnimation(this);
		Timer animationTimer = new Timer();
		animationTimer.schedule(animator, ANIMATION_PERIOD, ANIMATION_PERIOD);
	}

}
