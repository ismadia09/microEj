package project.lab;

import java.io.IOException;

import ej.animation.Animation;
import ej.animation.Animator;
import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.motion.Motion;
import ej.motion.bounce.BounceEaseOutMotion;
import ej.style.Style;
import ej.style.container.AlignmentHelper;
import ej.style.container.Rectangle;
import ej.style.util.StyleHelper;
import ej.widget.StyledWidget;
import project.model.CircularBoundedRangeModel;

/**
 * A personalised widget.
 */
public class GameWidget extends StyledWidget implements Animation {

	/**
	 * Diameter of the base of the hand.
	 */
	private static final int DIAMETER = 10;

	/**
	 * Y offset of the base of the hand.
	 */
	private static final int BASE_Y_OFFSET = 15;

	/**
	 * Size of the hand.
	 */
	private static final int HAND_SIZE = 40;

	/**
	 * Angle when the model is at its minimum.
	 */
	private static final float INITIAL_ANGLE = (float) Math.toRadians(20);

	/**
	 * Angle difference between the minimum and maximum value.
	 */
	private static final float MAX_ANGLE = (float) Math.toRadians(220);

	private static final long MOTION_DURATION = 0;

	// /**
	// * Duration of the motion.
	// */
	// private static final int MOTION_DURATION = 1000;

	/**
	 * Background image of the widget.
	 */
	private Image background;

	/**
	 * Model of the hand.
	 */
	private final CircularBoundedRangeModel model;

	// /**
	// * Motion for automatic movement.
	// */
	private Motion motion;

	// /**
	// * Whether the widget is be animated.
	// */
	private boolean animated;

	private Image ball;
	private Image but;

	public GameWidget() {
		try {
			this.but = Image.createImage("/images/cage.png");
			this.ball = Image.createImage("/images/ball.png");
			this.background = Image.createImage("/images/bg.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.model = new CircularBoundedRangeModel(0, 1000, 1000);
	}

	private void moveBall(GraphicsContext g) {
		int x = 50;
		int y = 50;
		for (int i = 0; i < 100; i++) {
			g.drawImage(this.ball, x, y, GraphicsContext.TOP | GraphicsContext.LEFT);
			x += 2;
			y += 8;
		}

	}

	@Override
	public void renderContent(GraphicsContext g, Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		g.drawImage(this.background, 0, 0, GraphicsContext.TOP | GraphicsContext.LEFT);
		// moveBall(g);
		initBallPosition(g);
		initGoalsPosition(g);

	}

	@Override
	public Rectangle validateContent(Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		Rectangle rectangle = new Rectangle();
		rectangle.setSize(this.background.getWidth(), this.background.getHeight());
		return rectangle;
	}

	/**
	 * Gets the Y position of the top of the Hand.
	 *
	 * @param percentComplete
	 *            the percentage of the current value.
	 * @param centerY
	 *            the Y coordinate of the base of the hand.
	 * @return the Y coordinate
	 */
	@SuppressWarnings("unused")
	private int getHandY(float percentComplete, int centerY) {
		double sin = Math.sin(percentComplete * MAX_ANGLE - INITIAL_ANGLE);
		int i = (int) (centerY - HAND_SIZE * sin);
		return i;
	}

	/**
	 * Gets the X position of the top of the Hand.
	 *
	 * @param percentComplete
	 *            the percentage of the current value.
	 * @param centerX
	 *            the X coordinate of the base of the hand.
	 * @return the X coordinate
	 */
	@SuppressWarnings("unused")
	private int getHandX(float percentComplete, int centerX) {
		double cos = Math.cos((1 - percentComplete) * MAX_ANGLE - INITIAL_ANGLE);
		int i = (int) (centerX + HAND_SIZE * cos);
		return i;
	}

	/**
	 * Gets the value of a x and y position
	 *
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return the position
	 */
	@SuppressWarnings("unused")
	private int getValue(int x, int y) {
		// Compute the position of the base of the hand
		Rectangle boundsHint = new Rectangle(0, 0, getWidth(), getHeight());
		Style style = getStyle();
		Rectangle bounds = StyleHelper.computeContentBounds(boundsHint, style);
		int alignment = style.getAlignment();
		int width = this.background.getWidth();
		int height = this.background.getHeight();
		int centerX = AlignmentHelper.computeXLeftCorner(width, 0, bounds.getWidth(), alignment) + bounds.getX();
		int centerY = AlignmentHelper.computeYTopCorner(height, 0, bounds.getHeight(), alignment) + bounds.getY();
		centerX += width / 2;
		centerY += height - DIAMETER - BASE_Y_OFFSET;

		// Compute the angle.
		float deltaX = x - centerX;
		float deltaY = y - centerY;
		float angle = (float) Math.atan2(deltaX, deltaY);
		if (angle < 0) { // Left position of the circle
			angle = (float) (-angle - INITIAL_ANGLE - Math.PI / 4);
		} else { // Right position of the circle
			angle = (float) (Math.PI + Math.PI / 2 - angle + INITIAL_ANGLE);
		}

		float percent = (angle / MAX_ANGLE);
		return (int) (percent * this.model.getMaximum() + this.model.getMinimum());
	}

	@Override
	public boolean tick(long currentTimeMillis) {
		this.model.incrementValue();
		// Trigger a repaint action in the UI Thread.
		this.model.setValue(this.motion.getCurrentValue());
		// When the motion finished, start a new one.
		if (this.motion.isFinished()) {
			// Change the motion direction.
			int maximum = this.model.getMaximum();
			if (this.motion.getStartValue() == maximum) {
				this.motion = new BounceEaseOutMotion(this.model.getMinimum(), maximum, MOTION_DURATION);
			} else {
				this.motion = new BounceEaseOutMotion(maximum, this.model.getMinimum(), MOTION_DURATION);
				this.motion.start();
			}
			repaint();
		}
		return this.animated;
	}

	@Override
	public void showNotify() {
		super.showNotify();
		this.motion = new BounceEaseOutMotion(this.model.getMinimum(), this.model.getMaximum(), MOTION_DURATION);
		this.motion.start();
		ServiceLoaderFactory.getServiceLoader().getService(Animator.class, Animator.class).startAnimation(this);
		this.animated = true;
	}

	@Override
	public void hideNotify() {
		super.hideNotify();
		ServiceLoaderFactory.getServiceLoader().getService(Animator.class, Animator.class).stopAnimation(this);
		this.animated = false;
	}

	@Override
	public boolean handleEvent(int event) {
		if (Event.getType(event) == Event.POINTER) {
			this.animated = false;
			if (Pointer.isPressed(event) || Pointer.isDragged(event)) {
				Pointer pointer = (Pointer) Event.getGenerator(event);
				this.model
						.setValue(getValue(getRelativeX(pointer.getAbsoluteX()), getRelativeY(pointer.getAbsoluteY())));
				repaint();
				return true;
			}
		}
		return super.handleEvent(event);
	}

	private void initBallPosition(GraphicsContext g) {
		int x = g.getClipWidth() / 2;
		int y = g.getClipHeight() - 20;
		g.drawImage(this.ball, x, y, GraphicsContext.TOP | GraphicsContext.LEFT);
	}

	private void initGoalsPosition(GraphicsContext g) {
		int minX = 20;
		int minY = 50;
		int maxX = 480;
		int maxY = 272;
		// Random ran = new Random();
		int x = (int) (minX + (Math.random() * (maxX - minX)));
		int y = (int) (minY + (Math.random() * (maxY - minY)));
		g.drawImage(this.but, x, y, GraphicsContext.TOP | GraphicsContext.LEFT);

	}

}
