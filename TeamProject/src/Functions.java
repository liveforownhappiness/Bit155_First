
public class Functions {
	Utils utils;
	Functions(){
		utils = new Utils();
	}
	
	public void showSignMenu() {
		System.out.println("showSignMene 실행");
		System.out.println("1.로그인 / 2.가입 / 3.랭킹보기 / 0. 프로그램 종료");
		int menu = Integer.parseInt(this.utils.sc.nextLine());
		
		switch(menu) {
		case 1 : this.signIn();
		break;
		case 2 : this.signUp();
		break;
		case 3 : this.showRank();
		break;
		case 0 : return;
		}
	}

	public void signIn() {
		System.out.println("signIn 실행");
		
		System.out.println("id를 입력하세요");
		String id = this.utils.sc.nextLine();
		
		System.out.println("pwd를 입력하세요");
		String pwd = this.utils.sc.nextLine();
		
		System.out.println("입력값 검증");
		
		if(true) {
			this.showMenu();
		}else {
			System.out.println("로그인 실패");
		}
	}
	
	
	public void signUp() {
		System.out.println("signUp 실행");
		
		System.out.println("id를 입력하세요");
		String id = this.utils.sc.nextLine();
		
		System.out.println("pwd를 입력하세요");
		String pwd = this.utils.sc.nextLine();
		
		System.out.println("이메일를 입력하세요");
		String email = this.utils.sc.nextLine();
		
		System.out.println("입력값 검증");
		
		System.out.println("User생성");
		User user = new User("id", "pwd", "email");
		//확실히 가입기능은 system에 있어야겠다.
//		user를 add하기위해서 
		
		
	}
	
	public void signOut() {
		System.out.println("signOut");
	}
	

	public void showMenu() {
		System.out.println("showMenu 메인페이지 실행");
		System.out.println("메뉴보기");
		System.out.println("입력");
		int menu = 1;
		System.out.println("입력값 검증");
		
		switch(menu) {
		case 1: this.showGameMenu();
		break;
		case 2: this.showRank();
		break;
		case 3: this.showUserInfo();
		break;
		case 4: this.signOut();
		break;
			
		}
	}
	
	
	public void showGameMenu() {
		System.out.println("showGameMenu 실행");
		int menu = 1;
		switch(menu) {
		case 1 : this.playGame1();
		break;
		case 2 : this.playGame2();
		break;
		case 3 : this.playGame3();
		break;
		case 0 : return;
			
		}
	}
	
	public void showRank() {
		System.out.println("showRank 실행");
		
	}
	
	public void showUserInfo() {
		System.out.println("showUserInfo 실행");
	}
	
	public void deleteUserInfo() {
		System.out.println("deleteUserInfo");
	}
	
	public void playGame1() {
		System.out.println("playGame1");
		this.autoSaveScore();
	}
	
	public void playGame2() {
		System.out.println("playGame2");
	}
	
	public void playGame3() {
		System.out.println("playGame3");
	}
	
	public void autoSaveScore(){
		System.out.println("saveTopScore");
	}
	
	
	public static void main(String[] args) { //test용
		// TODO Auto-generated method stub

	}

}
