package application;

public class WinnerContructor {
int rowNo;
String contructorName;
int blockNumber;
String level;

public WinnerContructor(Integer rowNum, String unionName, Integer blockNumber, String level){
	this.rowNo=rowNum;
	this.contructorName=unionName;
	this.blockNumber=blockNumber;
	this.level=level;
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

public int getBlockNumber() {
	return blockNumber;
}

public void setBlockNumber(int blockNumber) {
	this.blockNumber = blockNumber;
}

public String getLevel() {
	return level;
}

public void setLevel(String level) {
	this.level = level;
}


}
