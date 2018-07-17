/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.pages;

import java.util.ArrayList;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import project.lab.GameWidget;
import project.lab.ProjectActivity;

/**
 *
 */
public class ProjectMainPage extends Page {

	Split GRD = new Split(true, 0.25f);

	private final Split container;

	public ProjectMainPage() {

		this.container = new Split(false, (float) 0.2);
		List Btnlist = new List(false);

		Label title = new Label("BIENVENUE SUR LE JEU");
		title.addClassSelector("TITLE");

		ButtonWrapper exitBtn = new ButtonWrapper();
		exitBtn.setWidget(new Label("EXIT"));
		exitBtn.addClassSelector("EXTBTN");

		ButtonWrapper jouer = new ButtonWrapper();
		jouer.setWidget(new Label("JOUER"));

		ButtonWrapper scores = new ButtonWrapper();
		scores.setWidget(new Label("SCORES"));

		ButtonWrapper settings = new ButtonWrapper();
		settings.setWidget(new Label("SETTINGS"));

		Btnlist.add(jouer);
		Btnlist.add(scores);
		Btnlist.add(settings);

		jouer.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ProjectActivity.show(new ProjectGamePage(10));

			}
		});

		scores.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ArrayList scores = GameWidget.scoreList;

				ProjectActivity.show(new ProjectGameScorePage(scores));

			}
		});

		settings.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ProjectActivity.show(new ProjectGameSettings());

			}
		});

		exitBtn.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				// use the ExitHandler to stop application
				System.out.println("Exit");
				ExitHandler exitHandler = ServiceLoaderFactory.getServiceLoader().getService(ExitHandler.class);
				if (exitHandler != null) {
					exitHandler.exit();
				}
			}
		});

		this.GRD.setFirst(exitBtn);
		this.GRD.setLast(title);

		this.container.setLast(Btnlist);
		this.container.setFirst(this.GRD);

		setWidget(this.container);

	}
}
