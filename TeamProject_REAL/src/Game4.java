import java.util.Random;
import java.util.Scanner;

public class Game4 extends Game{
	int x;// �������� ������ ����
	int y;
	int z;// ����� ���� ��
	int answer;// ���� ��
	int i;// ���� �ݺ�
//	int score4;// ����
	long start;//���� ���� �ð�
	long end;//���� ���� �ð�
	long result;//���� - ����
	
	Scanner scan = new Scanner(System.in);
	Random random = new Random();	
	
	

	Game4() {
		score4 = 0;	
	}
	
	void makeSubGame() {
		System.out.println("**************************************");
		System.out.println("������ ������ �����մϴ�!!");
		System.out.println("**************************************");
            start = System.currentTimeMillis();
          
		while (i < 5) {			
			i++;
			x = random.nextInt(9) + 1;
			y = random.nextInt(9) + 1;
			answer = x * y;
			System.out.println(x + "X" + y + "=?");
			z = scan.nextInt();
			if (z == answer) {
				System.out.println("�����Դϴ�!");
				newScore += 10;
			} else {
				System.out.println("�����Դϴ�!");
			}

		}
		end = System.currentTimeMillis();
		float playTime = (end - start)/1000f;
		System.out.println("���� ���� newScore : "  + newScore);
		int bonus = (int)playTime;
		if(playTime < 6) {
			bonus = 30;
		}
		
		newScore += newScore*15/bonus;
		
		if(newScore > 100) {
			System.out.println("�ִ� ������ 100�Դϴ�");
			newScore = 100;
		}
		
		if (newScore == 100) {
			System.out.println("�����Դϴ�");			
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
			System.out.println("before CU totalsc : "+MGSystem.currentUser.getTotalScore());//Ȯ�ο�
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
			System.out.println("After CU totalsc : "+MGSystem.currentUser.getTotalScore());//Ȯ�ο�

		}
	}
	


}
