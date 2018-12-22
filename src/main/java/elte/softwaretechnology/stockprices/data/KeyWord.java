package elte.softwaretechnology.stockprices.data;

public class KeyWord {
	private String keyWord;
	private String major;

	@Override
	public String toString() {
		return "KeyWord{" +
						"keyWord='" + keyWord + '\'' +
						'}';
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
}
