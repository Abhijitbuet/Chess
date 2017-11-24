package abhijit.chess;

public class whiteSolver {
	
	int f=0,g=0,h=0;
	int[] solution=new int[4];
	
	public void whiteSolver(String[][] piece){
		
		
		
	
	}

	public int[] getSolution(){
		
		
		return solution;
	}
	public int estimation(String[][] piece){
	int pieceVal=0;
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				if(piece[i][j].equals("blackQueen"))pieceVal-=9;
				else if(piece[i][j].equals("blackRook"))pieceVal-=5;
				else if(piece[i][j].equals("blackKnight"))pieceVal-=3;
				else if(piece[i][j].equals("blackBishop"))pieceVal-=3;
				else if(piece[i][j].equals("blackPawn"))pieceVal--;
				else if(piece[i][j].equals("whiteRook"))pieceVal+=5;
				else if(piece[i][j].equals("whiteQueen"))pieceVal+=9;
				else if(piece[i][j].equals("whitePawn"))pieceVal++;
				else if(piece[i][j].equals("whiteKnight"))pieceVal+=3;
				else if(piece[i][j].equals("whiteBishop"))pieceVal+=3;
				
			}
		return pieceVal;
	}
	public static boolean isLegal(int pastX, int pastY, int presentX, int presentY,String[][] piece,int playerTurn) {

		if (pastX == presentX && pastY == presentY)
			return false;
		else if (piece[pastX][pastY].equals("b"))
			return false;

		else if (playerTurn == 1 && piece[pastX][pastY].startsWith("b")
				|| playerTurn == 2 && piece[pastX][pastY].startsWith("w"))
			return false;
		else if (piece[pastX][pastY].startsWith("black")
				&& piece[presentX][presentY].startsWith("black")
				|| piece[pastX][pastY].startsWith("white")
				&& piece[presentX][presentY].startsWith("white"))
			return false;
		else if (piece[pastX][pastY].equals("whitePawn")
				|| piece[pastX][pastY].equals("blackPawn")) {
			pawn p = new pawn(0, 0);
			return p.checkLegality(pastX, pastY, presentX, presentY, piece);
		} else if (piece[pastX][pastY].equals("whiteRook")
				|| piece[pastX][pastY].equals("blackRook")) {
			rook r = new rook(0, 0);
			
			
			return r.checkLegality(pastX, pastY, presentX, presentY, piece);
		} else if (piece[pastX][pastY].equals("whiteQueen")
				|| piece[pastX][pastY].equals("blackQueen")) {
			queen q = new queen(0, 0);
			return q.checkLegality(pastX, pastY, presentX, presentY, piece);
		} else if (piece[pastX][pastY].equals("whiteBishop")
				|| piece[pastX][pastY].equals("blackBishop")) {
			bishop b = new bishop(0, 0);
			return b.checkLegality(pastX, pastY, presentX, presentY, piece);
		} else if (piece[pastX][pastY].equals("whiteKnight")
				|| piece[pastX][pastY].equals("blackKnight")) {
			knight b = new knight(0, 0);
			return b.checkLegality(pastX, pastY, presentX, presentY, piece);
		} else {
			king k = new king(0, 0);
			
			return k.checkLegality(pastX, pastY, presentX, presentY, piece);

		}
	}
}
