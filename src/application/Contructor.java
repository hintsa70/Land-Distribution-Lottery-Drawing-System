package application;

public class Contructor {
	private String contructorName, contructorPhone, contructorAddress, contructorRank, contructorRoundName;
	int rowNo;
	public Contructor(int rowNo, String contructorName,  String  contructorPhone,  String  contructorAddress,  String  contructorRank, String contructorRoundName){
		this.rowNo=rowNo;
		this.contructorName=contructorName;
		this.contructorPhone=contructorPhone;
		this.contructorAddress=contructorAddress;
		this.contructorRank=contructorRank;
		this.contructorRoundName=contructorRoundName;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getContructorName() {
		return contructorName;
	}

	public void setContructorName(String contructorName) {
		this.contructorName = contructorName;
	}

	public String getContructorPhone() {
		return contructorPhone;
	}

	public void setContructorPhone(String contructorPhone) {
		this.contructorPhone = contructorPhone;
	}

	public String getContructorAddress() {
		return contructorAddress;
	}

	public void setContructorAddress(String contructorAddress) {
		this.contructorAddress = contructorAddress;
	}

	public String getContructorRank() {
		return contructorRank;
	}

	public void setContructorRank(String contructorRank) {
		this.contructorRank = contructorRank;
	}

	public String getContructorRoundName() {
		return contructorRoundName;
	}

	public void setContructorRoundName(String contructorRoundName) {
		this.contructorRoundName = contructorRoundName;
	}


	
}
