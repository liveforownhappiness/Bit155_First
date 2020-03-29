import java.util.Random;
import java.util.Scanner;
public class Game4 extends Game{
	int x;// �������� ������ ����
	int y;
	int z;
	String input;// ����� ���� ��
	int answer;// ���� ��

//##
	long start;//���� ���� �ð�
	long end;//���� ���� �ð�
	long result;//���� - ����
	Scanner scan;
	Random random;
	Game4() {
		input = "";
		newScore = 0; 
		scan = new Scanner(System.in);
		random = new Random();	
	}
	public int intro() {
		score1 = MGSystem.currentUser.getGame1Score();
		score2 = MGSystem.currentUser.getGame2Score();
		score3 = MGSystem.currentUser.getGame3Score();
		score4 = MGSystem.currentUser.getGame4Score();
		System.out.println();
		System.out.println("**************************************************");
		System.out.println("                     ������ ����");
		System.out.println("**************************************************");
		System.out.println();
		System.out.println("	���� ���� ���� ���� �� ���� ������ ȹ���� �� �ֽ��ϴ� ");
		System.out.println();
		System.out.println("**************************************************");
		System.out.println("	1. ���� �����ϱ�  	 0. ���� �޴��� ���ư���");
		System.out.println("**************************************************");
		int menu = 0;
		do {
			try {
				menu = Integer.parseInt(scan.nextLine());
				if(menu == 0 || menu == 1) {
					break;
				}else {
					throw new Exception();
				}
			}catch(Exception e) {
				System.out.println("1 �Ǵ� 0�� �Է����ּ���");
			}		
		}while(true);
		return menu;
	}
	
	public void guguMain() {

		toGdp : while(true) {

			switch(this.intro()) {
			case 1 : this.makeSubGame();
				break;
			case 0 : 
				break toGdp;
			}
		}
	}

	
	void makeSubGame() {
		int i = 0;
        start = System.currentTimeMillis();

		while (i < 5) {	
			i++;
			x = random.nextInt(9) + 1;
			y = random.nextInt(9) + 1;
			answer = x * y;
			System.out.println(x + "X" + y + "=?");

    		
    		while(true) {
    			try {
    				input = scan.nextLine();
    				if(isNumeric(input)) {
    					z = Integer.parseInt(input);
    					break;
    				}else {
    					throw new Exception("���ڸ� �Է��ϼ���");
  
    				}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
    		}

    		if (z == answer) {
				System.out.println("�����Դϴ�!");
				newScore += 15;
			} else {
				System.out.println("�����Դϴ�!");
			}
		}
		end = System.currentTimeMillis();
		float playTime = (end - start)/1000f;
		int bonus = (int)playTime; // �ð���������
		if(playTime < 6) {
			bonus = 30;
		}
		newScore += newScore*12/bonus;
		if(newScore > 100) {
			newScore = 100;
		}
		if (newScore == 100) {
			System.out.println("���������� 100�� ���� �� �����Դϴ�");			
		} else if(newScore < 100 ){
			System.out.println("���������� 100�� ���� ��" + newScore + " ���Դϴ�." + "\n�ƽ��� ������ ���� ���߽��ϴ�\n" + "�й��ϼ���!");
		}
		
		scoreAutoSave(newScore);
	}
	
	@Override
	public void scoreAutoSave(int newScore) {
		if(newScore > score4) {
			score4 = newScore;
			MGSystem.currentUser.setGame4Score(score4); // Ŀ��Ʈ���� ���� ����
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
		}
	}
	
	public boolean isNumeric(String input) { 
		try { Double.parseDouble(input); 
				return true; 
			} catch (NumberFormatException e) { 
				return false; 
			} 
		}

}