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


public class UserManager implements Serializable{ // 회원가입, 로그인 기능

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
		System.out.println("가입");
		String id;
		while (true) {
			System.out.println("아이디 입력");
			id = sc.nextLine();
			if (!idMap.containsKey(id)) {
				break;
			} else {
				System.out.println("중복된 아이디 입니다.");
			}
		}
		System.out.println("이메일 입력");
		String email = sc.nextLine();

		String pwd = null;
		String pwd2 = null;

		while (true) {
			System.out.println("비밀번호 입력");
			pwd = sc.nextLine();
			System.out.println("비밀번호 확인");
			pwd2 = sc.nextLine();

			if (pwd.equals(pwd2))
				break;

			System.out.println("비밀번호가 일치하지 않습니다.");
		}

		idMap.put(id, pwd);
		userlist.put(id, new UserDTO(id, pwd, email));

		
		this.saveUserList();
		this.saveIdMap();
		System.out.println(idMap.toString()); // 맵 확인용
		System.out.println(userlist.toString()); // 리스트 확인용

	}

	public void signIn() {
		System.out.println("로그인");

		while (true) {
			System.out.println("아이디를 입력하세요");
			String id = sc.nextLine();
			System.out.println("비밀번호를 입력하세요");
			String pwd = sc.nextLine();

			if (idMap.containsKey(id)) {
				if (pwd.equals(idMap.get(id))) {
					System.out.println("로그인 성공");
					
					currentUser = this.userlist.get(id);// 로그인이 성공하면 해당 유저의 정보를 불러오는 기능
					
////					임시로 scoreMap 값 추가////////////
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
					
					
					return; // 리턴으로 함수를 빠져나갈지 다른 함수를 호출할지(메뉴를 보여주는 함수) 생각해보기
					
				} else {
					System.out.println("잘못된 비밀번호 입니다.");
				}
			} else {
				System.out.println("존재하지 않는 아이디 입니다.");
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
			ois = new ObjectInputStream(bis); // 이 한줄의 코드가 역직렬화

//			객체가 몇개 들었는지 모르는 상황일 때
			Object loadedUserList = null;
			while((loadedUserList = ois.readObject()) != null) { // 읽어오는 값이 null이 아닌 동안
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
			
			System.out.println("파일생성 -> buffer -> 직렬화 -> 파일 write");
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
			
//			객체가 몇개 들었는지 모르는 상황일 때
			Object loadedIdMap = null;
			while((loadedIdMap = ois.readObject()) != null) { 
				idMap = (HashMap)loadedIdMap;
			}
			
//			idMap = (HashMap)ois.readObject();
//			이렇게 하면 출력 내용이 짤리는 것 같다 .. 왜지?
			
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
			
			System.out.println("파일생성 -> buffer -> 직렬화 -> 파일 write");
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
		Collections.reverse(rankList); //오름차순
		return rankList;
	} // 아직 사용 안함. Generic수정 필요.
	
	
	
	
	public void run() {
		
		for(int i = 3; i > 0; i--) {
			
			if((this.userListFile.exists())&&(this.idMapFile.exists())) {
				this.loadIdMap(); //		idMap읽어오기
				this.loadUserList();//		userlist 읽어오기
				}
			
				this.signUp(); // 가입 하는 경우
				this.signIn();// 로그인 하는 경우
				
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
//		while (i > 0) { // 기능 확인용 반복
//			if((um.userListFile.exists())&&(um.idMapFile.exists())) {
//			um.loadIdMap();
//			um.loadUserList();
//			}
//			
//			um.signUp(); // id중복 확인, 비밀번호 일치 확인, 내용 파일 저장 저장됨
//
//			um.signIn(); //
			um.run();
//			System.out.println(um.userlist.get("id1234")); // 
//			i--;
//		}


	}

}


