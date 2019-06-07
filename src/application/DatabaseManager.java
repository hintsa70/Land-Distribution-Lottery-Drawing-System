package application;

import java.sql.DriverManager;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;

public class DatabaseManager {
	private DriverManager driverManager;
	public Connection connection=null;
	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet resultSet;
	public static boolean CONNECTED=false;
	public String databaseName="HouseLottery";
	
	public DatabaseManager(){
		javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 14)));
	       javax.swing.UIManager.put("OptionPane.messageFont", new Font("Nyala", Font.PLAIN, 14));
	       javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Nyala", Font.PLAIN, 20));
	     
		if(!CONNECTED){
			this.createDatabase(databaseName);
			CONNECTED=true;
			}
			try {
				connection=driverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+this.databaseName+"?characterEncoding=UTF-8", "root", "");
				statement=connection.createStatement();
			} catch (SQLException e) {
				CONNECTED=false;
				}
	}
	public boolean connectedToDatabase(String databaseName){
		this.databaseName=databaseName;
		if(!this.databaseExists(databaseName))
				this.createDatabase(databaseName);
		try {
			connection=driverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+this.databaseName+"?characterEncoding=UTF-8", "root", "");
			statement=connection.createStatement();
			System.out.println("connected to database "+databaseName);
			CONNECTED=true;
			return true;
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return false;
	}

	
	///*
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//ASSOCIATIONS TABLE
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void createAssociationsTable(){

		try {
			String sql="CREATE TABLE Associations("
					+ "id int(255) primary key auto_increment, "
					+ "roundId int(255) not null, "
					+ "foreign key(roundId) references Rounds(id), "
					+ "associationName varchar(255) not null, "
					+ "registrationNumber int(255) not null, "
					+ "numberOfMembers int(255) not null "
					+ ")";
			statement.execute(sql);
			System.out.println("Associations table created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized void insertAssociations(int roundId, String associationName, int registrationNumber, int numberOfMembers){
		try {
			 preparedStatement=connection.prepareStatement("INSERT INTO Associations(roundId, associationName, registrationNumber, numberOfMembers) VALUES(?, ?, ?, ?)");
			 preparedStatement.setInt(1, roundId);
			 preparedStatement.setString(2, associationName);
			 preparedStatement.setInt(3, registrationNumber);
			 preparedStatement.setInt(4, numberOfMembers);
			 preparedStatement.executeUpdate();//flushs(commits) to database

		System.out.println("1 row inserted successsfully");
		} catch (SQLException e) {
			 e.printStackTrace();
			return;
		}
	}

	public synchronized void insertAssociations(int roundId, String associationName, String registrationNumber, String nameOfChairman, String phoneNumber){
		try {
			 preparedStatement=connection.prepareStatement("INSERT INTO Associations(roundId, associationName, registrationNumber, nameOfChairman, phoneNumber) VALUES(?, ?, ?, ?, ?)");
			 preparedStatement.setInt(1, roundId);
			 preparedStatement.setString(2, associationName);
			 preparedStatement.setString(3, registrationNumber);
			 preparedStatement.setString(4, nameOfChairman);
			 preparedStatement.setString(5, phoneNumber);
			 preparedStatement.executeUpdate();//flushs(commits) to database

		System.out.println("1 row inserted successsfully");
		} catch (SQLException e) {
			 e.printStackTrace();
			return;
		}
	}

	//SETTERS Associatives TABLE	
	public synchronized void setAssociationRegistrationRound(int roundId, int associationId){
		try {
			preparedStatement=connection.prepareStatement("update Associations set roundId = (?) where id=(?)");
			preparedStatement.setInt(1, roundId);
			preparedStatement.setInt(2, associationId);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	public synchronized void setAssociationName(String associationName, int associationId){
		try {
			preparedStatement=connection.prepareStatement("update Associations set associationName = (?) where id=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setInt(2, associationId);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	
	public synchronized void setAssociationRegistrationNumber(int registrationNumber, int associationId){
		try {
			preparedStatement=connection.prepareStatement("update Associations set registrationNumber = (?) where id=(?)");
			preparedStatement.setInt(1, registrationNumber);
			preparedStatement.setInt(2, associationId);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	
	public synchronized void setAssociationNumberOfMembers(int numberOfMembers, int associationId){
		try {
			preparedStatement=connection.prepareStatement("update Associations set numberOfMembers = (?) where id=(?)");
			preparedStatement.setInt(1, numberOfMembers);
			preparedStatement.setInt(2, associationId);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}

		//GETTERS Associatios TABLE Round
			public ArrayList<String> getAllAssociationNames(){
				if(!this.tableExists("Associations")){
					////JOptionPane.showMessageDialog(null, "Associations table does not Exist");
					return null;
				}
				
				ArrayList<String> associationNames=new ArrayList<>();
				try {
					preparedStatement=connection.prepareStatement("select * from Associations");
					resultSet=preparedStatement.executeQuery();
					while(resultSet.next()){
						associationNames.add(resultSet.getString("associationName"));
					}
				} catch (SQLException e) {
					 e.printStackTrace();
				}
		return associationNames;
			}

			//GETTERS Associatios TABLE Round(Int) 
			public ArrayList<Integer> getAllAssociationIds(){
				if(!this.tableExists("Associations")){
					////JOptionPane.showMessageDialog(null, "Associations table does not Exist");
					return null;
				}
					
					ArrayList<Integer> associationIds=new ArrayList<>();
					try {
						preparedStatement=connection.prepareStatement("select * from Associations");
							resultSet=preparedStatement.executeQuery();
							while(resultSet.next()){
								associationIds.add(resultSet.getInt("id"));
							}
						} catch (SQLException e) {
							 e.printStackTrace();
						}
				return associationIds;
					}

	public ArrayList<Integer> getAllHouseWinerAssociationIds(){
				if(!this.tableExists("housewinnerassociations")){
					//JOptionPane.showMessageDialog(null, "Associations table does not Exist");
					return null;
				}
					
					ArrayList<Integer> associationIds=new ArrayList<>();
					try {
						preparedStatement=connection.prepareStatement("select * from housewinnerassociations");
							resultSet=preparedStatement.executeQuery();
							while(resultSet.next()){
								associationIds.add(resultSet.getInt("associationId"));
							}
						} catch (SQLException e) {
							 e.printStackTrace();
						}
				return associationIds;
					}

			//GETTERS Associatios TABLE Round(Int) 
			public ArrayList<String> getAllAssociationNames(int roundId){
					if(!this.tableExists("Associations")){
						////JOptionPane.showMessageDialog(null, "Associations table does not Exist");
						return null;
					}
						
						ArrayList<String> associationNames=new ArrayList<>();
						try {
							preparedStatement=connection.prepareStatement("select * from Associations where roundId=(?)");
								preparedStatement.setInt(1, roundId);
								resultSet=preparedStatement.executeQuery();
								while(resultSet.next()){
									associationNames.add(resultSet.getString("associationName"));
								}
							} catch (SQLException e) {
								 e.printStackTrace();
							}
					return associationNames;
						}
				

public ArrayList<Integer> getAllRegistrationNumbers(){
		if(!this.tableExists("Associations")){
			////JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return null;
		}
		
		ArrayList<Integer> registrationNumbers=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement("select * from Associations");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				registrationNumbers.add(resultSet.getInt("registrationNumber"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return registrationNumbers;
	}

	public ArrayList<Integer> getAllRegistrationNumbers(int roundId){
		if(!this.tableExists("Associations")){
			////JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return null;
		}
		
		ArrayList<Integer> registrationNumbers=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement("select * from Associations where roundId=(?)");
			preparedStatement.setInt(1, roundId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				registrationNumbers.add(resultSet.getInt("registrationNumber"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return registrationNumbers;
	}
	
	public synchronized String getAssociationRegistrationRound(int associationId){
		if(!this.tableExists("Associations")){
		//	//JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return null;
		}
		
		if(!this.tableExists("Rounds")){
			////JOptionPane.showMessageDialog(null, "Rounds table does not Exist");
			return null;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select * from Associations, Rounds where Associations.id=(?) && Associations.roundId=Rounds.id");
			preparedStatement.setInt(1, associationId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("roundName");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}
	
	public synchronized String getNameOfChairman(String associationName, int roundId){
		if(!this.tableExists("Associations")){
			//JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return "";
		}
		
		try {
			preparedStatement=connection.prepareStatement("select * from Associations where associationName=(?)");
			preparedStatement.setString(1, "Round_"+roundId+"___"+associationName);
			preparedStatement.setInt(2, roundId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("nameOfChairman");
			}else{
				return "";
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return "";
		}
}
	
	public synchronized String getPhoneNumber(String associationName, int roundId){
		if(!this.tableExists("Associations")){
			//JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return "";
		}
		
		try {
			preparedStatement=connection.prepareStatement("select * from Associations where associationName=(?) && roundId=(?)");
			preparedStatement.setString(1, "Round_"+roundId+"___"+associationName);
			preparedStatement.setInt(2, roundId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("phoneNumber");
			}else{
				return "";
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return "";
		}
}

	public synchronized int getAssociationId(String associationName, int roundId){
		if(!this.tableExists("Associations")){
			//JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return 0;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select id from Associations where associationName=(?) && roundId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setInt(2, roundId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("id");
			}else{
				return 0;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return 0;
		}
}

	public synchronized String getAssociationName(int associationId){
		if(!this.tableExists("Associations")){
			//JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return null;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select associationName from Associations where id=(?)");
			preparedStatement.setInt(1, associationId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("associationName");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}

	public synchronized String getContructorName(int contructorId){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select contructorName from Contructors where id=(?)");
			preparedStatement.setInt(1, contructorId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("contructorName");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}
	
	public synchronized int getAssociationRegistrationNumber(int associationId){
		if(!this.tableExists("Associations")){
			//JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return 0;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select registrationNumber from Associations where id=(?)");
			preparedStatement.setInt(1, associationId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("registrationNumber");
			}else{
				return 0;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return 0;
		}
}

	public synchronized int getAssociationNumberOfMembers(int associationId){
		if(!this.tableExists("Associations")){
			//JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return 0;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select numberOfMembers from Associations where id=(?)");
			preparedStatement.setInt(1, associationId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("numberOfMembers");
			}else{
				return 0;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return 0;
		}
}

//*/
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//CONTRUCTORS TABLE
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void createContructorsTable(){

		try {
			String sql="CREATE TABLE Contructors("
					+ "id int(255) primary key auto_increment, "
					+ "roundId int(255) not null, "
					+ "foreign key(roundId) references Rounds(id), "
					+ "contructorName varchar(255) not null, "
					+ "phone varchar(20) not null, "
					+ "address varchar(255) not null, "
					+ "rank int(10) not null"
					+ ")";
			statement.execute(sql);
			System.out.println("Contructors table created");
		} catch (SQLException e) {
			 e.printStackTrace();
		}
	}

	public synchronized void insertContructors(int roundId, String contructorName, String phone, String address, int rank){
		try {
			 preparedStatement=connection.prepareStatement("INSERT INTO Contructors(roundId, contructorName, phone, address, rank) VALUES(?, ?, ?, ?, ?)");
			 preparedStatement.setInt(1, roundId);
			 preparedStatement.setString(2, contructorName);
			 preparedStatement.setString(3, phone);
			 preparedStatement.setString(4, address);
			 preparedStatement.setInt(5, rank);
			 preparedStatement.executeUpdate();//flushs(commits) to database

		System.out.println("1 row inserted successsfully");
		} catch (SQLException e) {
			 e.printStackTrace();
			return;
		}
	}

	//SETTERS Associatives TABLE	
	public synchronized void setContructorRegistrationRound(int roundId, String contructorName){
		try {
			preparedStatement=connection.prepareStatement("update Contructors set roundId = (?) where contructorName=(?)");
			preparedStatement.setInt(1, roundId);
			preparedStatement.setString(2, contructorName);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}

	public synchronized void setContructorName(String contructorName, int id){
		try {
			preparedStatement=connection.prepareStatement("update Contructors set contructorName = (?) where id=(?)");
			preparedStatement.setString(1, contructorName);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	
	public synchronized void setContructorPhone(String phone, String contructorName){
		try {
			preparedStatement=connection.prepareStatement("update Contructors set phone = (?) where contructorName=(?)");
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, contructorName);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	
	public synchronized void setContructorAddress(String address, String contructorName){
		try {
			preparedStatement=connection.prepareStatement("update Contructors set address = (?) where contructorName=(?)");
			preparedStatement.setString(1, address);
			preparedStatement.setString(2, contructorName);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}

	public synchronized void setContructorRank(int rank, String contructorName){
		try {
			preparedStatement=connection.prepareStatement("update Contructors set rank = (?) where contructorName=(?)");
			preparedStatement.setInt(1, rank);
			preparedStatement.setString(2, contructorName);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	
//GETTERS Contructors TABLE 
	public ArrayList<String> getAllContructorNames(){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		
		ArrayList<String> contructorNames=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement("select * from Contructors");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				contructorNames.add(resultSet.getString("contructorName"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return contructorNames;
	}
	
	public ArrayList<String> getAllContructorNames(int roundId){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		
		ArrayList<String> contructorNames=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement("select * from Contructors where roundId=(?)");
			preparedStatement.setInt(1, roundId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				contructorNames.add(resultSet.getString("contructorName"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return contructorNames;
	}
	
	
	public int getNumberOfContructorWithLevel(int rank, int roundId){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return 0;
		}
		
		int count=0;
		try {
			preparedStatement=connection.prepareStatement("select * from Contructors where rank=(?) && roundId=(?)");
			preparedStatement.setInt(1, rank);
			preparedStatement.setInt(2, roundId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				count++;
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return count;
	}
	
public synchronized String getContructorRegistrationRound(int conId){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
				try {
			preparedStatement=connection.prepareStatement("select * from Contructors, Rounds where Contructors.id=(?) && Contructors.roundId=Rounds.id");
			preparedStatement.setInt(1, conId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("roundName");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}


	public synchronized String getContructorPhone(String contructorName){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select phone from Contructors where contructorName=(?)");
			preparedStatement.setString(1, contructorName);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("phone");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}

	public synchronized String getContructorAddress(String contructorName){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select address from Contructors where contructorName=(?)");
			preparedStatement.setString(1, contructorName);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("address");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}

	public synchronized int getContructorRank(int id){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return -1;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select rank from Contructors where id=(?)");
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("rank");
			}else{
				return 0;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return 0;
		}
}

	public synchronized int getContructorRank(String contructorName, int roundId){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return -1;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select * from Contructors where contructorName=(?) && roundId=(?)");
			preparedStatement.setString(1, contructorName);
			preparedStatement.setInt(2, roundId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("rank");
			}else{
				return 0;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return 0;
		}
}

	public synchronized int getContructorId(String contructorName, int roundId){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return 0;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select * from Contructors where contructorName=(?) && roundId=(?)");
			preparedStatement.setString(1, contructorName);
			preparedStatement.setInt(2, roundId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("id");
			}else{
				return 0;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return 0;
		}
}
	

	public synchronized ArrayList<Integer> getAllWinnerContructorsIds(){
		ArrayList<Integer> allWinners=new ArrayList<>();
		if(!this.tableExists("WinnerContructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select * from WinnerContructors");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				allWinners.add(resultSet.getInt("contructorId"));
			}
			
			return allWinners;
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}
	

	public synchronized ArrayList<Integer> getAllContructorsIds(){
		ArrayList<Integer> allWinners=new ArrayList<>();
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select * from Contructors");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				allWinners.add(resultSet.getInt("id"));
			}
			
			return allWinners;
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}
	

//*/
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//ROLEs TABLE
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void createRolesTable(){

		try {
			String sql="CREATE TABLE Roles("
					+ "id int(255) primary key auto_increment, "
					+ "roleType varchar(255) unique not null "
					+ ")";
			statement.execute(sql);
			System.out.println("Roles table created");
		} catch (SQLException e) {
			 e.printStackTrace();
		}
	}

	public synchronized void insertRoles(String roleType){
		try {
			 preparedStatement=connection.prepareStatement("INSERT INTO Roles(roleType) VALUES(?)");
			 preparedStatement.setString(1, roleType);
			 preparedStatement.executeUpdate();//flushs(commits) to database

		System.out.println("1 row inserted successsfully");
		} catch (SQLException e) {
			 e.printStackTrace();
			return;
		}
	}

	//SETTERS Associatives TABLE	
	public synchronized void setRoleType(String roleType, int id){
		try {
			preparedStatement=connection.prepareStatement("update Roles set roleType = (?) where id=(?)");
			preparedStatement.setString(1, roleType);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
		
//GETTERS Contructors TABLE 
	public ArrayList<String> getAllRoleTypes(){
		if(!this.tableExists("Roles")){
			//JOptionPane.showMessageDialog(null, "Roles table does not Exist");
			return null;
		}
		
		ArrayList<String> roleTypes=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement("select * from Roles");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				roleTypes.add(resultSet.getString("roleType"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return roleTypes;
	}
	
	public synchronized String getRoleType(int id){
		if(!this.tableExists("Roles")){
			//JOptionPane.showMessageDialog(null, "Roles table does not Exist");
			return null;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select roleType from Roles where id=(?)");
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("roleType");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}
	

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Rounds TABLE
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void createRoundsTable(){

		try {
			String sql="CREATE TABLE Rounds("
					+ "id int(255) primary key auto_increment, "
					+ "roundName varchar(255) unique not null "
					+ ")";
			statement.execute(sql);
			System.out.println("Rounds table created");
		} catch (SQLException e) {
			 e.printStackTrace();
		}
	}

	public synchronized void insertRounds(String roundName){
		try {
			 preparedStatement=connection.prepareStatement("INSERT INTO Rounds(roundName) VALUES(?)");
			 preparedStatement.setString(1, roundName);
			 preparedStatement.executeUpdate();//flushs(commits) to database

		System.out.println("1 row inserted successsfully");
		} catch (SQLException e) {
			 e.printStackTrace();
			return;
		}
	}

	//SETTERS Associatives TABLE	
	public synchronized void setRoundName(String roundName, int id){
		try {
			preparedStatement=connection.prepareStatement("update Rounds set roundName = (?) where id=(?)");
			preparedStatement.setString(1, roundName);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
		
//GETTERS Contructors TABLE 
	public ArrayList<String> getAllRoundNames(){
		if(!this.tableExists("Rounds")){
			//JOptionPane.showMessageDialog(null, "Rounds table does not Exist");
			return null;
		}
		
		ArrayList<String> roundNames=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement("select * from Rounds");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				roundNames.add(resultSet.getString("roundName"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return roundNames;
	}
	
	public synchronized int getRoundId(String roundName){
		if(!this.tableExists("Rounds")){
			//JOptionPane.showMessageDialog(null, "Rounds table does not Exist");
			return 0;
		}
	
		try {
			preparedStatement=connection.prepareStatement("select id from Rounds where roundName=(?)");
			preparedStatement.setString(1, roundName);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("id");
			}else{
				return 0;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return 0;
		}
}
	public synchronized String getRoundName(int id){
		if(!this.tableExists("Rounds")){
			//JOptionPane.showMessageDialog(null, "Rounds table does not Exist");
			return null;
		}
		
		try {
			preparedStatement=connection.prepareStatement("select roundName from Rounds where id=(?)");
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("roundName");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}
	

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//LEVELS TABLE
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void createUsersTable(){

		try {
			String sql="CREATE TABLE Users("
					+ "id int(255) primary key auto_increment, "
					+ "username varchar(255) unique not null, "
					+ "password varchar(255) not null "
					+ ")";
			statement.execute(sql);
			System.out.println("Users table created");
		} catch (SQLException e) {
			 e.printStackTrace();
		}
	}

	public synchronized void insertUsers(String username, String password){
		try {
			 preparedStatement=connection.prepareStatement("INSERT INTO Users(username, password) VALUES(?, ?)");
			 preparedStatement.setString(1, username);
			 preparedStatement.setString(2, password);
			 preparedStatement.executeUpdate();//flushs(commits) to database

		System.out.println("1 row inserted successsfully");
		} catch (SQLException e) {
			 e.printStackTrace();
			return;
		}
	}

	//SETTERS Associatives TABLE	
	public synchronized void setUsername(String username, int id){
		try {
			preparedStatement=connection.prepareStatement("update Users set username = (?) where id=(?)");
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("1 row updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	//SETTERS Associatives TABLE	
	public synchronized void setPassword(String password, int id){
		try {
			preparedStatement=connection.prepareStatement("update Users set password = (?) where id=(?)");
			preparedStatement.setString(1, password);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("1 row updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	public synchronized String getUsername(int id){
		if(!this.tableExists("Users")){
			//JOptionPane.showMessageDialog(null, "Levels table does not Exist");
			return "";
		}
			try {
			preparedStatement=connection.prepareStatement("select * from Users where id=(?)");
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("username");
			}else{
				return "";
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return "";
		}
}
	public synchronized String getPassword(String username){
		if(!this.tableExists("Users")){
			//JOptionPane.showMessageDialog(null, "Levels table does not Exist");
			return "";
		}
			try {
			preparedStatement=connection.prepareStatement("select * from Users where username=(?)");
			preparedStatement.setString(1, username);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("password");
			}else{
				return "";
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return "";
		}
}

	public void createLevelsTable(){

		try {
			String sql="CREATE TABLE Levels("
					+ "id int(255) primary key auto_increment, "
					+ "levelName varchar(255) unique not null "
					+ ")";
			statement.execute(sql);
			System.out.println("Levels table created");
		} catch (SQLException e) {
			 e.printStackTrace();
		}
	}

	public synchronized void insertLevels(String levelName){
		try {
			 preparedStatement=connection.prepareStatement("INSERT INTO Levels(levelName) VALUES(?)");
			 preparedStatement.setString(1, levelName);
			 preparedStatement.executeUpdate();//flushs(commits) to database

		System.out.println("1 row inserted successsfully");
		} catch (SQLException e) {
			 e.printStackTrace();
			return;
		}
	}

	//SETTERS Associatives TABLE	
	public synchronized void setLevelName(String levelName, int id){
		try {
			preparedStatement=connection.prepareStatement("update Levels set levelName = (?) where id=(?)");
			preparedStatement.setString(1, levelName);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
		
//GETTERS Contructors TABLE 
	public ArrayList<Integer> getAllLevelIds(){
		if(!this.tableExists("Levels")){
			//JOptionPane.showMessageDialog(null, "Levels table does not Exist");
			return null;
		}
		
		ArrayList<Integer> levelIds=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement("select * from Levels");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				levelIds.add(resultSet.getInt("id"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return levelIds;
	}
	
	public ArrayList<String> getAllLevelNames(){
		if(!this.tableExists("Levels")){
			//JOptionPane.showMessageDialog(null, "Levels table does not Exist");
			return null;
		}
		
		ArrayList<String> levelNames=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement("select * from Levels");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				levelNames.add(resultSet.getString("levelName"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return levelNames;
	}
	
	public synchronized int getLevelId(String levelName){
		if(!this.tableExists("Levels")){
			//JOptionPane.showMessageDialog(null, "Levels table does not Exist");
			return -1;
		}
			try {
			preparedStatement=connection.prepareStatement("select id from Levels where levelName=(?)");
			preparedStatement.setString(1, levelName);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("id");
			}else{
				return 0;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return 0;
		}
}
	
	public synchronized String getContructorNameSequenceIndex(int index){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		String contructorName=null;
		try {		
			preparedStatement=connection.prepareStatement("select * from Contructors ORDER BY id LIMIT "+index);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())//while loop ends at this line
				contructorName=resultSet.getString("contructorName");
	
			return contructorName;
			} catch (SQLException e) {
				 e.printStackTrace();
		}
		return null;
	}

	public synchronized String getLevelSequenceIndex(int index){
		if(!this.tableExists("Levels")){
			//JOptionPane.showMessageDialog(null, "Levels table does not Exist");
			return null;
		}
		String levelName=null;
		try {		
			preparedStatement=connection.prepareStatement("select * from Levels ORDER BY id LIMIT "+index);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())//while loop ends at this line
				levelName=resultSet.getString("levelName");
	System.out.println(levelName);
			return levelName;
			} catch (SQLException e) {
				 e.printStackTrace();
		}
		return null;
	}

	public synchronized ArrayList<String> getLevelSequence(int index){
		if(!this.tableExists("Levels")){
			//JOptionPane.showMessageDialog(null, "Levels table does not Exist");
			return null;
		}
		
		try {
			ArrayList<String> levelNames=new ArrayList<>();
		
			preparedStatement=connection.prepareStatement("select levelName from Levels order by Id LIMIT "+index);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				levelNames.add(resultSet.getString("levelName"));
			}
			return levelNames;
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
	}

	public synchronized ArrayList<Integer> getIdSequence(String tableName, int index){
		if(!this.tableExists(tableName)){
			//JOptionPane.showMessageDialog(null, "Levels table does not Exist");
			return null;
		}
		
		try {
			ArrayList<Integer> squIds=new ArrayList<>();
		
			preparedStatement=connection.prepareStatement("select * from "+tableName+" order by Id LIMIT "+index);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				squIds.add(resultSet.getInt("id"));
			}
			return squIds;
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
	}
		public synchronized String getLevelName(int id){
			if(!this.tableExists("Levels")){
				//JOptionPane.showMessageDialog(null, "Levels table does not Exist");
				return null;
			}
			
			try {
				preparedStatement=connection.prepareStatement("select levelName from Levels where id=(?)");
				preparedStatement.setInt(1, id);
				resultSet=preparedStatement.executeQuery();
				if(resultSet.next()){
					return resultSet.getString("levelName");
				}else{
					return null;
				}
				} catch (SQLException e) {
					 e.printStackTrace();
					return null;
			}
}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//ASSOCIATION MEMBERS TABLE
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void createAssociationMembersTable(String associationName){

		try {
			String sql="CREATE TABLE "+associationName+"("
					+ "id int(255) primary key auto_increment, "
					+ "firstName varchar(255) not null, "
					+ "lastName varchar(255) not null, "
					+ "memberId varchar(255) unique not null, "
					+ "phone varchar(20) not null, "
					+ "address varchar(255) not null, "
					+ "role varchar(255) not null default 1, "
					+ "FOREIGN KEY(role) REFERENCES Roles(roleType) "
					+ ")";
			statement.execute(sql);
			System.out.println("AssociationMembers table created");
		} catch (SQLException e) {
			 e.printStackTrace();
		}
	}

	public synchronized void insertAssociationMembers(String AssociationName, String firstName, String lastName, String memberId, String phone, String address, int role){
		try {
			 preparedStatement=connection.prepareStatement("INSERT INTO "+AssociationName+"(firstName, lastName, memberId, phone, address, role) VALUES(?, ?, ?, ?, ?, ?)");
			 preparedStatement.setString(1, firstName);
			 preparedStatement.setString(2, lastName);
			 preparedStatement.setString(3, memberId);
			 preparedStatement.setString(4, phone);
			 preparedStatement.setString(5, address);
			 preparedStatement.setInt(6, role);
			 preparedStatement.executeUpdate();//flushs(commits) to database

		System.out.println("1 row inserted successsfully");
		} catch (SQLException e) {
			 e.printStackTrace();
			return;
		}
	}

	//SETTERS Associatives TABLE	
	public synchronized void setAssociationMemberFirstName(String associationName, String firstName, String memberId){
		try {
			preparedStatement=connection.prepareStatement("update (?) set firstName = (?) where memberId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, memberId);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	public synchronized void setAssociationMemberLastName(String associationName, String lastName, String memberId){
		try {
			preparedStatement=connection.prepareStatement("update (?) set lastName = (?) where memberId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, memberId);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}

	public synchronized void setAssociationMemberId(String associationName, String memberId, int id){
		try {
			preparedStatement=connection.prepareStatement("update (?) set memberId = (?) where id=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, memberId);
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	
	public synchronized void setAssociationMemberPhone(String associationName, String phone, String memberId){
		try {
			preparedStatement=connection.prepareStatement("update (?) set phone = (?) where memberId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, phone);
			preparedStatement.setString(3, memberId);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	
	public synchronized void setAssociationMemberAddress(String associationName, String address, String memberId){
		try {
			preparedStatement=connection.prepareStatement("update (?) set address = (?) where memberId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, memberId);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}

	public synchronized void setAssociationMemberRole(String associationName, int role, String memberId){
		try {
			preparedStatement=connection.prepareStatement("update (?) set role = (?) where memberId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setInt(2, role);
			preparedStatement.setString(3, memberId);
			preparedStatement.executeUpdate();
			System.out.println("table updated successfully");
			} catch (SQLException e) {
				 e.printStackTrace();
		}
}
	
//GETTERS AssociationMembers TABLE 
	public ArrayList<String> getAllAssociationMemberNames(String associationName){
		if(!this.tableExists(associationName)){
			//JOptionPane.showMessageDialog(null, associationName+" table does not Exist");
			return null;
		}
		
		ArrayList<String> associationNames=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement("select * from (?)");
			preparedStatement.setString(1, associationName);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				associationNames.add(resultSet.getString("firstName")+"\t"+resultSet.getString("lastName"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
return associationNames;
	}

	public synchronized String getAssociationMemberFirstName(String associationName, String memberId){
		if(!this.tableExists(associationName)){
			//JOptionPane.showMessageDialog(null, associationName+" table does not Exist ");
			return null;
		}
	
		try {
			preparedStatement=connection.prepareStatement("select firstName from (?) where memberId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, memberId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("firstName");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}

	public synchronized String getAssociationMemberLastName(String associationName, String memberId){
		if(!this.tableExists(associationName)){
			//JOptionPane.showMessageDialog(null, associationName+" table does not Exist");
			return null;
		}
	
		try {
			preparedStatement=connection.prepareStatement("select lastName from (?) where memberId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, memberId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("lastName");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}

	public synchronized String getAssociationMemberPhone(String associationName, String memberId){
		if(!this.tableExists(associationName)){
			//JOptionPane.showMessageDialog(null, associationName+" table does not Exist");
			return null;
		}
	
		try {
			preparedStatement=connection.prepareStatement("select phone from (?) where memberId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, memberId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("phone");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}

	public synchronized String getAssociationMemberAddress(String associationName, String memberId){
		if(!this.tableExists(associationName)){
			//JOptionPane.showMessageDialog(null, associationName+" table does not Exist");
			return null;
		}
	
		try {
			preparedStatement=connection.prepareStatement("select address from (?) where memberId=(?)");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, memberId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("address");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}

	public synchronized String getAssociationMemberRole(String associationName, String memberId){
		if(!this.tableExists(associationName)){
			//JOptionPane.showMessageDialog(null, associationName+" table does not Exist");
			return null;
		}
	
		try {
			
			preparedStatement=connection.prepareStatement("select * from (?), Roles where (?).memberId=(?) && (?).role=Roles.id");
			preparedStatement.setString(1, associationName);
			preparedStatement.setString(2, associationName);
			preparedStatement.setString(3, memberId);
			preparedStatement.setString(4, associationName);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("roleType");
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}

	
	public synchronized ArrayList<String> getAllStrings(String colmunName, String tableName){
		if(!this.tableExists(tableName)){
			//JOptionPane.showMessageDialog(null, tableName+" table does not Exist ");
			return null;
		}
	
		ArrayList<String> stringValues=new ArrayList<>();	
		try {
			String SqlSelectString = String.format("select * from %s", tableName.toLowerCase());
			preparedStatement=connection.prepareStatement(SqlSelectString);
			//preparedStatement.setString(1, colmunName);
			//preparedStatement.setString(2, tableName);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				stringValues.add(resultSet.getString(String.format("%s", colmunName)));
				System.out.println(resultSet.getString(String.format("%s", colmunName)));

			}
			return stringValues;
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}
	
	public synchronized ArrayList<Integer> getAllIntegers(String colmunName, String tableName){
		if(!this.tableExists(tableName)){
			//JOptionPane.showMessageDialog(null, tableName+" table does not Exist");
			return null;
		}
	
	
		ArrayList<Integer> integerValues=new ArrayList<>();
		try {
			//String SqlSelectString = String.format("select '"+colmunName+"' from '"+tableName.toLowerCase()+"'");
			String SqlSelectString = String.format("select * from %s", tableName.toLowerCase());
			preparedStatement=connection.prepareStatement(SqlSelectString);
			//preparedStatement.setString(1, colmunName);
			//preparedStatement.setString(2, tableName);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				integerValues.add(resultSet.getInt(String.format("%s", colmunName)));
			}
			return integerValues;
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
}
	
	public synchronized int getRegisteredNumber(String tableName){
		if(!this.tableExists(tableName)){
			//JOptionPane.showMessageDialog(null, tableName+" table does not Exist mmm");
			return 0;
		}
	
		try {
			String SqlSelectString = String.format("select * from %s", tableName.toLowerCase());
			preparedStatement=connection.prepareStatement(SqlSelectString);
			resultSet=preparedStatement.executeQuery();
			int num=0;
			while(resultSet.next()){
			num++;
			}
			return num;//getAllIntegers("id", String.format("%s", resultSet.getString(String.format("%s", colmunName)))).size());
			} catch (SQLException e) {
				 e.printStackTrace();
				return 0;
		}
}
	
	

	
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//WINNERS TABLE
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void createHouseWinnerAssociations(){

		try {
			String sql="CREATE TABLE HouseWinnerAssociations("
					+ "id int(255) auto_increment, "
					+ "associationId int(255) not null, "
					+ "blockNumber varchar(255) not null, "
					+ "roundId int(255) not null, "
					+ "primary key (id), "
					+ "FOREIGN KEY (associationId) REFERENCES Associations (id)"
					+ ")";
			statement.execute(sql);
			System.out.println("HouseWinnerAssociations table created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertHouseWinnerAssociation(int winnerAssociationId, String blockNumber, int roundId){
		try {
			String sql="INSERT INTO HouseWinnerAssociations(associationId, blockNumber, roundId) values(?, ?, ?)";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, winnerAssociationId);
			preparedStatement.setString(2, blockNumber);
			preparedStatement.setInt(3, roundId);
			preparedStatement.executeUpdate();
			System.out.println("1 row inserted");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	


	public void insertWinnerContructor(int winnerContructorId, int blockNumber, int roundId){
		try {
			String sql="INSERT INTO WinnerContructors(contructorId, blockNumber, roundId) values(?, ?, ?)";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, winnerContructorId);
			preparedStatement.setInt(2, blockNumber);
			preparedStatement.setInt(3, roundId);
			preparedStatement.executeUpdate();
			System.out.println("1 row inserted");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public void updateHouseWinnerAssociation(int winnerAssociationId, int blockNumber){
		try {
			String sql="UPDATE TABLE HouseWinnerAssociations SET associationId=(?), blockNumber=(?))";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, winnerAssociationId);
			preparedStatement.setInt(2, blockNumber);
			preparedStatement.executeUpdate();
			System.out.println("1 row inserted");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void HouseWinnerAssociations(ArrayList<Integer> winnerIds){
		try {
			String sql="INSERT INTO HouseWinnerAssociations(associationId) values(?)";
			preparedStatement=connection.prepareStatement(sql);
			
			for(int winnerId: winnerIds){
			preparedStatement.setInt(1, winnerId);
			preparedStatement.executeUpdate();
			}
			System.out.println(winnerIds.size()+" rows inserted");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void createWinnerContructors(){

		try {
			String sql="CREATE TABLE WinnerContructors("
					+ "id int(255) auto_increment, "
					+ "contructorId int(255) not null, "
					+ "blockNumber int(255) not null, "
					+ "roundId int(255) not null, "
					+ "primary key (id), "
					+ "FOREIGN KEY (contructorId) REFERENCES Contructors (id)"
					+ ")";
			statement.execute(sql);
			System.out.println("WinnerContructors table created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertWinnerContructors(int winnerContructorId, int blockNumber, int roundId){
		try {
			String sql="INSERT INTO WinnerContructors(contructorId, blockNumber, roundId) values(?, ?, ?)";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, winnerContructorId);
			preparedStatement.setInt(2, blockNumber);
			preparedStatement.setInt(3, roundId);
			preparedStatement.executeUpdate();
			System.out.println("1 row inserted");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public void updateWinnerContructor(int winnerContructorId, int blockNumber){
		try {
			String sql="UPDATE TABLE WinnerContructors SET contructorId=(?), blockNumber=(?))";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, winnerContructorId);
			preparedStatement.setInt(2, blockNumber);
			preparedStatement.executeUpdate();
			System.out.println("1 row inserted");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public LinkedHashMap<String, Integer> getAllHouseWinnerAssociationsData(int roundId){
		if(!this.tableExists("Associations")){
			//JOptionPane.showMessageDialog(null, "Associations table does not Exist");
			return null;
		}
		if(!this.tableExists("HouseWinnerAssociations")){
			//JOptionPane.showMessageDialog(null, "HouseWinnerAssociations table does not Exist");
			return null;
		}
	
		LinkedHashMap<String, Integer> winnersData=new LinkedHashMap<>();
		LinkedHashMap<Integer, Integer> winnersTempData=new LinkedHashMap<>();
		try {
			String sql="SELECT * FROM HouseWinnerAssociations WHERE roundId="+roundId+" ORDER BY id";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				winnersTempData.put(resultSet.getInt("associationId"),	resultSet.getInt("blockNumber"));
			}
			
			for(Entry<Integer, Integer> entry: winnersTempData.entrySet()){
				winnersData.put(this.getAssociationName(entry.getKey()), entry.getValue());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return winnersData;
	}
	

	public LinkedHashMap<Integer, String> getAllWinnerContructorsData(int roundId){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		if(!this.tableExists("WinnerContructors")){
			//JOptionPane.showMessageDialog(null, "WinnerContructors table does not Exist");
			return null;
		}
	
		LinkedHashMap<Integer, String> winnersData=new LinkedHashMap<>();
		LinkedHashMap<Integer, Integer> winnersTempData=new LinkedHashMap<>();
		try {
			String sql="SELECT * FROM WinnerContructors WHERE roundId="+roundId+" ORDER BY id";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				winnersTempData.put(resultSet.getInt("blockNumber"), resultSet.getInt("contructorId"));
			}
			
			for(Entry<Integer, Integer> entry: winnersTempData.entrySet()){
				winnersData.put(entry.getKey(), this.getContructorName(entry.getValue()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return winnersData;
	}
	

	public LinkedHashMap<String, Integer> getAllHouseWinnerAssociationsData(){
		if(!this.tableExists("Contructors")){
			//JOptionPane.showMessageDialog(null, "Contructors table does not Exist");
			return null;
		}
		if(!this.tableExists("WinnerContructors")){
			//JOptionPane.showMessageDialog(null, "WinnerContructors table does not Exist");
			return null;
		}
	
		LinkedHashMap<String, Integer> winnersData=new LinkedHashMap<>();
		ArrayList<Integer> contructorIds=new ArrayList<>();
		ArrayList<Integer> blockNumbers=new ArrayList<>();
		try {
			String sql="SELECT * FROM WinnerContructors";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				contructorIds.add(resultSet.getInt("contructorId"));
				blockNumbers.add(resultSet.getInt("blockNumber"));
			}
			
			for(int i=0;i<contructorIds.size();i++){
				winnersData.put(this.getAssociationName(contructorIds.get(i)), blockNumbers.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return winnersData;
	}
	
	//BLOCK NUMBER TABLE
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public void createLotteryRoundsTable(){

			try {
				String sql="CREATE TABLE LotteryRounds("
						+ "id int(255) auto_increment, "
						+ "roundId int(255) default 1, "
						+ "lotteryId int(255) default 1, "
						+ "numberOfBlocks int(255) not null, "
						+ "primary key (id) "
						+ ")";
				statement.execute(sql);
				System.out.println("LotteryRounds table created");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void insertLotteryRounds(int roundId, int lotteryId, int numberOfBlocks){
			try {
				String sql="INSERT INTO LotteryRounds(roundId, lotteryId, numberOfBlocks) values(?, ?, ?)";
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, roundId);
				preparedStatement.setInt(2, lotteryId);
				preparedStatement.setInt(3, numberOfBlocks);
				preparedStatement.executeUpdate();
				System.out.println("1 row inserted");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		

public int getLastLotteryRoundBlockNumber(){
	if(!this.tableExists("LotteryRounds")){
		//JOptionPane.showMessageDialog(null, "LotteryRounds table does not Exist");
		return 0;
	}

	int numberOfBlocks=0;
try{
String sql="SELECT * FROM LotteryRounds ORDER BY id LIMIT 1";	
preparedStatement=connection.prepareStatement(sql);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
	numberOfBlocks=resultSet.getInt("numberOfBlocks");
}catch(Exception e){
	e.printStackTrace();
}

return numberOfBlocks;
}

public int getLotteryRoundBlockNumber(int roundId, int lotteryId){
	if(!this.tableExists("LotteryRounds")){
		//JOptionPane.showMessageDialog(null, "LotteryRounds table does not Exist");
		return 0;
	}

	int numberOfBlocks=0;
try{
String sql="SELECT * FROM LotteryRounds WHERE roundId="+roundId+" && lotteryId="+lotteryId+" ORDER BY id DESC";	
preparedStatement=connection.prepareStatement(sql);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
	numberOfBlocks=resultSet.getInt("numberOfBlocks");
}catch(Exception e){
	e.printStackTrace();
}

return numberOfBlocks;
}

public int getLastLotteryRoundId(){
	if(!this.tableExists("LotteryRounds")){
		//JOptionPane.showMessageDialog(null, "LotteryRounds table does not Exist");
		return 0;
	}

	int roundId=0;
try{
String sql="SELECT * FROM LotteryRounds ORDER BY id LIMIT 1";	
preparedStatement=connection.prepareStatement(sql);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
	roundId=resultSet.getInt("roundId");
}catch(Exception e){
	e.printStackTrace();
}

return roundId;
}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void createHouseWinnersSavePointTable(){
	if(this.tableExists("HouseWinnersSavePoint"))
		return;
	try {
		String sql="CREATE TABLE HouseWinnersSavePoint("
				+ "id int(255) auto_increment, "
				+ "roundId int(255) default 0, "
				+ "savePointIndex int(255) default 0, "
				+ "primary key (id) "
				+ ")";
		statement.execute(sql);
		System.out.println("HouseWinnersSavePoint table created");
	} catch (SQLException e) {
		e.printStackTrace();
	}

}


public void insertHouseWinnersSavePoint(int roundId, int savePointIndex){
	try {
		String sql="INSERT INTO HouseWinnersSavePoint(roundId, savePointIndex) values(?, ?)";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, roundId);
		preparedStatement.setInt(2, savePointIndex);
		preparedStatement.executeUpdate();
		System.out.println("1 row inserted");

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}	

public int getHouseWinnersSavePoint(){		
	if(!this.tableExists("HouseWinnersSavePoint")){
		//JOptionPane.showMessageDialog(null, "HouseWinnersSavePoint table does not Exist");
		return 0;
	}

	try {
		String SqlSelectString = String.format("select * from HouseWinnersSavePoint ORDER BY id DESC LIMIT 1");
		preparedStatement=connection.prepareStatement(SqlSelectString);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		return resultSet.getInt("savePointIndex");
		} catch (SQLException e) {
			 e.printStackTrace();
			return 0;
	}
	return 0;
}


public int getHouseWinnersSavePoint(int roundId){		
	if(!this.tableExists("HouseWinnersSavePoint")){
		//JOptionPane.showMessageDialog(null, "HouseWinnersSavePoint table does not Exist");
		return 0;
	}

	try {
		String SqlSelectString = String.format("select * from HouseWinnersSavePoint Where roundId=(?) ORDER BY id DESC LIMIT 1");
		preparedStatement=connection.prepareStatement(SqlSelectString);
		preparedStatement.setInt(1, roundId);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		return resultSet.getInt("savePointIndex");
		} catch (SQLException e) {
			 e.printStackTrace();
			return 0;
	}
	return 0;
}


public int getHouseWinnersSavePointRoundId(){		
	if(!this.tableExists("HouseWinnersSavePoint")){
		//JOptionPane.showMessageDialog(null, "HouseWinnersSavePoint table does not Exist");
		return 0;
	}

	try {
		String SqlSelectString = String.format("select * from HouseWinnersSavePoint ORDER BY id DESC LIMIT 1");
		preparedStatement=connection.prepareStatement(SqlSelectString);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		return resultSet.getInt("roundId");
		} catch (SQLException e) {
			 e.printStackTrace();
			return 0;
	}
	return 0;
}

public void createWinnerContructorsSavePointTable(){
	if(this.tableExists("WinnerContructorsSavePoint"))
		return;
	try {
		String sql="CREATE TABLE WinnerContructorsSavePoint("
				+ "id int(255) auto_increment, "
				+ "roundId int(255) default 0, "
				+ "savePointIndex int(255) default 0, "
				+ "primary key (id) "
				+ ")";
		statement.execute(sql);
		System.out.println("WinnerContructorsSavePoint table created");
	} catch (SQLException e) {
		e.printStackTrace();
	}

}


public void insertWinnerContructorsSavePoint(int roundId, int savePointIndex){
	try {
		String sql="INSERT INTO WinnerContructorsSavePoint(roundId, savePointIndex) values(?, ?)";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, roundId);
		preparedStatement.setInt(2, savePointIndex);
		preparedStatement.executeUpdate();
		System.out.println("1 row inserted");

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}	

public int getWinnerContructorsSavePoint(){		
	if(!this.tableExists("WinnerContructorsSavePoint")){
		//JOptionPane.showMessageDialog(null, "WinnerContructorsSavePoint table does not Exist");
		return 0;
	}

	try {
		String SqlSelectString = String.format("select * from WinnerContructorsSavePoint ORDER BY id DESC LIMIT 1");
		preparedStatement=connection.prepareStatement(SqlSelectString);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		return resultSet.getInt("savePointIndex");
		} catch (SQLException e) {
			 e.printStackTrace();
			return 0;
	}
	return 0;
}

public int getWinnerContructorsSavePointRoundId(){		
	if(!this.tableExists("WinnerContructorsSavePoint")){
		//JOptionPane.showMessageDialog(null, "WinnerContructorsSavePoint table does not Exist");
		return 0;
	}

	try {
		String SqlSelectString = String.format("select * from WinnerContructorsSavePoint ORDER BY id DESC LIMIT 1");
		preparedStatement=connection.prepareStatement(SqlSelectString);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		return resultSet.getInt("roundId");
		} catch (SQLException e) {
			 e.printStackTrace();
			return 0;
	}
	return 0;
}


public LinkedHashMap<Integer, String> getAllWinnerAssociations(int roundId){		
	if(!this.tableExists("housewinnerassociations")){
		return null;
	}

	try {
		LinkedHashMap<Integer, String> winnerAssociationsDetail=new LinkedHashMap<>();
		preparedStatement=connection.prepareStatement("select * from housewinnerassociations WHERE roundId=(?) ORDER BY id");
		preparedStatement.setInt(1, roundId);
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			winnerAssociationsDetail.put(resultSet.getInt("associationId"), resultSet.getString("blockNumber"));
		}
		return winnerAssociationsDetail;
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}


public LinkedHashMap<Integer, Integer> getAllBlocksAndContructors(int roundId){		
	if(!this.tableExists("winnercontructors")){
		return null;
	}

	try {
		LinkedHashMap<Integer, Integer> blocksAndContructors=new LinkedHashMap<>();
		preparedStatement=connection.prepareStatement("select * from winnercontructors WHERE roundId=(?) ORDER BY id");
		preparedStatement.setInt(1, roundId);
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			blocksAndContructors.put(resultSet.getInt("blockNumber"), resultSet.getInt("contructorId"));
		}
		return blocksAndContructors;
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

public LinkedHashMap<Integer, Integer> getAllWinnerAssociations(){		
	if(!this.tableExists("housewinnerassociations")){
		return null;
	}

	try {
		LinkedHashMap<Integer, Integer> winnerAssociationsDetail=new LinkedHashMap<>();
		preparedStatement=connection.prepareStatement("select * from housewinnerassociations ORDER BY id");
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			winnerAssociationsDetail.put(resultSet.getInt("associationId"), resultSet.getInt("blockNumber"));
		}
		return winnerAssociationsDetail;
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}


public LinkedHashMap<Integer, Integer> getAllBlocksAndContructors(){		
	if(!this.tableExists("winnercontructors")){
		return null;
	}

	try {
		LinkedHashMap<Integer, Integer> blocksAndContructors=new LinkedHashMap<>();
		preparedStatement=connection.prepareStatement("select * from winnercontructors ORDER BY id");
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			blocksAndContructors.put(resultSet.getInt("blockNumber"), resultSet.getInt("contructorId"));
		}
		return blocksAndContructors;
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}





	//DROP TABLE(tableName)
public void dropTable(String tableName){
if(!tableExists(tableName))
return;

	String sql="DROP TABLE "+tableName;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.execute();
			System.out.println(tableName+" Table droped");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
//DELETE ENTIRE TABLE CONTENT
public void deleteTableContents(String tableName){
if(!tableExists(tableName))
return;
	try {
		String sql=String.format("DELETE FROM %s ", tableName);
		preparedStatement=connection.prepareStatement( sql+" WHERE id > (?)");
		preparedStatement.setInt(1, 0);
		preparedStatement.execute();
		System.out.println("\n\n-----"+tableName+" Contents Cleared-----\n\n");

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
//DELETE TABLE Column name
public void deleteRow(String tableName, int id){
if(!tableExists(tableName))
return;
	try {
		String sql=String.format("DELETE FROM %s ", tableName);
		preparedStatement=connection.prepareStatement( sql+" WHERE id = (?)");
		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		System.out.println("row Deleted");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public void deleteRow(String tableName, String columnName, int columnValue){//exhaustive delete
if(!tableExists(tableName))
return;

String sql=String.format("DELETE FROM %s WHERE %s = %s ",tableName, columnName, columnValue);
	try {
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.execute();
		System.out.println(columnValue+" Deleted");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

//DELETE TABLE Column name
public void deleteRow(String tableName, String columnName, String columnValue, int roundId){
if(!tableExists(tableName))
return;

String sql="DELETE FROM "+tableName+" WHERE "+columnName+" = '"+columnValue+"' && roundId="+roundId;
	try {
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.execute();
		System.out.println(columnValue+" Deleted");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}


public boolean tableExists(String tableName){
try {
    if(connection==null){
        return false;
    }
	// check if "tableName" table is there
	ResultSet tables;
	DatabaseMetaData dbm = connection.getMetaData();
	tables = dbm.getTables(null, null, tableName, null);
	if (tables.next()) {
		 return true;
		}
	else 
		return false;
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
return false;
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//IS UNIQUE OVERLOADED FUNCTION
public boolean isUnique(String colmunName, String value, String tableName){
	System.out.println(value+"  is 3");

	if(!this.tableExists(tableName))
		return true;
	try {
		String SqlSelectString = String.format("select * from %s where %s=", tableName.toLowerCase(), colmunName.toLowerCase());
		preparedStatement=connection.prepareStatement(SqlSelectString+" (?)");
		preparedStatement.setString(1, value);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			return false;
		}
	} catch (SQLException e) {
		 e.printStackTrace();
		//	return false;
	}

	return true;
}

public boolean isUnique(String colmunName, String value, String tableName, int roundId){
	System.out.println("round id =="+roundId);
	if(!this.tableExists(tableName))
		return true;
	try {
		
		String SqlSelectString = String.format("select * from "+tableName.toLowerCase()+" where roundId="+roundId+" && "+colmunName.toLowerCase()+" = '"+value.toLowerCase()+"'");
		preparedStatement=connection.prepareStatement(SqlSelectString);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			return false;
		}
	} catch (SQLException e) {
		 e.printStackTrace();
			return false;
	}

	return true;
}

public boolean isUnique(String colmunName, int value, String tableName){
	if(!this.tableExists(tableName))
		return true;
	
	try {
		String SqlSelectString = String.format("select * from "+tableName.toLowerCase()+" where "+colmunName.toLowerCase()+" = "+value+"");
		preparedStatement=connection.prepareStatement(SqlSelectString);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			return false;
		}
	} catch (SQLException e) {
		 e.printStackTrace();
			return false;
	}

	return true;
}

public boolean isUnique(String colmunName, int value, String tableName, int roundId){
	if(!this.tableExists(tableName))
		return true;
	
	try {
		String SqlSelectString = String.format("select * from "+tableName.toLowerCase()+" where "+colmunName.toLowerCase()+" = "+value+" && roundId="+roundId);
		preparedStatement=connection.prepareStatement(SqlSelectString);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			return false;
		}
	} catch (SQLException e) {
		 e.printStackTrace();
			return false;
	}

	return true;
}

public boolean renameTable(String oldName, String newName){
	try {
		//if(this.tableExists(oldName))
		String renameSql="RENAME TABLE "+oldName+" TO "+newName;
		preparedStatement=connection.prepareStatement(renameSql);
		preparedStatement.execute();
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}

public boolean databaseExists(String databaseName){
	try {
		Connection conTry=driverManager.getConnection("jdbc:mysql://127.0.0.1:3306?characterEncoding=UTF-8", "root", "");//without database name to check if database exists
		ResultSet resultSet=conTry.getMetaData().getCatalogs();
		while(resultSet.next()){
			String dbName=resultSet.getString(1);//always index sets to 1 after each iteration
			if(databaseName.equalsIgnoreCase(dbName))
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
return false;
}

public boolean createDatabase(String databaseName){
	try {
		Connection conTry=driverManager.getConnection("jdbc:mysql://127.0.0.1:3306?characterEncoding=UTF-8", "root", "");//without database name to check if database exists
		String sql="CREATE DATABASE "+databaseName+"";
		statement=conTry.createStatement();
		statement.execute(sql);
		return true;
	} catch (SQLException e) {
	return false;
	}
}

public boolean createUTFDatabase(String databaseName){
	try {
		Connection conTry=driverManager.getConnection("jdbc:mysql://127.0.0.1:3306?characterEncoding=UTF-8", "root", "");//without database name to check if database exists
		String sql="CREATE DATABASE "+databaseName+" CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
		statement=conTry.createStatement();
		statement.execute(sql);
		return true;
	} catch (SQLException e) {
	return false;
	}
}

void alterTableName(){
String sql ="ALTER TABLE roles CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci";
try {
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.executeUpdate();
System.out.println("changed to  CHARACTER SET utf8 COLLATE utf8_general_ci");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

public Association getAssociation(String associationName, int roundId){
    System.out.println("Round_"+roundId+"___"+associationName);
    try {
			preparedStatement=connection.prepareStatement("select * from associations where associationName=(?) limit 1");
			preparedStatement.setString(1, "Round_2___ጉና 2020"/*"Round_"+roundId+"___"+associationName*/);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				new Association(0, resultSet.getString("associationName"), Integer.valueOf(resultSet.getString("registrationNumber")), resultSet.getString("nameOfChairman"), resultSet.getString("phoneNumber"), "");
        
			}else{
				return null;
			}
			} catch (SQLException e) {
				 e.printStackTrace();
				return null;
		}
    return null;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args){
		DatabaseManager databaseManager=new DatabaseManager();

		//databaseManager.getContructorRank(contructorName, roundId);
		//databaseManager.alterTableName();
		//System.out.println(databaseManager.getNumberOfContructorWithLevel(1));
		System.out.println(databaseManager.isUnique("levelName", "ደረጃ 1", "Levels"));
		//databaseManager.insertLotteryRounds(1, 1, 3);
		//databaseManager.insertLotteryRounds(1, 2, 4);
		//System.out.println("save point = "+databaseManager.getLotteryRoundBlockNumber(1, 1));
		//LinkedHashMap<String, Integer> map= databaseManager.getAllHouseWinnerAssociationsData(5);
		//for(Entry<String, Integer> m: map.entrySet())
			//System.out.println(m.getKey()+"  "+m.getValue());
		//databaseManager.dropTable("Winners");
		//databaseManager.createLevelsTable();
		//databaseManager.deleteRow("Associations", "associationName", "asso1");
	}
}



