import java.io.Serializable;

public class UserDTO implements Serializable{
	private String id;
	private String pwd;
	private String email;
	private int game1Score, game2Score, game3Score, totalScore;
	private int rank;

	UserDTO(String id, String pwd, String email) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		game1Score = 0;
		game2Score = 0;
		game3Score = 0;
		totalScore = game1Score + game2Score + game3Score;
		rank = 0;
	}

	public String getId() {
		return id;
	}
	// public void setId(String id) { // ���̵�� �������� ���� ���̱� ������ �ʿ� ����
	// this.id = id;
	// }

	public String getPwd() { // ��й�ȣ ã�⿡ ���̷���?
		return pwd;
	}
	// public void setPwd(String pwd) { // ���̵�� ����
	// this.pwd = pwd;
	// }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {// �����ּ� ���濡 ���� �� ����
		this.email = email;
	}

	public int getGame1Score() {
		return game1Score;
	}

	public void setGame1Score(int game1Score) {
		this.game1Score = game1Score;
	}

	public int getGame2Score() {
		return game2Score;
	}

	public void setGame2Score(int game2Score) {
		this.game2Score = game2Score;
	}

	public int getGame3Score() {
		return game3Score;
	}

	public void setGame3Score(int game3Score) {
		this.game3Score = game3Score;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pwd=" + pwd + ", email=" + email + ", game1Score=" + game1Score
				+ ", game2Score=" + game2Score + ", game3Score=" + game3Score + ", totalScore=" + totalScore + ", rank="
				+ rank + "]";
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
