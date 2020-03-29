
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Game3 extends Game {
	private static int SIZE = 10;
	public static int matrix[][] = new int[SIZE][SIZE];
	Scanner sc = new Scanner(System.in);
	boolean checkEmptyKey = false;
	boolean solCol;
	boolean solRow;
	boolean solBox;
	int[][] matrixForSolve;

	public int intro() {
		score1 = MGSystem.currentUser.getGame1Score();
		score2 = MGSystem.currentUser.getGame2Score();
		score3 = MGSystem.currentUser.getGame3Score();
		score4 = MGSystem.currentUser.getGame4Score();

		System.out.println("\t\t<��  ��  ��  ��  ��>");
		System.out.println("\t\t\t<���� ����>");
		System.out.println("\t[0]���� ���õǾ� �ִ� ���� ���ڷ� ä���ּ���!");
		System.out.println("\t�� �࿡�� 1���� 9������ ���� �ߺ� ���� �ԷµǾ� �մϴ�.");
		System.out.println("\t�� ������ 1���� 9������ ���� �ߺ� ���� �ԷµǾ� �մϴ�.");
		System.out.println("�� [3 x 3] �ڽ����� 1���� 9������ ���� �ߺ� ���� �ԷµǾ� �մϴ�.");
		System.out.println("�������������������������������������������������������������������");
		System.out.println("\t\t\t���� �Է½ÿ� ����� [�������]!!!!!!!!");
		System.out.println("\t�� ��� ������ �����Ǿ�� �����Դϴ�!");
		System.out.println("\t������ ������ ������ ���� �� �⺻ [50��]�̸�,");
		System.out.println("������ ���߽� ��� �⺻���� + play time�� ����� ������ �ջ�˴ϴ�.");
		System.out.println("\t���� 1���� ������ ���� ������ ���������!");
		System.out.println("�������������������������������������������������������������������");
		System.out.println("1. Play Game   0. �����޴��� ���ư���");
		int select = 0;
		do {
			try {
				select = Integer.parseInt(sc.nextLine());

				if (select == 0 || select == 1) {
					break;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("1 �Ǵ� 0�� �Է����ּ���");
				System.out.println(e.getMessage());
				e.printStackTrace();
				break;
			}
		} while (true);

		return select;

	}

	public void tgmain() {
		toGdp: while (true) {
			switch (this.intro()) {
			case 1:
				playGame();

				break;
			case 0:
				System.out.println("���� �޴��� ���ư��ϴ�.");
				break toGdp;
			}
		}
	}

	public void playGame() {
		for (int i = 1; i < SIZE; i++) {
			for (int j = 1; j < SIZE; j++) {
				matrix[i][j] = 0;
			}
		}
		fill(0);

	}

	public void fill(int cnt) {

		if (cnt == 81) {
			for (int i = 1; i <= 9; i++) {
				for (int j = 1; j <= 9; j++) {

				}

			}

			makeBlank(matrix);
			showMatrix(matrix); // Ȯ�ο�
			long starttime = System.currentTimeMillis();
			while (!checkEmptyKey) {
				userInput(matrix);
				System.out.println();
				System.out.println("=========================================");
				showMatrix(matrix);
				checkEmpty(matrix);

			} // while Ż��
			solveBox(matrix);
			solveCol(matrix);
			solveRow(matrix);
			System.out.println("��� �Է��ϼ̽��ϴ�.");
			System.out.println("ä���� �Ͻ÷��� �ƹ�Ű�� �����ּ���!");
			sc.nextLine();

			if ((solBox == false) || (solCol == false) || (solRow == false)) {
				System.out.println("���� ����! ��^��");
				System.out.println("�ٽ� �÷��� �Ͻðڽ��ϱ�? ");
				System.out.println("1. YES!   0. �ý��� ����");
				int sel = Integer.parseInt(sc.nextLine());

				switch (sel) {
				case 1:
					tgmain();

				case 0:
					System.exit(0);
				}

				// intro ���� �ϱ�
			} else {
				System.out.println("�����մϴ�! ��� ���߼̽��ϴ�!");
				long endtime = System.currentTimeMillis();
				float playTime = (endtime - starttime) / 1000f;
				newScore = 100 - ((int) ((playTime / 60f) * 10));
				scoreAutoSave(newScore);
				System.out.println("�� ��� �ð�: " + playTime);
				System.out.println("ȹ������: " + newScore + "!!");
				System.out.println();
				tgmain();

			}

		} // if end

		int y = 0;
		int x = 0;
		boolean Check = true;
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				if (matrix[i][j] == 0) {
					y = i;
					x = j;
					Check = false;
					break;
				}
			}
			if (!Check)
				break;
		}
		if (Check)
			return;

		for (int n = 1; n <= 9; n++) {
			Random r = new Random();
			int num = r.nextInt(9) + 1;
			if (isSafe(num, y, x)) {
				matrix[y][x] = num;
				fill(cnt + 1);
				matrix[y][x] = 0;
			}
		}
	} // fill end

	// ���� ���� ��� ��Ģ Ȯ��
	public static boolean isSafe(int n, int y, int x) {
		if (checkRow(y, n) && checkCol(x, n) && checkBox(y, x, n))
			return true;

		return false;
	}

	// ���� ���� �� ��Ģ Ȯ��
	public static boolean checkCol(int x, int n) {
		for (int i = 1; i <= 9; i++) {
			if (matrix[i][x] == n)
				return false;
		}
		return true;
	}

	// ���� ���� �� ��Ģ Ȯ��
	public static boolean checkRow(int y, int n) {
		for (int j = 1; j <= 9; j++) {
			if (matrix[y][j] == n)
				return false;
		}
		return true;
	}

	// ���� ���� �ڽ� ��Ģ Ȯ��
	public static boolean checkBox(int y, int x, int n) {
		int row = ((int) Math.ceil(y / 3.0) - 1) * 3 + 1;
		int col = ((int) Math.ceil(x / 3.0) - 1) * 3 + 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matrix[row + i][col + j] == n)
					return false;
			}
		}
		return true;
	}

	// ���� �����ֱ�
	public static void showMatrix(int[][] matrix) {
		System.out.println("�������������������������������������������������������������������");
		for (int o = 1; o < matrix.length; o++) {
			for (int p = 1; p < matrix[o].length; p++) {
				System.out.print("   " + matrix[o][p] + "   ");
			}
			System.out.println();
		}
	}

//	, ran1.nextInt(9) + 1, ran1.nextInt(9) + 1, ran1.nextInt(9) + 1
	// ��ĭ �����
	public static void makeBlank(int[][] matrix) {
		Random ran1 = new Random();
		for (int a = 1; a <= 9; a++) {
			int[] c = { ran1.nextInt(9) + 1 };
			for (int k = 0; k < c.length; k++) {
				matrix[a][c[k]] = 0;
			}
			for (int s = 1; s <= 9; s++) {

			}
		}

	}

	// ���� �Է°� �迭 ����
	public void userInput(int[][] matrix) {
		try {
			sc = new Scanner(System.in);
			String inputNumb;
			int col = 0;
			int row = 0;
			int sol = 0;

			System.out.println("�������������������������������������������������������������������");
			System.out.println("���ڸ� �Է��� �ּ���: (��: [��],[��]:[�Է°�])");
			inputNumb = sc.nextLine();
			String[] inputNumbArray = inputNumb.split(",|:");
			row = Integer.parseInt(inputNumbArray[0]);
			col = Integer.parseInt(inputNumbArray[1]);
			sol = Integer.parseInt(inputNumbArray[2]);
			matrix[row][col] = sol;
			System.out.printf("[%d]�� [%d]���� [%d]�� �Է��Ͽ����ϴ�.", row, col, sol);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// box ���� Ȯ��
	public void solveBox(int[][] matrix) {
//		int col;
//		for (int r = 1; r <= 9; r++) {
//			for (int t = 1; t <= 9; t++) {
//				row = ((int) Math.ceil(r / 3.0) - 1) * 3 + 1;
//				col = ((int) Math.ceil(t / 3.0) - 1) * 3 + 1;
////				row = (int) ((r + 2 / 3.0) - 1);
////				col = (int) ((t + 2 / 3.0) - 1);
//				Set<Integer> set = new HashSet<Integer>();
//				for (int i = 0; i < 3; i++) {
//					for (int j = 0; j < 3; j++) {
//						set.add(matrix[row + i][col + j]);
//
//					}
//				}
//				if (set.size() == 9) {
//					solBox = true;
//				} else {
//					solBox = false;
//				}
//			}
//
//		}

		Set<Integer> box1 = new HashSet<Integer>();
		Set<Integer> box2 = new HashSet<Integer>();
		Set<Integer> box3 = new HashSet<Integer>();
		Set<Integer> box4 = new HashSet<Integer>();
		Set<Integer> box5 = new HashSet<Integer>();
		Set<Integer> box6 = new HashSet<Integer>();
		Set<Integer> box7 = new HashSet<Integer>();
		Set<Integer> box8 = new HashSet<Integer>();
		Set<Integer> box9 = new HashSet<Integer>();

		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				box1.add(matrix[i][j]);
			}
		}

		for (int i = 1; i < 4; i++) {
			for (int j = 4; j < 7; j++) {
				box2.add(matrix[i][j]);
			}
		}

		for (int i = 1; i < 4; i++) {
			for (int j = 7; j < 10; j++) {
				box3.add(matrix[i][j]);
			}
		}

		for (int i = 4; i < 7; i++) {
			for (int j = 1; j < 4; j++) {
				box4.add(matrix[i][j]);
			}
		}

		for (int i = 4; i < 7; i++) {
			for (int j = 4; j < 7; j++) {
				box5.add(matrix[i][j]);
			}
		}

		for (int i = 4; i < 7; i++) {
			for (int j = 7; j < 10; j++) {
				box6.add(matrix[i][j]);
			}
		}

		for (int i = 7; i < 10; i++) {
			for (int j = 1; j < 4; j++) {
				box7.add(matrix[i][j]);
			}
		}

		for (int i = 7; i < 10; i++) {
			for (int j = 4; j < 7; j++) {
				box8.add(matrix[i][j]);
			}
		}

		for (int i = 7; i < 10; i++) {
			for (int j = 7; j < 10; j++) {
				box9.add(matrix[i][j]);
			}
		}

		if ((box1.size() != 9) || (box2.size() != 9) || (box3.size() != 9) || (box4.size() != 9) || (box5.size() != 9)
				|| (box6.size() != 9) || (box7.size() != 9) || (box8.size() != 9) || (box9.size() != 9)) {
			solBox = false;
		} else {
			solBox = true;
		}
	}

	// �� ���� Ȯ��
	public void solveCol(int[][] matrix) {
		Set<Integer> col1 = new HashSet<Integer>();
		Set<Integer> col2 = new HashSet<Integer>();
		Set<Integer> col3 = new HashSet<Integer>();
		Set<Integer> col4 = new HashSet<Integer>();
		Set<Integer> col5 = new HashSet<Integer>();
		Set<Integer> col6 = new HashSet<Integer>();
		Set<Integer> col7 = new HashSet<Integer>();
		Set<Integer> col8 = new HashSet<Integer>();
		Set<Integer> col9 = new HashSet<Integer>();
		for (int i = 1; i <= 9; i++) {
			col1.add(matrix[i][1]);
		}
		for (int i = 1; i <= 9; i++) {
			col2.add(matrix[i][2]);
		}
		for (int i = 1; i <= 9; i++) {
			col3.add(matrix[i][3]);
		}
		for (int i = 1; i <= 9; i++) {
			col4.add(matrix[i][4]);
		}
		for (int i = 1; i <= 9; i++) {
			col5.add(matrix[i][5]);
		}
		for (int i = 1; i <= 9; i++) {
			col6.add(matrix[i][6]);
		}
		for (int i = 1; i <= 9; i++) {
			col7.add(matrix[i][7]);
		}
		for (int i = 1; i <= 9; i++) {
			col8.add(matrix[i][8]);
		}
		for (int i = 1; i <= 9; i++) {
			col9.add(matrix[i][9]);
		}

		if ((col1.size() != 9) || (col2.size() != 9) || (col3.size() != 9) || (col4.size() != 9) || (col5.size() != 9)
				|| (col6.size() != 9) || (col7.size() != 9) || (col8.size() != 9) || (col9.size() != 9)) {
			solCol = false;
		} else {
			solCol = true;
		}

	}

	// �� ���� Ȯ��
	public void solveRow(int[][] matrix) {
		Set<Integer> row1 = new HashSet<Integer>();
		Set<Integer> row2 = new HashSet<Integer>();
		Set<Integer> row3 = new HashSet<Integer>();
		Set<Integer> row4 = new HashSet<Integer>();
		Set<Integer> row5 = new HashSet<Integer>();
		Set<Integer> row6 = new HashSet<Integer>();
		Set<Integer> row7 = new HashSet<Integer>();
		Set<Integer> row8 = new HashSet<Integer>();
		Set<Integer> row9 = new HashSet<Integer>();

		for (int i = 1; i <= 9; i++) {
			row1.add(matrix[1][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row2.add(matrix[2][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row3.add(matrix[3][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row4.add(matrix[4][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row5.add(matrix[5][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row6.add(matrix[6][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row7.add(matrix[7][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row8.add(matrix[8][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row9.add(matrix[9][i]);
		}

		if ((row1.size() != 9) || (row2.size() != 9) || (row3.size() != 9) || (row4.size() != 9) || (row5.size() != 9)
				|| (row6.size() != 9) || (row7.size() != 9) || (row8.size() != 9) || (row9.size() != 9)) {
			solRow = false;
		} else {
			solRow = true;
		}
	}

	// ��ĭ ���� Ȯ��
	public void checkEmpty(int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {

			for (int j = 1; j <= 9; j++) {

				list.add(matrix[i][j]);
			}
		}
		if (!list.contains(0)) {

			checkEmptyKey = true;

		}

	}

	public static void main(String[] args) {

		Game3 gm3 = new Game3();
		gm3.tgmain();
	}

	Game3() {
		newScore = 0;
	}

//
	@Override
	public void scoreAutoSave(int newScore) {
		if (newScore > score3) {
			score3 = newScore;
			MGSystem.currentUser.setGame1Score(score3); // Ŀ��Ʈ���� ���� ����
			System.out.println("before CU totalsc : " + MGSystem.currentUser.getTotalScore());
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
			System.out.println("After CU totalsc : " + MGSystem.currentUser.getTotalScore());

		}
	}

}
