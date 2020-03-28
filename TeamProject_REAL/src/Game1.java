import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Game1 extends Game{
	int rAnswer;
	int bonus;
	Scanner scanner;
	String[][][] quizArr;
	Map<String, String> quizMap;
	
	Game1(){
		
		rAnswer = 0;
		scanner = new Scanner(System.in);
		quizArr = new String[3][2][];
		quizMap = new HashMap<String, String>();
		newScore = 0;

	}
	
	public int intro() {
		score1 = MGSystem.currentUser.getGame1Score();
		score2 = MGSystem.currentUser.getGame2Score();
		score3 = MGSystem.currentUser.getGame3Score();
		score4 = MGSystem.currentUser.getGame4Score();
		
		System.out.println();
		System.out.println("**************************************************");
		System.out.println("		��Ÿ ���߱� ����");
		System.out.println("**************************************************");
		System.out.println("		< ���� ���� >");
		System.out.println("	��Ÿ�� ������ �ѱ� �ܾ �󸶳� ���� �˾ƺ� �� �ֳ���?");
		System.out.println("	��Ÿ�� ������ ������ ���� �ѱ۷� ���� �Է��ϼ���");
		System.out.println("	���� ���� ���� ����, �� �� �ܾ ���� ���� ");
		System.out.println("	�� ���� ������ ȹ���� �� �ֽ��ϴ� ");
		System.out.println("	10���� �ȿ� �� ���� ������ ȹ���غ����� !");
		System.out.println("**************************************************");
		System.out.println("	1. ���� �����ϱ�  	 0. ���� �޴��� ���ư���");
		System.out.println("**************************************************");
		
		int menu = 0;
		do {
			try {
				menu = Integer.parseInt(scanner.nextLine());
				
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
	
	public void tgMain() {
		toGdp : while(true) {
			switch(this.intro()) {
			case 1 : playGame1();
				break;
			case 0 : System.out.println("�����޴��� ���ư��ϴ�.");
				break toGdp; 
			}
		}
	}

	public void playGame1() {
		//�� ����&���� �����͵� ���Ϸ� �����ؼ� ����� �� ���� ������?
		quizArr[0][0] = new String[]{"qkek", "thrma", "rkawk", "skrxk", "zpckq", "zjvl", "qhfvps", "rydbr", "dhdl", "tnqns"};
		quizArr[0][1] = new String[]{"�ٴ�", "�ұ�", "����", "��Ÿ", "����", "Ŀ��", "����", "����", "����", "����"};
		
		quizArr[1][0] = new String[] {"djtjdch", "vudckdtn", "xjaqmffj", "roskfl", "clawjsanf", "chzhclq", "dbfanck", "thswjsemd", "tjsdlswkd", "ahtjfl"};
		quizArr[1][1] = new String[] {"���", "��â��", "�Һ�", "������", "ħ����", "����Ĩ", "������", "������", "������", "�𼭸�"};
		
		quizArr[2][0] = new String[] {"wpwhdlfwk", "wjsxhdtlwkd", "vmfhvhffltm", "dhwhscjfl", "wlrtkrhkdtjs", "qlxmzoavm", "tmakxmvhs", "qhemakzk", "dlstnqnsgo", "skqkrrlacl"};
		quizArr[2][1] = new String[] {"��������", "�������", "����������", "����ó��", "���籤��", "��Ʈķ��", "����Ʈ��", "���帶ī", "�μ�����", "���ڱ�ġ"};

		
		
		for(int i = 0; i < quizArr[0][0].length; i++) {
			quizMap.put(quizArr[0][0][i], quizArr[0][1][i]);
			quizMap.put(quizArr[1][0][i], quizArr[1][1][i]);	
			quizMap.put(quizArr[2][0][i], quizArr[2][1][i]);
		}
		
		//keyList new �ڸ� �̰� ������ ���� ����
		List<String> keyList = new ArrayList<String>(quizMap.keySet());
		
		int randomIndex = 0;
		Set<Integer> indexCheck = new HashSet<Integer>();

		long starttime = System.currentTimeMillis();
		
		for(int i = 0; i < 10; i++) {
				
			while(true) {
				randomIndex = (int)(Math.random()*(quizArr[0][0].length*3));	
				if(indexCheck.add(randomIndex) == true) {
					break;
				}
			}
			

			
			System.out.println();
			System.out.println("====================================");
			System.out.printf("                      %s\n",keyList.get(randomIndex));
			System.out.println("====================================");
			System.out.println();
			
			String input = scanner.nextLine();
			
			
			
			if(input.equals(quizMap.get(keyList.get(randomIndex)))) {
				
				System.out.println("���� ~");
				newScore += 5;
				rAnswer++;
				
				for(int j = 0; j < quizArr[0][0].length; j++) { //��ܾ� ������ �ֱ�
					if(quizArr[1][0][j] == keyList.get(randomIndex)) {
						newScore += 1;
					}else if(quizArr[2][0][j] == keyList.get(randomIndex)){
						newScore += 2;
					}
				}
				
			}else {
				System.out.println(" �� ! ");
			}
			
		}//for�� ��
		
		long endTime = System.currentTimeMillis();
		float playTime = (endTime - starttime)/1000f;
		System.out.println("���� ���� newScore : "+newScore);
		int bonus = (int)playTime;
		if(playTime < 6) {
			bonus = 40;
		}
		
		
		newScore += newScore*15/(int)playTime;
		
		

		System.out.printf("�ҿ�ð� : %.2f��\t ���� ���� �� : %d\n", playTime, rAnswer);
//		System.out.println(newScore + "�� ! ");
		
		
		//�α������� ���� ���డ���� ����
		System.out.printf("[%s]���� ������ [%d]�� �Դϴ�!\n", MGSystem.currentUser.getId(), newScore);
		
//		�ڵ����� �� �κ�
		scoreAutoSave(newScore);
	}

	@Override
	public void scoreAutoSave(int newScore) {
		if(newScore > score1) {
			score1 = newScore;
			MGSystem.currentUser.setGame1Score(score1); // Ŀ��Ʈ���� ���� ����
			System.out.println("before CU totalsc : "+MGSystem.currentUser.getTotalScore());
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
			System.out.println("After CU totalsc : "+MGSystem.currentUser.getTotalScore());

		}
	}
	
	


}

