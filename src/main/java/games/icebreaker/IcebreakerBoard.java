package games.icebreaker;

import java.util.ArrayList;

import iialib.games.model.IBoard;
import iialib.games.model.Score;

public class IcebreakerBoard implements IBoard<IcebreakerMove, IcebreakerRole, IcebreakerBoard> {

	@Override
	public ArrayList<IcebreakerMove> possibleMoves(IcebreakerRole playerRole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IcebreakerBoard play(IcebreakerMove move, IcebreakerRole playerRole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidMove(IcebreakerMove move, IcebreakerRole playerRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Score<IcebreakerRole>> getScores() {
		// TODO Auto-generated method stub
		return null;
	}

}
