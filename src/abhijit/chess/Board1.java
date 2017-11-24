package abhijit.chess;

import java.util.ArrayList;

import android.widget.ImageView;

public class Board1  {
	//String[][] piece = new String[8][8];
	double h = 0;
	double f = 0;
	//int playerTurn = 1;
	int intent = 0;
	boolean blackShort=false;
	boolean blackLong=false;
	boolean whiteShort=false;
	boolean whiteLong=false;
	int i;
	int j;
	boolean[] castleFlag = new boolean[2];

	//ImageView[][] image = new ImageView[8][8];
	boolean specialMovePerformed = false;
	String[][] base = new String[8][8];
	String[][] piece = new String[8][8];
	boolean[][] isAttacked = new boolean[8][8];
	boolean[] check = new boolean[2];
	int playerTurn = 1;
	int pressedX, pressedY, prevX, prevY;

	public Board1(String[][] piece) {
		// super();
		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				this.piece[i][j]=piece[i][j];
				//System.out.print(piece[i][j]+ " ");
			}
			//System.out.println("");
		}
	//	System.out.println("");
		h=utility();
	}
	
	
	
	public Board1 getObject(int[]x,String[][] piece){
		
		String[][] modified = modifyPiece(piece, x[0], x[1], x[2],
				x[3]);
		final Board1 b=new Board1(modified);
	
		return b;
	//	return b;
		
	}
	
 	public boolean isWhiteCheckmated(String[][] piece) {
		// String[][] temp = piece;
		if (whiteInCheck(piece)) {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					if (piece[i][j].startsWith("white")) {
						for (int x = 0; x < 8; x++)
							for (int y = 0; y < 8; y++) {
								if (i == x && j == y)
									continue;
								else if (piece[x][y].startsWith("white"))
									continue;
								else if (piece[i][j].equals("whitePawn")) {
									pawn p = new pawn(0, 0);
									if (p.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("whiteRook")) {
									rook r = new rook(0, 0);
									if (r.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("whiteQueen")) {
									queen q = new queen(0, 0);
									if (q.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("whiteBishop")) {
									bishop b = new bishop(0, 0);
									if (b.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("whiteKing")) {
									king k = new king(0, 0);
									if (k.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								}
							}
					}
				}
			return true;
		} else
			return false;
	}

	

	public double utility() {
		double val = 0;
		
		if (isBlackCheckmated(piece)) 
			return -200;
		else if (isWhiteCheckmated(piece))
			return 200;
		else if (isDrawn(piece))
			return 0;
		
		for ( int i=0;i<8;i++ ) {
			for ( int j=0;i<8;i++ ){
				if(piece[i][j].startsWith("bl") && (i>1 && i<6) && (j>1 && j<6))val=val+.01;
				else if(piece[i][j].startsWith("w") && (i>1 && i<6) && (j>1 && j<6))val=val-.01;
				if (piece[i][j].equals("blackPawn"))
					{val++;
					
					}
				else if (piece[i][j].equals("blackRook"))
					val = val+5;
				
				else if (piece[i][j].equals("blackKnight"))
					val = val+3;
				
				else if (piece[i][j].equals("blackBishop"))
					val = val+3.25;
				
				else if (piece[i][j].equals("blackQueen"))
					val = val+9;
				
				else if (piece[i][j].equals("whitePawn"))
					val = val--;
				
				else if (piece[i][j].equals("whiteRook"))
					val = val-5;
				
				else if (piece[i][j].equals("whiteKnight"))
					val = val-3;

				else if (piece[i][j].equals("whiteBishop"))
					val = val-3.25;
				
				else if (piece[i][j].equals("whiteQueen"))
					val = val-9;
				
			}
			
			
			
		}
		
		return val;
	}
	public ArrayList<Board1>neighbors(String[][]piece,int turn){
		ArrayList<Board1>n=new ArrayList<Board1>();
		ArrayList<int[]> l=possibleMoves(piece, turn);
	
		String[][] real=new String[8][8];
		for(int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				real[i][j]=piece[i][j];
			}
		}
		 
		
		
		while(!l.isEmpty())
		{
			int[]temp=l.remove(0);
			String past=real[temp[0]][temp[1]];
			String present=real[temp[2]][temp[3]];
			

			String[][] modified = modifyPiece(real, temp[0], temp[1], temp[2],
					temp[3]);							
			Board1 b=new Board1(modified)	;	
			n.add(b);
					
					real[temp[0]][temp[1]]=past;
					real[temp[2]][temp[3]]=present;
					if(l.size()==0)return n;
					
		
		}
		return n;
	}
	
	

	int Solver() {
		
		
		return 0;
	}
	public ArrayList<int[]> possibleMoves(String[][] piece, int turn) {
		
		boolean old=castleFlag[0];
		boolean old1=castleFlag[1];
		
		ArrayList<int[]> moves = new ArrayList<int[]>();
		if (turn == 1) {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					if (piece[i][j].startsWith("white")) {
						for (int x = 0; x < 8; x++)
							for (int y = 0; y < 8; y++) {

								if (isLegal(i, j, x, y, piece)) {
									int[] temp = new int[4];
									temp[0] = i;
									temp[1] = j;
									temp[2] = x;
									temp[3] = y;
									moves.add(temp);
								
								}
							}
					}
				}

		}
		if (turn == 2) {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					if (piece[i][j].startsWith("black")) {
						for (int x = 0; x < 8; x++)
							for (int y = 0; y < 8; y++) {
								boolean st=isLegal(i, j, x, y, piece,turn);
								if (st) {
									int[] temp = new int[4];
									temp[0] = i;
									temp[1] = j;
									temp[2] = x;
									temp[3] = y;
									moves.add(temp);
								
								}
							}
					}
				}

		}
	castleFlag[0]=old;
	castleFlag[1]=old1;
	return moves;
}
	public String[][] modifyPiece(String[][] piece, int pastX, int pastY,
			int presentX, int presentY) {
		String[][] modified = piece;
		modified[presentX][presentY] = piece[pastX][pastY];
		modified[pastX][pastY] = "b";

		return modified;

	}
	public boolean isLegal(int pastX, int pastY, int presentX, int presentY,
			String[][] piece,int playerTurn) {
		blackShort=blackLong=whiteLong=whiteShort=false;
		String past = piece[pastX][pastY];
		String present = piece[presentX][presentY];

		String[][] modified = modifyPiece(piece, pastX, pastY, presentX,
				presentY);
		boolean whiteAttackedStatus = whiteInCheck(modified);
		boolean blackAttackedStatus = blackInCheck(modified);
		piece[pastX][pastY] = past;
		piece[presentX][presentY] = present;

		if (pastX == presentX && pastY == presentY)
			return false;

		else if (piece[pastX][pastY].equals("b"))
			return false;
		else if (pressedY - prevY == 2 && piece[prevX][prevY].endsWith("eKing")
				&& castleFlag[0] && isSafe_W(7, 4, piece)
				&& isSafe_W(7, 5, piece) && isSafe_W(7, 6, piece)) {
			
			whiteShort=true;
			//whiteShortCastle();
			specialMovePerformed = true;
			return true;
		} else if (presentY - pastY == -2
				&& piece[pastX][pastY].endsWith("eKing") && castleFlag[0]
				&& isSafe_W(7, 4, piece) && isSafe_W(7, 3, piece)
				&& isSafe_W(7, 2, piece) && piece[7][1].equals("b")) {
			whiteLong=true;
			//whiteLongCastle();
			castleFlag[0] = false;
			specialMovePerformed = true;
			return true;
		}
		if (playerTurn == 1 && whiteAttackedStatus) {
			return false;
		} else if (playerTurn == 2 && blackAttackedStatus)
			return false;
		else if (presentY - pastY == 2 && piece[pastX][pastY].endsWith("kKing")
				&& castleFlag[1] && isSafe_B(0, 4, piece)
				&& isSafe_B(0, 5, piece) && isSafe_B(0, 6, piece)) {

			//blackShortCastle();
			blackShort=true;
			specialMovePerformed = true;
			castleFlag[1] = false;
			return true;

		} else if (presentY - pastY == -2
				&& piece[pastX][pastY].endsWith("kKing") && castleFlag[1]
				&& isSafe_B(0, 4, piece) && isSafe_B(0, 3, piece)
				&& isSafe_B(0, 2, piece) && piece[0][1].equals("b")) {
			//blackLongCastle();
			blackLong=true;
			specialMovePerformed = true;
			castleFlag[1] = false;
			return true;

		} else if (piece[pastX][pastY].endsWith("Pawn")
				&& (presentX == 0 || presentX == 7)) {
			pawn p = new pawn(0, 0);
			if (p.checkLegality(pastX, pastY, presentX, presentY, piece)) {
				//pawnPromote();
				specialMovePerformed = true;

				return true;
			} else
				return false;

		} else if (playerTurn == 1 && piece[pastX][pastY].startsWith("b")
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
			if (piece[pastX][pastY].startsWith("w")
					&& r.checkLegality(pastX, pastY, presentX, presentY, piece) == true)
				castleFlag[0] = false;
			else if (piece[pastX][pastY].startsWith("bl")
					&& r.checkLegality(pastX, pastY, presentX, presentY, piece) == true)
				castleFlag[1] = false;
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
		} else if (piece[pastX][pastY].endsWith("King")) {
			king k = new king(0, 0);
			if (piece[pastX][pastY].startsWith("w")
					&& k.checkLegality(pastX, pastY, presentX, presentY, piece) == true)
				castleFlag[0] = false;
			else if (piece[pastX][pastY].startsWith("bl")
					&& k.checkLegality(pastX, pastY, presentX, presentY, piece) == true)
				castleFlag[1] = false;
			// boolean check=k.checkLegality(pastX, pastY, presentX, presentY,
			// piece);
			return k.checkLegality(pastX, pastY, presentX, presentY, piece);

		}
		return true;
	}
	public boolean isLegal(int pastX, int pastY, int presentX, int presentY,
			String[][] piece) {
		blackShort=blackLong=whiteLong=whiteShort=false;
		String past = piece[pastX][pastY];
		String present = piece[presentX][presentY];

		String[][] modified = modifyPiece(piece, pastX, pastY, presentX,
				presentY);
		boolean whiteAttackedStatus = whiteInCheck(modified);
		boolean blackAttackedStatus = blackInCheck(modified);
		piece[pastX][pastY] = past;
		piece[presentX][presentY] = present;

		if (pastX == presentX && pastY == presentY)
			return false;

		else if (piece[pastX][pastY].equals("b"))
			return false;
		else if (pressedY - prevY == 2 && piece[prevX][prevY].endsWith("eKing")
				&& castleFlag[0] && isSafe_W(7, 4, piece)
				&& isSafe_W(7, 5, piece) && isSafe_W(7, 6, piece)) {
			
			whiteShort=true;
			//whiteShortCastle();
			specialMovePerformed = true;
			return true;
		} else if (presentY - pastY == -2
				&& piece[pastX][pastY].endsWith("eKing") && castleFlag[0]
				&& isSafe_W(7, 4, piece) && isSafe_W(7, 3, piece)
				&& isSafe_W(7, 2, piece) && piece[7][1].equals("b")) {
			whiteLong=true;
			//whiteLongCastle();
			castleFlag[0] = false;
			specialMovePerformed = true;
			return true;
		}
		if (playerTurn == 1 && whiteAttackedStatus) {
			return false;
		} else if (playerTurn == 2 && blackAttackedStatus)
			return false;
		else if (presentY - pastY == 2 && piece[pastX][pastY].endsWith("kKing")
				&& castleFlag[1] && isSafe_B(0, 4, piece)
				&& isSafe_B(0, 5, piece) && isSafe_B(0, 6, piece)) {

			//blackShortCastle();
			blackShort=true;
			specialMovePerformed = true;
			castleFlag[1] = false;
			return true;

		} else if (presentY - pastY == -2
				&& piece[pastX][pastY].endsWith("kKing") && castleFlag[1]
				&& isSafe_B(0, 4, piece) && isSafe_B(0, 3, piece)
				&& isSafe_B(0, 2, piece) && piece[0][1].equals("b")) {
			//blackLongCastle();
			blackLong=true;
			specialMovePerformed = true;
			castleFlag[1] = false;
			return true;

		} else if (piece[pastX][pastY].endsWith("Pawn")
				&& (presentX == 0 || presentX == 7)) {
			pawn p = new pawn(0, 0);
			if (p.checkLegality(pastX, pastY, presentX, presentY, piece)) {
				//pawnPromote();
				specialMovePerformed = true;

				return true;
			} else
				return false;

		} else if (playerTurn == 1 && piece[pastX][pastY].startsWith("b")
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
			if (piece[pastX][pastY].startsWith("w")
					&& r.checkLegality(pastX, pastY, presentX, presentY, piece) == true)
				castleFlag[0] = false;
			else if (piece[pastX][pastY].startsWith("bl")
					&& r.checkLegality(pastX, pastY, presentX, presentY, piece) == true)
				castleFlag[1] = false;
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
		} else if (piece[pastX][pastY].endsWith("King")) {
			king k = new king(0, 0);
			if (piece[pastX][pastY].startsWith("w")
					&& k.checkLegality(pastX, pastY, presentX, presentY, piece) == true)
				castleFlag[0] = false;
			else if (piece[pastX][pastY].startsWith("bl")
					&& k.checkLegality(pastX, pastY, presentX, presentY, piece) == true)
				castleFlag[1] = false;
			// boolean check=k.checkLegality(pastX, pastY, presentX, presentY,
			// piece);
			return k.checkLegality(pastX, pastY, presentX, presentY, piece);

		}
		return true;
	}
	public boolean isBlackCheckmated(String[][] piece) {
		// String[][] temp = piece;
		if (blackInCheck(piece)) {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					if (piece[i][j].startsWith("black")) {
						for (int x = 0; x < 8; x++)
							for (int y = 0; y < 8; y++) {
								if (i == x && j == y)
									continue;
								else if (piece[x][y].startsWith("black"))
									continue;
								else if (piece[i][j].equals("blackPawn")) {
									pawn p = new pawn(0, 0);
									if (p.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("blackRook")) {
									rook r = new rook(0, 0);
									if (r.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("blackQueen")) {
									queen q = new queen(0, 0);
									if (q.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("blackBishop")) {
									bishop b = new bishop(0, 0);
									if (b.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("blackKing")) {
									king k = new king(0, 0);
									if (k.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								}
							}
					}
				}
			return true;
		} else
			return false;
	}

	public boolean isDrawn(String[][] piece) {
		int whiteBishopCount = 0;
		int blackBishopCount = 0;
		int whiteKnightCount = 0;
		int blackKnightCount = 0;

		// staleMate check
		if (isStaleMated(piece))
			return true;

		// insufficient_material_check:
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (piece[i][j].endsWith("Queen")
						|| piece[i][j].endsWith("Rook")
						|| piece[i][j].endsWith("Pawn"))
					return false;
				else if (piece[i][j].equals("whiteBishop")) {
					whiteBishopCount++;
					if (whiteBishopCount > 1)
						return false;
				} else if (piece[i][j].equals("blackBishop")) {
					blackBishopCount++;
					if (blackBishopCount > 1)
						return false;
				} else if (piece[i][j].equals("whiteKnight")) {
					whiteKnightCount++;
					if (whiteBishopCount > 0)
						return false;
					else if (whiteKnightCount > 2)
						return false;

				} else if (piece[i][j].equals("blackKnight")) {
					blackKnightCount++;
					if (blackBishopCount > 0)
						return false;
					else if (blackKnightCount > 2)
						return false;

				}
			}

		}

		return true;
	}
	

	public boolean isSafe_W(int X, int Y, String[][] piece) {
		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++) {
				if (piece[a][b].equals("blackPawn")) {
					pawn p = new pawn(0, 0);
					if (p.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("blackBishop")) {
					bishop B = new bishop(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("blackRook")) {
					rook B = new rook(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("blackKnight")) {
					knight B = new knight(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("blackKing")) {
					king B = new king(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("blackQueen")) {
					queen B = new queen(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				}

			}
		return true;
	}

	public boolean isStaleMated(String[][] piece) {

		if (!whiteInCheck(piece) && playerTurn == 1) {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					if (piece[i][j].startsWith("white")) {
						for (int x = 0; x < 8; x++)
							for (int y = 0; y < 8; y++) {
								if (i == x && j == y)
									continue;
								else if (piece[x][y].startsWith("white"))
									continue;
								else if (piece[i][j].equals("whitePawn")) {
									pawn p = new pawn(0, 0);
									if (p.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("whiteRook")) {
									rook r = new rook(0, 0);
									if (r.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("whiteQueen")) {
									queen q = new queen(0, 0);
									if (q.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("whiteBishop")) {
									bishop b = new bishop(0, 0);
									if (b.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("whiteKing")) {
									king k = new king(0, 0);
									if (k.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (whiteInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								}
							}
					}
				}
		}
		if (!blackInCheck(piece)) {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					if (piece[i][j].startsWith("black")) {
						for (int x = 0; x < 8; x++)
							for (int y = 0; y < 8; y++) {
								if (i == x && j == y)
									continue;
								else if (piece[x][y].startsWith("black"))
									continue;
								else if (piece[i][j].equals("blackPawn")) {
									pawn p = new pawn(0, 0);
									if (p.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("blackRook")) {
									rook r = new rook(0, 0);
									if (r.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("blackQueen")) {
									queen q = new queen(0, 0);
									if (q.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("blackBishop")) {
									bishop b = new bishop(0, 0);
									if (b.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								} else if (piece[i][j].equals("blackKing")) {
									king k = new king(0, 0);
									if (k.checkLegality(i, j, x, y, piece) == true) {
										String past = piece[i][j];
										String present = piece[x][y];
										piece[x][y] = piece[i][j];
										piece[i][j] = "b";
										if (blackInCheck(piece)) {
											piece[i][j] = past;
											piece[x][y] = present;
										} else {
											piece[i][j] = past;
											piece[x][y] = present;
											return false;
										}

									}
								}
							}
					}
				}
		}

		// return true;
		return true;
	}

	public int[] wKingCoOrdinates(String[][] temp1) {

		int[] x = new int[2];
		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++) {
				if (temp1[a][b].equals("whiteKing")) {
					x[0] = a;
					x[1] = b;
					return x;
				}
			}

		return x;

	}

	public boolean whiteInCheck(String[][] piece) {
		int[] b = wKingCoOrdinates(piece);
		if (isSafe_W(b[0], b[1], piece) == true)
			return false;
		return true;
	}

	public boolean isSafe_B(int X, int Y, String[][] piece) {
		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++) {
				if (piece[a][b].equals("whitePawn")) {
					pawn p = new pawn(0, 0);
					if (p.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("whiteBishop")) {
					bishop B = new bishop(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("whiteRook")) {
					rook B = new rook(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("whiteKnight")) {
					knight B = new knight(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("whiteKing")) {
					king B = new king(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				} else if (piece[a][b].equals("whiteQueen")) {
					queen B = new queen(0, 0);
					if (B.checkLegality(a, b, X, Y, piece) == true)
						return false;
				}

			}
		return true;
	}

	public int[] bKingCoOrdinates(String[][] temp) {

		int[] x = new int[2];
		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++) {
				if (temp[a][b].equals("blackKing")) {
					x[0] = a;
					x[1] = b;
					return x;
				}
			}

		return x;

	}

	public boolean blackInCheck(String[][] piece) {
		int[] b = bKingCoOrdinates(piece);
		if (isSafe_B(b[0], b[1], piece) == true)
			return false;
		return true;
	}



}
