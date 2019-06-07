package application;

public class AssociationBlockContructor {
	int rowNo;
        String blockNumber;
	String associationName, contructorName, roundName, winnerContructorLevel;
public AssociationBlockContructor(int rowNo, String associationName, String blockNumber, String contructorName, String winnerContructorLevel, String roundName){
	this.rowNo=rowNo;
	this.associationName=associationName;
	this.blockNumber=blockNumber;
	this.contructorName=contructorName;
	this.roundName=roundName;
	this.winnerContructorLevel=winnerContructorLevel;
	System.out.print(contructorName+"-- "+winnerContructorLevel+"  ");
}

public int getRowNo() {
	return rowNo;
}
public void setRowNo(int rowNo) {
	this.rowNo = rowNo;
}
public String getBlockNumber() {
	return blockNumber;
}
public void setBlockNumber(String blockNumber) {
	this.blockNumber = blockNumber;
}
public String getAssociationName() {
	return associationName;
}
public void setAssociationName(String associationName) {
	this.associationName = associationName;
}
public String getContructorName() {
	return contructorName;
}
public void setContructorName(String contructorName) {
	this.contructorName = contructorName;
}

public String getWinnerContructorLevel() {
	return winnerContructorLevel;
}

public void setWinnerContructorLevel(String winnerContructorLevel) {
	this.winnerContructorLevel = winnerContructorLevel;
}

public String getRoundName() {
	return roundName;
}

public void setRoundName(String roundName) {
	this.roundName = roundName;
}


}
