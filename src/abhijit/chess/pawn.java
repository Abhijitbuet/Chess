package abhijit.chess;

public class pawn {

	int x, y;

	public pawn(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public boolean checkLegality(int pastX, int pastY, int presentX,
			int presentY, String[][] pieces) {

		if (Math.abs(presentY - pastY) > 1 || Math.abs(pastX - presentX) > 2)
			return false;
		else if (pieces[pastX][pastY].startsWith("bl")) {
			if (presentX == pastX)
				return false;
			else if (presentX < pastX)
				return false;
			else if (presentX - pastX == 2 && pastY == presentY && pastX != 1)
				return false;

			else if (presentX - pastX == 1 && pastY == presentY
					&& !pieces[presentX][presentY].equals("b"))
				return false;
			else if (presentX - pastX == 1 && Math.abs(presentY - pastY) == 1
					&& !pieces[presentX][presentY].startsWith("w"))
				return false;
			if (Math.abs(presentX - pastX) == 1
					&& Math.abs(presentY - pastY) == 2
					|| Math.abs(presentX - pastX) == 2
					&& Math.abs(presentY - pastY) == 1)
				return false;
		} else {
			if (presentX == pastX)
				return false;
			else if (presentX > pastX)
				return false;
			else if (presentX - pastX == -2 && pastY == presentY && pastX != 6)
				return false;
			else if (presentX - pastX == -1 && pastY == presentY
					&& !pieces[presentX][presentY].equals("b"))
				return false;
			else if (presentX - pastX == -1 && Math.abs(presentY - pastY) == 1
					&& !pieces[presentX][presentY].startsWith("bl"))
				return false;
			if (Math.abs(presentX - pastX) == 1
					&& Math.abs(presentY - pastY) == 2
					|| Math.abs(presentX - pastX) == 2
					&& Math.abs(presentY - pastY) == 1)
				return false;
		}
		return true;
	}
}
