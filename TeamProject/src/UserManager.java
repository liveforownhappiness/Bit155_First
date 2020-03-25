import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class UserManager implements Serializable{ // ȸ������, �α��� ���

	Scanner sc = new Scanner(System.in);
	Map<String ,UserDTO> userlist = new HashMap<String ,UserDTO>();
	Map<String, String> idMap = new HashMap<String, String>();
	static Map<String, Integer> scoreMap = new HashMap<String, Integer>();
	List<String> rankList = new ArrayList<String>();
	static UserDTO currentUser = null;
	
	
	String userListFileName = "UserList.txt";
	String idMapFileName = "IdMap.txt";
	File userListFile = new File(userListFileName);
	File idMapFile = new File(idMapFileName);

	
	
	
	public void signUp() {
		System.out.println("����");
		String id;
		while (true) {
			System.out.println("���̵� �Է�");
			id = sc.nextLine();
			if (!idMap.containsKey(id)) {
				break;
			} else {
				System.out.println("�ߺ��� ���̵� �Դϴ�.");
			}
		}
		System.out.println("�̸��� �Է�");
		String email = sc.nextLine();

		String pwd = null;
		String pwd2 = null;

		while (true) {
			System.out.println("��й�ȣ �Է�");
			pwd = sc.nextLine();
			System.out.println("��й�ȣ Ȯ��");
			pwd2 = sc.nextLine();

			if (pwd.equals(pwd2))
				break;

			System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}

		idMap.put(id, pwd);
		userlist.put(id, new UserDTO(id, pwd, email));

		
		this.saveUserList();
		this.saveIdMap();
		System.out.println(idMap.toString()); // �� Ȯ�ο�
		System.out.println(userlist.toString()); // ����Ʈ Ȯ�ο�

	}

	public void signIn() {
		System.out.println("�α���");

		while (true) {
			System.out.println("���̵� �Է��ϼ���");
			String id = sc.nextLine();
			System.out.println("��й�ȣ�� �Է��ϼ���");
			String pwd = sc.nextLine();

			if (idMap.containsKey(id)) {
				if (pwd.equals(idMap.get(id))) {
					System.out.println("�α��� ����");
					
					currentUser = this.userlist.get(id);// �α����� �����ϸ� �ش� ������ ������ �ҷ����� ���
					
////					�ӽ÷� scoreMap �� �߰�////////////
					System.out.println("cu totalscore : "+currentUser.getTotalScore());
					int random = ((int)(Math.random()*9)+1)*10;
					currentUser.setTotalScore(random);
					System.out.println(currentUser.getTotalScore());
					
					scoreMap.put(id, currentUser.getTotalScore());
					System.out.println("scoreMap : "+scoreMap.toString());
					

//					
////					this.userlist.get(4).setTotalScore(40);
////					this.userlist.get(5).setTotalScore(50);
////					this.userlist.get(6).setTotalScore(60);
////					this.userlist.get(7).setTotalScore(70);
//					
//					
////					///////////////////////////
					
					
					return; // �������� �Լ��� ���������� �ٸ� �Լ��� ȣ������(�޴��� �����ִ� �Լ�) �����غ���
					
				} else {
					System.out.println("�߸��� ��й�ȣ �Դϴ�.");
				}
			} else {
				System.out.println("�������� �ʴ� ���̵� �Դϴ�.");
			}
		}
	}
	
	public void loadUserList() {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(userListFileName);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis); // �� ������ �ڵ尡 ������ȭ

//			��ü�� � ������� �𸣴� ��Ȳ�� ��
			Object loadedUserList = null;
			while((loadedUserList = ois.readObject()) != null) { // �о���� ���� null�� �ƴ� ����
				userlist = (HashMap)loadedUserList;
			}
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
		}finally {
			try {
				ois.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void saveUserList() {
	
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(userListFileName);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(userlist);
			
			System.out.println("���ϻ��� -> buffer -> ����ȭ -> ���� write");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadIdMap() {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(idMapFileName);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			
//			��ü�� � ������� �𸣴� ��Ȳ�� ��
			Object loadedIdMap = null;
			while((loadedIdMap = ois.readObject()) != null) { 
				idMap = (HashMap)loadedIdMap;
			}
			
//			idMap = (HashMap)ois.readObject();
//			�̷��� �ϸ� ��� ������ ©���� �� ���� .. ����?
			
		} catch (Exception e) {
			
//			System.out.println(e.getMessage());
//			e.printStackTrace();
		}finally {
			try {
				ois.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void saveIdMap() {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(idMapFileName);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(idMap);
			
			System.out.println("���ϻ��� -> buffer -> ����ȭ -> ���� write");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void makeRank() {
//		Iterator<String> it = this.sortByValue(scoreMap).iterator();
//		while(it.hasNext()) {
//			String temp = (String)it.next();
//			System.out.println(temp + " = " + scoreMap.get(temp));
//			System.out.println("Rank Test");
//		}
		
	}
	
	public List<String> sortByValue(final Map<String, Integer> map){
		
		rankList.addAll(map.keySet());
		
		Collections.sort(rankList, new Comparator<Object>() {
		
			@Override
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
			return ((Comparable<Object>)v1).compareTo(v2);
				
			}
			
		});
		Collections.reverse(rankList); //��������
		return rankList;
	} // ���� ��� ����. Generic���� �ʿ�.
	
	
	
	
	public void run() {
		
		for(int i = 3; i > 0; i--) {
			
			if((this.userListFile.exists())&&(this.idMapFile.exists())) {
				this.loadIdMap(); //		idMap�о����
				this.loadUserList();//		userlist �о����
				}
			
				this.signUp(); // ���� �ϴ� ���
				this.signIn();// �α��� �ϴ� ���
				
//				System.out.println(currentUser.toString());
//				System.out.println(currentUser.getEmail());
		}
		
		sortByValue(scoreMap);
		System.out.println("toString : "+rankList.toString());
		System.out.println("key & vaule : "+rankList.get(0)+" & "+scoreMap.get(rankList.get(0)));
		
		

	}
	
	

	public static void main(String[] args) {
		UserManager um = new UserManager();
//		int i = 3;
//		while (i > 0) { // ��� Ȯ�ο� �ݺ�
//			if((um.userListFile.exists())&&(um.idMapFile.exists())) {
//			um.loadIdMap();
//			um.loadUserList();
//			}
//			
//			um.signUp(); // id�ߺ� Ȯ��, ��й�ȣ ��ġ Ȯ��, ���� ���� ���� �����
//
//			um.signIn(); //
			um.run();
//			System.out.println(um.userlist.get("id1234")); // 
//			i--;
//		}


	}

}


