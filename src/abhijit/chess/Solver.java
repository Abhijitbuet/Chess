package abhijit.chess;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Solver {
	public Solver(){
		
		
	}
	
	public  int[] moveDecider(Board1 b,int turn){
		
		int[] sol=new int[4];
		double ft=-2000;
		double ht=-2000;
		String [][] tpiece=new String[8][8];
		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				tpiece[i][j]=b.piece[i][j];
			}
		}
		Board1 temp=b;
	
		ArrayList<Board1> l = b.neighbors(tpiece,2);
		for(Board1 x:l){
			x.f=solve(x,2,-2000, 2000, false);
			if(x.f>ft){
				ft=x.f;
				temp=x;
			}
			/*else if(x.f==ft && x.h>ht){
				ft=x.f;
				temp=x;
			}*/
		
		}
	
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				
				if(!temp.piece[i][j].equals(tpiece[i][j]) && temp.piece[i][j].equals("b")){
					sol[0]=i;
					sol[1]=j;
				}
				if(!temp.piece[i][j].equals(tpiece[i][j]) && !temp.piece[i][j].equals("b")){
					sol[2]=i;
					sol[3]=j;
				}
				
				
			}
		}
		return sol;
	}
	
	
	public  static double solve(Board1 b, int depth, double maxVal, double minVal,
			boolean maximizingPlayer) {
		String [][] tpiece=new String[8][8];
		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				tpiece[i][j]=b.piece[i][j];
			}
		}
		if (depth == 0 ||  b.isBlackCheckmated(tpiece) || b.isWhiteCheckmated(tpiece) || b.isDrawn(tpiece))
			return b.h;
		if (maximizingPlayer == true) {
			b.f = -2000;
			ArrayList<Board1> l = b.neighbors(tpiece,2);
			for (Board1 x : l) {
				b.f = Math.max(b.f, solve(x, depth - 1, maxVal, minVal, false));
				maxVal = Math.max(maxVal, b.f);
				if (maxVal >= minVal)
					break;// (* ß cut-off *)
			}
			return b.f;

		} else {
			b.f = 2000;
			ArrayList<Board1> l = b.neighbors(tpiece,1);
			for (Board1 x : l) {
				b.f = Math.min(b.f, solve(x, depth - 1, maxVal, minVal, true));
				minVal = Math.min(minVal, b.f);
				if (maxVal<= minVal)
					break;// (* a cut-off *)
			}
			return b.f;

		}
	}
	
}
