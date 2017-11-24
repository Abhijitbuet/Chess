package abhijit.chess;



public class king {
	int x,y;

	public king(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	public  boolean checkLegality(int pastX,int pastY, int presentX,int presentY,String [][] pieces){
		boolean status=false;
		if(Math.abs(presentX-pastX)<2 && Math.abs(presentY-pastY)<2 )status= true;
		//else if(Math.abs(presentX-pastX)==2 && b==true){}
		
		return status;
	}

}