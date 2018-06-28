/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.pages;

import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import project.lab.GameWidget;
import project.lab.ProjectActivity;

/**
 *
 */
public class ProjectGamePage extends Page {

	Split sp = new Split();

	public ProjectGamePage() {
		this.sp = new Split(false, (float) 0.10);
		// Gameplay gp = new Gameplay();
		Split headerContainer = new Split(true, 0.25f);
		Label title = new Label("Bon courage");
		title.addClassSelector("TITLE");
		ButtonWrapper sback = new ButtonWrapper();
		sback.setWidget(new Label("Back"));
		sback.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				ProjectActivity.home();
			}
		});
		headerContainer.setFirst(sback);
		headerContainer.setLast(title);
		this.sp.setFirst(headerContainer);
		this.sp.setLast(new GameWidget());
		setWidget(this.sp);
	}

}
