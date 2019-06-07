package application;

public class Association {
	int rowNo, registrationNumber, numberOfMembers, numberOfRegisteredMembers;
	String associationName, roundName;
	String nameOfChairman, phoneNumber;
	public Association(int rowNo, String associationName, int registrationNumber, int numberOfMembers, int numberOfRegisteredMembers, String roundName){
		this.rowNo=rowNo;
		this.associationName=associationName;
		this.registrationNumber=registrationNumber;
		this.numberOfMembers=numberOfMembers;
		this.numberOfRegisteredMembers=numberOfRegisteredMembers;
		this.roundName=roundName;
	}
	public Association(int rowNo, String associationName, int registrationNumber, String nameOfChairman, String phoneNumber, String roundName){
		this.rowNo=rowNo;
		this.associationName=associationName;
		this.registrationNumber=registrationNumber;
		this.nameOfChairman=nameOfChairman;
		this.phoneNumber=phoneNumber;
		this.roundName=roundName;
	}
	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public int getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public int getNumberOfMembers() {
		return numberOfMembers;
	}
	public void setNumberOfMembers(int numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}
	public String getAssociationName() {
		return associationName;
	}
	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}
	public String getRoundName() {
		return roundName;
	}
	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	public int getNumberOfRegisteredMembers() {
		return numberOfRegisteredMembers;
	}

	public void setNumberOfRegisteredMembers(int numberOfRegisteredMembers) {
		this.numberOfRegisteredMembers = numberOfRegisteredMembers;
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
