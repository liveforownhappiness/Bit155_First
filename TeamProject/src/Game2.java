
public class Game2 extends Game{

	@Override
	public void scoreAutoSave() {
		// TODO Auto-generated method stub
		super.scoreAutoSave();
	}
	
	Game2(){
		this.score2 = UserManager.currentUser.getGame2Score();
	}


}
