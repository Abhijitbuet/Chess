package abhijit.chess;

import java.util.LinkedList;
import java.util.Vector;

import abhijit.chess.R.drawable;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class DoublePlayer extends Activity {
	int intent = 0;
	boolean blackShort = false;
	boolean blackLong = false;
	boolean whiteShort = false;
	boolean whiteLong = false;
	int takenCounter1 = 1;
	int takenCounter2 = 1;
	int i;
	int j;
	boolean[] castleFlag = new boolean[2];
	int highlightedX = -1, highlightedY = -1;
	ImageView[][] image = new ImageView[8][8];
	boolean specialMovePerformed = false;
	String[][] base = new String[8][8];
	String[][] piece = new String[8][8];
	boolean[][] isAttacked = new boolean[8][8];
	boolean[] check = new boolean[2];
	int playerTurn = 1;
	int pressedX, pressedY, prevX, prevY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doubletest);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.double_player, menu);
		initialize();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void initialize() {

		castleFlag[0] = true;
		castleFlag[1] = true;
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {

				if (i == 0) {

					switch (j) {
					case 0:
						image[i][j] = (ImageView) findViewById(R.id.a8);

						base[i][j] = "white";
						piece[i][j] = "blackRook";

						break;

					case 1:
						image[i][j] = (ImageView) findViewById(R.id.b8);

						base[i][j] = "black";
						piece[i][j] = "blackKnight";
						break;

					case 2:
						image[i][j] = (ImageView) findViewById(R.id.c8);

						base[i][j] = "white";
						piece[i][j] = "blackBishop";
						break;
					case 3:
						image[i][j] = (ImageView) findViewById(R.id.d8);

						base[i][j] = "black";
						piece[i][j] = "blackQueen";
						break;

					case 4:
						image[i][j] = (ImageView) findViewById(R.id.e8);

						base[i][j] = "white";
						piece[i][j] = "blackKing";
						break;
					case 5:
						image[i][j] = (ImageView) findViewById(R.id.f8);

						base[i][j] = "black";
						piece[i][j] = "blackBishop";
						break;
					case 6:
						image[i][j] = (ImageView) findViewById(R.id.g8);

						base[i][j] = "white";
						piece[i][j] = "blackKnight";
						break;
					case 7:
						image[i][j] = (ImageView) findViewById(R.id.h8);

						base[i][j] = "black";
						piece[i][j] = "blackRook";
						break;

					}

				}

				else if (i == 1) {
					if (j == 0) {
						image[i][j] = (ImageView) findViewById(R.id.a7);

						base[i][j] = "black";
						piece[i][j] = "blackPawn";

					} else if (j == 2) {
						image[i][j] = (ImageView) findViewById(R.id.c7);

						base[i][j] = "black";
						piece[i][j] = "blackPawn";

					} else if (j == 4) {
						image[i][j] = (ImageView) findViewById(R.id.e7);

						base[i][j] = "black";
						piece[i][j] = "blackPawn";

					} else if (j == 6) {
						image[i][j] = (ImageView) findViewById(R.id.g7);

						base[i][j] = "black";
						piece[i][j] = "blackPawn";

					} else if (j == 1) {
						image[i][j] = (ImageView) findViewById(R.id.b7);

						base[i][j] = "white";
						piece[i][j] = "blackPawn";

					} else if (j == 3) {
						image[i][j] = (ImageView) findViewById(R.id.d7);

						base[i][j] = "white";
						piece[i][j] = "blackPawn";

					} else if (j == 5) {
						image[i][j] = (ImageView) findViewById(R.id.f7);

						base[i][j] = "white";
						piece[i][j] = "blackPawn";

					} else {
						image[i][j] = (ImageView) findViewById(R.id.h7);

						base[i][j] = "white";
						piece[i][j] = "blackPawn";

					}

				}

				else if (i == 2 || i == 4) {

					if (j == 0) {
						if (i == 2)
							image[i][j] = (ImageView) findViewById(R.id.a6);
						else
							image[i][j] = (ImageView) findViewById(R.id.a4);
						base[i][j] = "white";
						piece[i][j] = "b";
					} else if (j == 2) {
						if (i == 2)
							image[i][j] = (ImageView) findViewById(R.id.c6);
						else
							image[i][j] = (ImageView) findViewById(R.id.c4);
						base[i][j] = "white";
						piece[i][j] = "b";
					} else if (j == 4) {
						if (i == 2)
							image[i][j] = (ImageView) findViewById(R.id.e6);
						else
							image[i][j] = (ImageView) findViewById(R.id.e4);
						base[i][j] = "white";
						piece[i][j] = "b";
					} else if (j == 6) {
						if (i == 2)
							image[i][j] = (ImageView) findViewById(R.id.g6);
						else
							image[i][j] = (ImageView) findViewById(R.id.g4);
						base[i][j] = "white";
						piece[i][j] = "b";
					} else if (j == 1) {
						if (i == 2)
							image[i][j] = (ImageView) findViewById(R.id.b6);
						else
							image[i][j] = (ImageView) findViewById(R.id.b4);
						base[i][j] = "black";
						piece[i][j] = "b";
					} else if (j == 3) {
						if (i == 2)
							image[i][j] = (ImageView) findViewById(R.id.d6);
						else
							image[i][j] = (ImageView) findViewById(R.id.d4);
						base[i][j] = "black";
						piece[i][j] = "b";
					} else if (j == 5) {
						if (i == 2)
							image[i][j] = (ImageView) findViewById(R.id.f6);
						else
							image[i][j] = (ImageView) findViewById(R.id.f4);
						base[i][j] = "black";
						piece[i][j] = "b";
					} else {
						if (i == 2)
							image[i][j] = (ImageView) findViewById(R.id.h6);
						else
							image[i][j] = (ImageView) findViewById(R.id.h4);
						base[i][j] = "black";
						piece[i][j] = "b";
					}

				} else if (i == 3 || i == 5) {

					if (j == 1) {
						if (i == 3)
							image[i][j] = (ImageView) findViewById(R.id.b5);
						else
							image[i][j] = (ImageView) findViewById(R.id.b3);
						base[i][j] = "white";
						piece[i][j] = "b";
					} else if (j == 3) {
						if (i == 3)
							image[i][j] = (ImageView) findViewById(R.id.d5);
						else
							image[i][j] = (ImageView) findViewById(R.id.d3);
						base[i][j] = "white";
						piece[i][j] = "b";
					} else if (j == 5) {
						if (i == 3)
							image[i][j] = (ImageView) findViewById(R.id.f5);
						else
							image[i][j] = (ImageView) findViewById(R.id.f3);
						base[i][j] = "white";
						piece[i][j] = "b";
					} else if (j == 7) {
						if (i == 3)
							image[i][j] = (ImageView) findViewById(R.id.h5);
						else
							image[i][j] = (ImageView) findViewById(R.id.h3);
						base[i][j] = "white";
						piece[i][j] = "b";
					} else if (j == 0) {
						if (i == 3)
							image[i][j] = (ImageView) findViewById(R.id.a5);
						else
							image[i][j] = (ImageView) findViewById(R.id.a3);
						base[i][j] = "black";
						piece[i][j] = "b";
					} else if (j == 2) {
						if (i == 3)
							image[i][j] = (ImageView) findViewById(R.id.c5);
						else
							image[i][j] = (ImageView) findViewById(R.id.c3);
						base[i][j] = "black";
						piece[i][j] = "b";
					} else if (j == 4) {
						if (i == 3)
							image[i][j] = (ImageView) findViewById(R.id.e5);
						else
							image[i][j] = (ImageView) findViewById(R.id.e3);
						base[i][j] = "black";
						piece[i][j] = "b";
					} else {
						if (i == 3)
							image[i][j] = (ImageView) findViewById(R.id.g5);
						else
							image[i][j] = (ImageView) findViewById(R.id.g3);
						base[i][j] = "black";
						piece[i][j] = "b";
					}

				}

				else if (i == 6) {
					if (j == 0) {
						image[i][j] = (ImageView) findViewById(R.id.a2);

						base[i][j] = "white";
						piece[i][j] = "whitePawn";

					} else if (j == 2) {
						image[i][j] = (ImageView) findViewById(R.id.c2);

						base[i][j] = "white";
						piece[i][j] = "whitePawn";

					} else if (j == 4) {
						image[i][j] = (ImageView) findViewById(R.id.e2);

						base[i][j] = "white";
						piece[i][j] = "whitePawn";

					} else if (j == 6) {
						image[i][j] = (ImageView) findViewById(R.id.g2);

						base[i][j] = "white";
						piece[i][j] = "whitePawn";

					} else if (j == 1) {
						image[i][j] = (ImageView) findViewById(R.id.b2);

						base[i][j] = "black";
						piece[i][j] = "whitePawn";

					} else if (j == 3) {
						image[i][j] = (ImageView) findViewById(R.id.d2);

						base[i][j] = "black";
						piece[i][j] = "whitePawn";

					} else if (j == 5) {
						image[i][j] = (ImageView) findViewById(R.id.f2);

						base[i][j] = "black";
						piece[i][j] = "whitePawn";

					} else {
						image[i][j] = (ImageView) findViewById(R.id.h2);

						base[i][j] = "black";
						piece[i][j] = "whitePawn";

					}

				} else {
					switch (j) {
					case 0:
						image[i][j] = (ImageView) findViewById(R.id.a1);
						piece[i][j] = "whiteRook";
						base[i][j] = "black";
						break;
					case 1:
						image[i][j] = (ImageView) findViewById(R.id.b1);
						piece[i][j] = "whiteKnight";
						base[i][j] = "white";
						break;
					case 2:
						image[i][j] = (ImageView) findViewById(R.id.c1);
						piece[i][j] = "whiteBishop";
						base[i][j] = "black";
						break;
					case 3:
						image[i][j] = (ImageView) findViewById(R.id.d1);
						piece[i][j] = "whiteQueen";
						base[i][j] = "white";
						break;
					case 4:
						image[i][j] = (ImageView) findViewById(R.id.e1);
						piece[i][j] = "whiteKing";
						base[i][j] = "black";
						break;
					case 5:
						image[i][j] = (ImageView) findViewById(R.id.f1);
						piece[i][j] = "whiteBishop";
						base[i][j] = "white";
						break;
					case 6:
						image[i][j] = (ImageView) findViewById(R.id.g1);
						piece[i][j] = "whiteKnight";
						base[i][j] = "black";

						break;
					case 7:
						image[i][j] = (ImageView) findViewById(R.id.h1);
						piece[i][j] = "whiteRook";
						base[i][j] = "white";
						break;

					}

				}

				image[i][j].setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent arg1) {
						int x = 0, y = 0;
						int id = v.getId();
						//System.out.println(id);
						outerloop: for (x = 0; x < 8; x++)
							for (y = 0; y < 8; y++) {
								if (image[x][y].getId() == id) {
									pressedX = x;
									pressedY = y;
									break outerloop;
								}
							}

						switch (arg1.getAction()) {
						case MotionEvent.ACTION_DOWN:
							if (highlightedX != -1)
								clear();
							image[pressedX][pressedY].setBackgroundColor(Color
									.parseColor("#00ff00"));

							highlightedX = pressedX;
							highlightedY = pressedY;
							break;
						case MotionEvent.ACTION_UP:

							break;
						case MotionEvent.ACTION_MOVE:

							break;

						default:
							break;

						}

						if (intent == 0) {
							intent++;

							prevX = pressedX;
							prevY = pressedY;

						} else if (prevX != -1 && pressedX != -1) {
							LinkedList<int[]> l = possibleMoves(piece,
									playerTurn);
							int count = 0;
							while (!l.isEmpty()) {

								int[] temp = l.remove();
							
								count++;
							}
							boolean state;

							state = isLegal(prevX, prevY, pressedX, pressedY,
									piece);
							if (isWhiteCheckmated(piece)) {
								Intent intent = new Intent(DoublePlayer.this,
										WhiteCheckMated.class);
								startActivity(intent);
								return false;
							} else if (isBlackCheckmated(piece)) {
								Intent intent = new Intent(DoublePlayer.this,
										BlackCheckMated.class);
								startActivity(intent);
								return false;
							} else if (!whiteInCheck(piece)
									&& !blackInCheck(piece) && isDrawn(piece)) {
								Intent intent = new Intent(DoublePlayer.this,
										Drawn.class);
								startActivity(intent);
								return false;

							} else if (specialMovePerformed) {
								specialMovePerformed = false;
								if (blackShort)
									blackShortCastle();
								else if (blackLong)
									blackLongCastle();
								else if (whiteShort)
									whiteShortCastle();
								else if (whiteLong)
									whiteLongCastle();
								blackShort = blackLong = whiteLong = whiteShort = false;
							}

							else if (state == true) {

								move(prevX, prevY, pressedX, pressedY);
								piece[pressedX][pressedY] = piece[prevX][prevY];
								piece[prevX][prevY] = "b";

								turn();

							}
							pressedX = pressedY = prevX = prevY = -1;
							intent = 0;

						}

						return false;
					}

					private void clear() {
						if (base[highlightedX][highlightedY].equals("white"))
							image[highlightedX][highlightedY]
									.setBackgroundColor(Color
											.parseColor("#ccffff"));
						else
							image[highlightedX][highlightedY]
									.setBackgroundColor(Color
											.parseColor("#b22222"));
					}

				});

			}
		}
	}

	public LinkedList<int[]> possibleMoves(String[][] piece, int turn) {

		boolean old = castleFlag[0];
		boolean old1 = castleFlag[1];
		LinkedList<int[]> moves = new LinkedList<int[]>();
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
		castleFlag[0] = old;
		castleFlag[1] = old1;
		return moves;
	}

	public String[][] modifyPiece(String[][] piece, int pastX, int pastY,
			int presentX, int presentY) {
		String[][] modified = piece;
		modified[presentX][presentY] = piece[pastX][pastY];
		modified[pastX][pastY] = "b";

		return modified;

	}

	public void turn() {
		if (playerTurn == 1)
			playerTurn = 2;
		else
			playerTurn = 1;
	}

	public void move(int pastX, int pastY, int presentX, int presentY) {
		if (piece[presentX][presentY].startsWith("black")) {
			switch (takenCounter1) {
			case 1:
				((ImageView) findViewById(R.id.blank1))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());

				break;
			case 2:
				((ImageView) findViewById(R.id.blank2))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 3:
				((ImageView) findViewById(R.id.blank3))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 4:
				((ImageView) findViewById(R.id.blank4))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 5:
				((ImageView) findViewById(R.id.blank5))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 6:
				((ImageView) findViewById(R.id.blank6))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 7:
				((ImageView) findViewById(R.id.blank7))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 8:
				((ImageView) findViewById(R.id.blank8))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;

			default:
				((ImageView) findViewById(R.id.blank8))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			}
			takenCounter1++;

		} else if (piece[presentX][presentY].startsWith("white")) {
			switch (takenCounter2) {
			case 1:
				((ImageView) findViewById(R.id.blank9))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());

				break;
			case 2:
				((ImageView) findViewById(R.id.blank10))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 3:
				((ImageView) findViewById(R.id.blank11))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 4:
				((ImageView) findViewById(R.id.blank12))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 5:
				((ImageView) findViewById(R.id.blank13))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 6:
				((ImageView) findViewById(R.id.blank14))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 7:
				((ImageView) findViewById(R.id.blank15))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			case 8:
				((ImageView) findViewById(R.id.blank16))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;

			default:
				((ImageView) findViewById(R.id.blank16))
						.setImageDrawable(image[presentX][presentY]
								.getDrawable());
				break;
			}
			takenCounter2++;

		}
		image[presentX][presentY].setImageDrawable(image[pastX][pastY]
				.getDrawable());
		image[pastX][pastY].setImageResource(R.drawable.blank);

	}

	public void whiteLongCastle() {
		image[7][3].setImageDrawable(image[7][0].getDrawable());
		piece[7][3] = "whiteRook";

		image[7][2].setImageDrawable(image[7][4].getDrawable());
		piece[7][2] = "whiteKing";
		image[7][4].setImageDrawable(null);
		piece[7][4] = "b";
		image[7][0].setImageDrawable(null);
		piece[7][0] = "b";
		turn();

	}

	public void whiteShortCastle() {
		image[7][5].setImageDrawable(image[7][7].getDrawable());
		piece[7][5] = "whiteRook";

		image[7][6].setImageDrawable(image[7][4].getDrawable());
		piece[7][6] = "whiteKing";
		image[7][7].setImageDrawable(null);
		piece[7][7] = "b";
		image[7][4].setImageDrawable(null);
		piece[7][4] = "b";
		turn();
	}

	public void blackShortCastle() {
		image[0][5].setImageDrawable(image[0][7].getDrawable());
		piece[0][5] = "blackRook";

		image[0][6].setImageDrawable(image[0][4].getDrawable());
		piece[0][6] = "blackKing";
		image[0][4].setImageDrawable(null);
		piece[0][4] = "b";
		image[0][7].setImageDrawable(null);
		piece[0][7] = "b";
		turn();

	}

	public void blackLongCastle() {

		image[0][3].setImageDrawable(image[0][0].getDrawable());
		piece[0][3] = "blackRook";

		image[0][2].setImageDrawable(image[0][4].getDrawable());
		piece[0][2] = "blackKing";
		image[0][4].setImageDrawable(null);
		piece[0][4] = "b";
		image[0][0].setImageDrawable(null);
		piece[0][0] = "b";
		turn();

	}

	public void pawnPromote() {
		if (piece[prevX][prevY].startsWith("black")) {
			piece[pressedX][pressedY] = "blackQueen";
			piece[prevX][prevY] = "b";

			image[pressedX][pressedY]
					.setImageResource(R.drawable.chess_piece_black_queen);
			playerTurn = 1;

			image[prevX][prevY].setImageDrawable(null);

		} else {
			piece[pressedX][pressedY] = "whiteQueen";
			piece[prevX][prevY] = "b";

			image[pressedX][pressedY]
					.setImageResource(R.drawable.chess_piece_white_queen);
			playerTurn = 2;

			image[prevX][prevY].setImageDrawable(null);

		}

	}

	public boolean isLegal(int pastX, int pastY, int presentX, int presentY,
			String[][] piece) {
		specialMovePerformed = false;
		blackShort = blackLong = whiteLong = whiteShort = false;
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

			whiteShort = true;
			castleFlag[0] = false;
			specialMovePerformed = true;
			return true;
		} else if (presentY - pastY == -2
				&& piece[pastX][pastY].endsWith("eKing") && castleFlag[0]
				&& isSafe_W(7, 4, piece) && isSafe_W(7, 3, piece)
				&& isSafe_W(7, 2, piece) && piece[7][1].equals("b")) {
			whiteLong = true;

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

			blackShort = true;
			specialMovePerformed = true;
			castleFlag[1] = false;
			return true;

		} else if (presentY - pastY == -2
				&& piece[pastX][pastY].endsWith("kKing") && castleFlag[1]
				&& isSafe_B(0, 4, piece) && isSafe_B(0, 3, piece)
				&& isSafe_B(0, 2, piece) && piece[0][1].equals("b")) {
			// blackLongCastle();
			blackLong = true;
			specialMovePerformed = true;
			castleFlag[1] = false;
			return true;

		} else if (piece[pastX][pastY].endsWith("Pawn")
				&& (presentX == 0 || presentX == 7)) {
			pawn p = new pawn(0, 0);
			if (p.checkLegality(pastX, pastY, presentX, presentY, piece)) {
				pawnPromote();
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

	public boolean blackInCheck(String[][] piece) {
		int[] b = bKingCoOrdinates(piece);
		if (isSafe_B(b[0], b[1], piece) == true)
			return false;
		return true;
	}

	public boolean whiteInCheck(String[][] piece) {
		int[] b = wKingCoOrdinates(piece);
		if (isSafe_W(b[0], b[1], piece) == true)
			return false;
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

}
