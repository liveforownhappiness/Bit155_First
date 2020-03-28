import java.util.Random;
import java.util.Scanner;
public class Game4 extends Game{
	int x;// �������� ������ ����
	int y;
	int z;// ����� ���� ��
	int answer;// ���� ��

//##
	long start;//���� ���� �ð�
	long end;//���� ���� �ð�
	long result;//���� - ����
	Scanner scan;
	Random random;
	Game4() {
//		score4 = 0;	
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
		System.out.println("                         ������ ����");
		System.out.println("**************************************************");
		System.out.println();
		System.out.println("		���� ���� ���� ���� �� ���� ������ ȹ���� �� �ֽ��ϴ� ");
		System.out.println();
		System.out.println("**************************************************");
		System.out.println("		1. ���� �����ϱ�  	 0. ���� �޴��� ���ư���");
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
		System.out.println("gugumain before while ");

		toGdp : while(true) {
			System.out.println("gugumain while start");

			switch(this.intro()) {
			case 1 : System.out.println("gugumain switch case1 before makeSubGame");
				this.makeSubGame();
				System.out.println("gugumain switch case1 after makeSubGame");
				break;
			case 0 : System.out.println("�����޴��� ���ư��ϴ�.");
				break toGdp;
			}
			System.out.println("gugumain switch end");

		}
	System.out.println("gugumain while end");

	}

	
	void makeSubGame() {
		int i = 0;// ���� �ݺ� // i�� ���ú����� �����ؾ� �޼ҵ尡 ����� ������ �����������?
		System.out.println("makeSubGame start");

            start = System.currentTimeMillis();
    		System.out.println("makeSubGame before while");

		while (i < 5) {	// $$ i�� �ν��Ͻ� ������ ������ ������ �ѹ��ۿ� ������ �ȵ˴ϴ� ^^
    		System.out.println("makeSubGame while start");

			i++;
			x = random.nextInt(9) + 1;
			y = random.nextInt(9) + 1;
			answer = x * y;
			System.out.println(x + "X" + y + "=?");
			
    		System.out.println("makeSubGame while before scanner");

			z = Integer.parseInt(scan.nextLine());
//			z = scan.nextInt();
    		System.out.println("makeSubGame while after scanner");

    		System.out.println("makeSubGame while before if");
			if (z == answer) {
				System.out.println("�����Դϴ�!");
				newScore += 15;
			} else {
				System.out.println("�����Դϴ�!");
			}
    		System.out.println("makeSubGame while after if");

		}
		end = System.currentTimeMillis();
		float playTime = (end - start)/1000f;
//		System.out.println("���� ���� newScore : "  + newScore); #��������
		int bonus = (int)playTime; // �ð�������
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
		
		System.out.println("makeSubGame while before autoSave");
		scoreAutoSave(newScore);
		System.out.println("makeSubGame while after autoSave");

	}
	@Override
	public void scoreAutoSave(int newScore) {
		if(newScore > score4) {
			score4 = newScore;
			MGSystem.currentUser.setGame4Score(score4); // Ŀ��Ʈ���� ���� ����
			System.out.println("before CU totalsc : "+MGSystem.currentUser.getTotalScore());//Ȯ�ο�
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
			System.out.println("After CU totalsc : "+MGSystem.currentUser.getTotalScore());//Ȯ�ο�
		}
	}
}