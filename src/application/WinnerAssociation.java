package application;

public class WinnerAssociation {
	
private int rowNo;
private String unionName;
private String blockNumber;
private String registrationRound;
private String nameOfChairman;
private String phoneNumber;

public WinnerAssociation(Integer rowNum, String unionName, String blockNumber, int roundId){
	this.rowNo=rowNum;
	this.unionName=unionName;
	this.blockNumber=blockNumber;
        this.nameOfChairman= new DatabaseManager().getNameOfChairman(unionName, roundId);
        this.phoneNumber=new DatabaseManager().getPhoneNumber(unionName, roundId);;
}


public WinnerAssociation(Integer rowNum, String unionName, String blockNumber){
	this.rowNo=rowNum;
	this.unionName=unionName;
	this.blockNumber=blockNumber;
}
public String getRegistrationRound() {
	return registrationRound;
}


public void setRegistrationRound(String registrationRound) {
	this.registrationRound = registrationRound;
}


public int getRowNo() {
	return rowNo;
}


public void setRowNo(int rowNo) {
	this.rowNo = rowNo;
}


public String getUnionName() {
	return unionName;
}


public void setUnionName(String unionName) {
	this.unionName = unionName;
}


public String getBlockNumber() {
	return blockNumber;
}


public void setBlockNumber(String blockNumber) {
	this.blockNumber = blockNumber;
}

public void setNameOfChairman(String nameOfChairman) {
		this.nameOfChairman = nameOfChairman;
	}
	public String getNameOfChairman() {
		return nameOfChairman;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
}
