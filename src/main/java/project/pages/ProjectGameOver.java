/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.pages;

import java.util.ArrayList;

import ej.widget.basic.Button;
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
public class ProjectGameOver extends Page {
	Button BT1 = new Button("Diallo Ismael Main Page");
	Button BT2 = new Button("Exit me");
	Split GRD = new Split(true, 0.000000001f);

	private final Split container;

	int difficulty = 0;

	public ProjectGameOver(int score, final int difficulty) {
		this.difficulty = difficulty;
		this.container = new Split(false, (float) 0.2);
		List Btnlist = new List(false);

		Label title = new Label("GAME OVER");
		title.addClassSelector("TITLE");

		ButtonWrapper sback = new ButtonWrapper();
		sback.setWidget(new Label("Back"));
		sback.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				ProjectActivity.home();
			}
		});

		ButtonWrapper rejouer = new ButtonWrapper();
		rejouer.setWidget(new Label("REJOUER"));

		ButtonWrapper menuPrincipal = new ButtonWrapper();
		menuPrincipal.setWidget(new Label("MENU PRINCIPAL"));

		ButtonWrapper scores = new ButtonWrapper();
		scores.setWidget(new Label("SCORES"));

		Btnlist.add(rejouer);
		Btnlist.add(scores);
		Btnlist.add(menuPrincipal);

		rejouer.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ProjectActivity.show(new ProjectGamePage(difficulty));

			}
		});
		menuPrincipal.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ProjectActivity.home();

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

		this.GRD.setFirst(sback);
		this.GRD.setLast(title);

		Split gameOverContainer = new Split(false, (float) 0.5);

		String gameOverString = "VOUS AVEZ PERDU \n Vous avez marqué " + score + " buts";
		Label gameOver = new Label(gameOverString);
		gameOver.addClassSelector("TITLE");

		gameOverContainer.setFirst(gameOver);
		gameOverContainer.setLast(Btnlist);

		this.container.setLast(gameOverContainer);
		this.container.setFirst(this.GRD);

		setWidget(this.container);

	}
}
