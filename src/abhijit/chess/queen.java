package abhijit.chess;

public class queen {
	int x, y;

	public queen(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	
public  boolean checkLegality(int pastX,int pastY, int presentX,int presentY,String [][] pieces){
	
	
	if(pastX==presentX || pastY==presentY){
		rook r = new rook(0,0);
		return r.checkLegality(pastX, pastY, presentX, presentY, pieces);
	}
	else{
		bishop b = new bishop(0,0);
		return b.checkLegality(pastX, pastY, presentX, presentY, pieces);
	}
}
	
	
	
		
	

}
