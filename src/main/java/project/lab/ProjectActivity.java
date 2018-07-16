/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.lab;

import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.mwt.Desktop;
import ej.mwt.MWT;
import ej.mwt.Panel;
import ej.style.Stylesheet;
import ej.style.background.SimpleRoundedPlainBackground;
import ej.style.outline.SimpleOutline;
import ej.style.selector.ClassSelector;
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.ChildCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.wadapps.app.Activity;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.transition.SlideScreenshotTransitionContainer;
import ej.widget.container.transition.TransitionContainer;
import ej.widget.navigation.page.Page;
import project.pages.ProjectMainPage;

/**
 *
 */
public class ProjectActivity implements Activity {

	public static TransitionContainer transition;

	public static void show(Page p) {
		transition.show(p, true);
	}

	public static void home() {
		transition.show(new ProjectMainPage(), false);
	}

	private void initializeStyle() {
		Stylesheet sts = StyleHelper.getStylesheet();
		EditableStyle btnStyle = new EditableStyle();
		SimpleOutline btnMargin = new SimpleOutline(5);

		btnStyle.setMargin(btnMargin);
		btnStyle.setPadding(btnMargin);
		SimpleOutline myOutline = new SimpleOutline(5);
		btnStyle.setMargin(myOutline);
		SimpleRoundedPlainBackground myBackground = new SimpleRoundedPlainBackground(20);
		btnStyle.setBackground(myBackground);
		btnStyle.setBackgroundColor(Colors.GREEN);
		btnStyle.setForegroundColor(Colors.WHITE);
		btnStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		// Rule for labels child of buttons
		TypeSelector lblSel = new TypeSelector(Label.class);
		TypeSelector btnSel = new TypeSelector(ButtonWrapper.class);
		ChildCombinator parentBtnSel = new ChildCombinator(btnSel, lblSel);
		sts.addRule(parentBtnSel, btnStyle);

	}

	private void initialiseTitleStyle() {
		Stylesheet sts = StyleHelper.getStylesheet();
		EditableStyle titleStyle = new EditableStyle();

		titleStyle.setBackgroundColor(Colors.WHITE);
		titleStyle.setForegroundColor(Colors.GREEN);
		titleStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		/*
		 * ComplexRectangularBorder titleBorder = new ComplexRectangularBorder(); titleBorder.setBottom(2);
		 * titleStyle.setBorder(titleBorder); titleStyle.setBorderColor(Colors.GRAY);
		 */

		ClassSelector titleSelector = new ClassSelector("TITLE");
		sts.addRule(titleSelector, titleStyle);

	}

	private void initialiseItemStyle() {
		Stylesheet sts = StyleHelper.getStylesheet();
		EditableStyle itemStyle = new EditableStyle();

		itemStyle.setForegroundColor(Colors.GREEN);
		itemStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		ClassSelector itemSelector = new ClassSelector("ITEM");
		sts.addRule(itemSelector, itemStyle);

	}

	private void initializeExitStyle() {
		Stylesheet sts = StyleHelper.getStylesheet();
		EditableStyle btnStyle = new EditableStyle();
		SimpleOutline btnMargin = new SimpleOutline(5);

		btnStyle.setMargin(btnMargin);
		btnStyle.setPadding(btnMargin);
		SimpleOutline myOutline = new SimpleOutline(5);
		btnStyle.setMargin(myOutline);
		SimpleRoundedPlainBackground myBackground = new SimpleRoundedPlainBackground(20);
		btnStyle.setBackground(myBackground);
		btnStyle.setBackgroundColor(Colors.NAVY);
		btnStyle.setForegroundColor(Colors.YELLOW);
		btnStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		// Rule for labels child of buttons
		TypeSelector lblSel = new TypeSelector(Label.class);
		TypeSelector btnSel = new TypeSelector(ButtonWrapper.class);
		ClassSelector extBtnSelector = new ClassSelector("EXTBTN");
		sts.addRule(extBtnSelector, btnStyle);

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		MicroUI.start();
		Panel pnl = new Panel();
		Desktop dsk = new Desktop();
		transition = new SlideScreenshotTransitionContainer(MWT.LEFT, false, false);
		transition.show(new ProjectMainPage(), false);
		initializeStyle();
		initialiseTitleStyle();
		initialiseItemStyle();
		// initializeExitStyle();
		pnl.setWidget(transition);
		pnl.showFullScreen(dsk);
		dsk.show();

	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

}