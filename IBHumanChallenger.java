package games.icebreaker;

import java.util.Scanner;
import java.util.Set;

import iialib.games.model.IChallenger;

public class IBHumanChallenger implements IChallenger {
	
	private final String name;
	
	public IBHumanChallenger() {
		name = "Human";
	}

	@Override
	public String teamName() {
		return name;
	}

	@Override
	public void setRole(String role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iPlay(String move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void otherPlay(String move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String bestMove() {
		System.out.println("Entrer un coup :");
		String entreeClavier = new Scanner(System.in).nextLine();
		return entreeClavier;
	}

	@Override
	public String victory() {
		return "Youpi !";
	}

	@Override
	public String defeat() {
		return "Snif...";
	}

	@Override
	public String tie() {
		return "Ouf !";
	}

	@Override
	public String boardToString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setBoardFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> possibleMoves(String role) {
		// TODO Auto-generated method stub
		return null;
	}

}

