
public class Functions {
	Utils utils;
	Functions(){
		utils = new Utils();
	}
	
	public void showSignMenu() {
		System.out.println("showSignMene ����");
		System.out.println("1.�α��� / 2.���� / 3.��ŷ���� / 0. ���α׷� ����");
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
		System.out.println("signIn ����");
		
		System.out.println("id�� �Է��ϼ���");
		String id = this.utils.sc.nextLine();
		
		System.out.println("pwd�� �Է��ϼ���");
		String pwd = this.utils.sc.nextLine();
		
		System.out.println("�Է°� ����");
		
		if(true) {
			this.showMenu();
		}else {
			System.out.println("�α��� ����");
		}
	}
	
	
	public void signUp() {
		System.out.println("signUp ����");
		
		System.out.println("id�� �Է��ϼ���");
		String id = this.utils.sc.nextLine();
		
		System.out.println("pwd�� �Է��ϼ���");
		String pwd = this.utils.sc.nextLine();
		
		System.out.println("�̸��ϸ� �Է��ϼ���");
		String email = this.utils.sc.nextLine();
		
		System.out.println("�Է°� ����");
		
		System.out.println("User����");
		User user = new User("id", "pwd", "email");
		//Ȯ���� ���Ա���� system�� �־�߰ڴ�.
//		user�� add�ϱ����ؼ� 
		
		
	}
	
	public void signOut() {
		System.out.println("signOut");
	}
	

	public void showMenu() {
		System.out.println("showMenu ���������� ����");
		System.out.println("�޴�����");
		System.out.println("�Է�");
		int menu = 1;
		System.out.println("�Է°� ����");
		
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
		System.out.println("showGameMenu ����");
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
		System.out.println("showRank ����");
		
	}
	
	public void showUserInfo() {
		System.out.println("showUserInfo ����");
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
	
	
	public static void main(String[] args) { //test��
		// TODO Auto-generated method stub

	}

}
