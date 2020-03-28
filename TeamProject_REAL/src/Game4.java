import java.util.Random;
import java.util.Scanner;
public class Game4 extends Game{
	int x;// 랜덤으로 생성할 숫자
	int y;
	int z;// 사람이 적을 답
	int answer;// 계산된 답
	int i;// 문제 반복
//##
	long start;//게임 시작 시간
	long end;//게임 종료 시간
	long result;//종료 - 시작
	Scanner scan;
	Random random;
	Game4() {
		score4 = 0;	
		newScore = 0; //#
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
		System.out.println("                         구구단 게임");
		System.out.println("**************************************************");
		System.out.println();
		System.out.println("		답을 빨리 맞출 수록 더 많은 점수를 획득할 수 있습니다 ");
		System.out.println();
		System.out.println("**************************************************");
		System.out.println("		1. 게임 실행하기  	 0. 이전 메뉴로 돌아가기");
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
				System.out.println("1 또는 0만 입력해주세요");
			}		
		}while(true);
		return menu;
	}
	public void guguMain() {
		toGdp : while(true) {
			switch(this.intro()) {
			case 1 : makeSubGame();
				break;
			case 0 : System.out.println("이전메뉴로 돌아갑니다.");
				break toGdp;
			}
		}
	}
	void makeSubGame() {
            start = System.currentTimeMillis();
		while (i < 5) {			
			i++;
			x = random.nextInt(9) + 1;
			y = random.nextInt(9) + 1;
			answer = x * y;
			System.out.println(x + "X" + y + "=?");
			z = Integer.parseInt(scan.nextLine());
			if (z == answer) {
				System.out.println("정답입니다!");
				newScore += 15;
			} else {
				System.out.println("오답입니다!");
			}
		}
		end = System.currentTimeMillis();
		float playTime = (end - start)/1000f;
//		System.out.println("원래 점수 newScore : "  + newScore); #삭제가능
		int bonus = (int)playTime; // 시간가산점
		if(playTime < 6) {
			bonus = 30;
		}
		newScore += newScore*15/bonus;
		if(newScore > 100) {
			newScore = 100;
		}
		if (newScore == 100) {
			System.out.println("만점입니다");			
		} else if(newScore < 100 ){
			System.out.println("최종점수는 100점 만점 중" + newScore + " 점입니다." + "\n아쉽게 만점을 받지 못했습니다\n" + "분발하세요!");
		}
		scoreAutoSave(newScore);
	}
	@Override
	public void scoreAutoSave(int newScore) {
		if(newScore > score4) {
			score4 = newScore;
			MGSystem.currentUser.setGame4Score(score4); // 커런트유저 점수 세팅
			System.out.println("before CU totalsc : "+MGSystem.currentUser.getTotalScore());//확인용
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
			System.out.println("After CU totalsc : "+MGSystem.currentUser.getTotalScore());//확인용
		}
	}
}