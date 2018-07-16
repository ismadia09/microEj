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
public class ProjectGameOver extends Page {
	Button BT1 = new Button("Diallo Ismael Main Page");
	Button BT2 = new Button("Exit me");
	Split GRD = new Split(true, 0.25f);

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

		Btnlist.add(rejouer);
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

		this.GRD.setFirst(sback);
		this.GRD.setLast(title);

		Split gameOverContainer = new Split(false, (float) 0.7);

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
