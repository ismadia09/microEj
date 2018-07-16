/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.pages;

import ej.widget.basic.Button;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import project.lab.ProjectActivity;

/**
 *
 */
public class ProjectGameSettings extends Page {
	Button BT1 = new Button("Diallo Ismael Main Page");
	Button BT2 = new Button("Exit me");
	Split GRD = new Split(true, 0.25f);

	private final Split container;

	public ProjectGameSettings() {

		this.container = new Split(false, (float) 0.2);
		List Btnlist = new List(false);

		Label title = new Label("BIENVENUE SUR LE JEU");
		title.addClassSelector("TITLE");

		ButtonWrapper sback = new ButtonWrapper();
		sback.setWidget(new Label("Back"));
		sback.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				ProjectActivity.home();
			}
		});

		ButtonWrapper facile = new ButtonWrapper();
		facile.setWidget(new Label("FACILE"));

		ButtonWrapper normal = new ButtonWrapper();
		normal.setWidget(new Label("NORMAL"));

		ButtonWrapper difficile = new ButtonWrapper();
		difficile.setWidget(new Label("DIFFICLE"));

		ButtonWrapper impossible = new ButtonWrapper();
		impossible.setWidget(new Label("IMPOSSIBLE"));

		Btnlist.add(facile);
		Btnlist.add(normal);
		Btnlist.add(difficile);
		Btnlist.add(impossible);

		facile.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ProjectActivity.show(new ProjectGamePage(20));

			}
		});
		normal.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ProjectActivity.show(new ProjectGamePage(10));

			}
		});
		difficile.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ProjectActivity.show(new ProjectGamePage(7));

			}
		});
		impossible.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ProjectActivity.show(new ProjectGamePage(3));

			}
		});

		this.GRD.setFirst(sback);
		this.GRD.setLast(title);

		this.container.setLast(Btnlist);
		this.container.setFirst(this.GRD);

		setWidget(this.container);
	}

}
