package com.github.pkrysztofiak.rxjavafxtutorial.examples.example037;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayersFactory {

	public static ObservableList<Player> createEnglandNationlTeam() {
		return FXCollections.observableArrayList(
				new Player("David", "Seaman", Position.G),
				new Player("Gary", "Neville", Position.CB),
				new Player("Tony", "Adams", Position.CB),
				new Player("Gareth", "Southgate", Position.LF),
				new Player("Stuart", "Pearce", Position.RF),
				new Player("Steve", "McManaman", Position.LMF),
				new Player("David", "Platt", Position.CMF),
				new Player("Paul", "Gascoigne", Position.CMF),
				new Player("Darren", "Anderton", Position.RMF),
				new Player("Alan", "Shearer", Position.CF),
				new Player("Teddy", "Sheringham", Position.CF));
	}
}