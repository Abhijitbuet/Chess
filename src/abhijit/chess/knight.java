package abhijit.chess;

public class knight {
	int x,y;

	public knight(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	public  boolean checkLegality(int pastX,int pastY, int presentX,int presentY,String [][] pieces){
		if(Math.abs(presentX-pastX)==1 && Math.abs(presentY-pastY)==2 ||
				Math.abs(presentX-pastX)==2 && Math.abs(presentY-pastY)==1 )return true;
		return false;
	}
}
