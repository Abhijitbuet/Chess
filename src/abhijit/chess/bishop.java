package abhijit.chess;
public class bishop {
	int x, y;

	public bishop(int x, int y){
		super();
		this.x = x;
		this.y = y;
	}



public  boolean checkLegality(int pastX,int pastY, int presentX,int presentY,String [][] pieces){
	
	
	if(Math.abs(pastX-presentX)!=Math.abs(pastY-presentY))return false;
	
	else if(pastX<presentX && pastY<presentY  ){
		for(int i=pastX+1,j=pastY+1;i<presentX;i++,j++){
		//	System.out.println(pieces[i][j]);
			if(pieces[i][j].equals("b")==false)return false;
		}
	}
	else if(pastX<presentX && pastY>presentY  ){
		for(int i=pastX+1,j=pastY-1;i<presentX;i++,j--){
			
			if(!pieces[i][j].equals("b"))return false;
		}
	}
	else if(pastX>presentX && pastY>presentY  ){
		for(int i=pastX-1,j=pastY-1;i>presentX;i--,j--){
			
			if(!pieces[i][j].equals("b"))return false;
		}
	}
	else if(pastX>presentX && pastY<presentY  ){
		for(int i=pastX-1,j=pastY+1;j<presentY;i--,j++){
			
			if(!pieces[i][j].equals("b"))return false;
		}
	}
	return true;
}
	
		
}
