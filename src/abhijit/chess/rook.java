package abhijit.chess;

public class rook {
	
	int x, y;

	public rook(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	public  boolean checkLegality(int pastX,int pastY, int presentX,int presentY,String [][] pieces){
		
	
		if(pastX!=presentX && pastY!=presentY)return false;
	
		else if(pastX==presentX && pastY<presentY  ){
			for(int i=pastY+1;i<presentY;i++){
				if(!pieces[pastX][i].equals("b"))return false;
			}
		}
		else if(pastX==presentX && pastY>presentY  ){
			for(int i=pastY-1;i>presentY;i--){
				if(!pieces[pastX][i].equals("b"))return false;
			}
		}
		else if(pastY==presentY && pastX<presentX  ){
			for(int i=pastX+1;i<presentX;i++){
				if(!pieces[i][pastY].equals("b"))return false;
			}
		}
		else if(pastY==presentY && pastX>presentX  ){
			for(int i=pastX-1;i>presentX;i--){
				if(!pieces[i][pastY].equals("b"))return false;
			}
		}
		return true;
	}
}
