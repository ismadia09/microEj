/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.pages;

import java.util.ArrayList;

import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Scroll;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import project.lab.ProjectActivity;

/**
 *
 */
public class ProjectGameScorePage extends Page {
	private static Scroll scroll;
	private static List list;

	public ProjectGameScorePage() {
		list = new List(false);
		for (int i = 1; i <= 20; i++) {
			Label lbl = new Label("ISMA-ITEM-" + String.valueOf(i));
			lbl.addClassSelector("ITEM");
			list.add(lbl);
		}
		ButtonWrapper sback = new ButtonWrapper();
		sback.setWidget(new Label("Back"));
		sback.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				ProjectActivity.home();
			}
		});
		list.add(sback);
		scroll = new Scroll(false, true);
		scroll.setWidget(list);
		setWidget(scroll);
	}

	public ProjectGameScorePage(ArrayList scoreList) {
		list = new List(false);
		for (int i = 0; i < scoreList.size(); i++) {
			Label lbl = new Label("ISMA-ITEM-" + scoreList.get(i));
			lbl.addClassSelector("ITEM");
			list.add(lbl);
		}
		ButtonWrapper sback = new ButtonWrapper();
		sback.setWidget(new Label("Back"));
		sback.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				ProjectActivity.home();
			}
		});
		list.add(sback);
		scroll = new Scroll(false, true);
		scroll.setWidget(list);
		setWidget(scroll);
	}
}
