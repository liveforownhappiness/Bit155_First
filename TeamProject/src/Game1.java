
public class Game1 extends Game{

	@Override
	public void scoreAutoSave() {
		// TODO Auto-generated method stub
		super.scoreAutoSave();
	}
	
	Game1(){
		this.score1 = UserManager.currentUser.getGame1Score();
	}

}
