package objects;

import utility.StringUtil;

public class User {

	private int userID;
	private String firstName;
	private String lastName;
	private String middleName;
	private String squadron;
	private String email;
	private String base;
	private String rank;
	private int rankID;
	private String password;
	private String idHash;

	public User(int id, String firstName, String lastName,
			String middleName, String squadron, String base, String rank, String email) {
		
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setMiddleName(middleName);
		setSquadron(squadron);
		setBase(base);
		setRank(rank);
		setEmail(email);
	}
	
	public User(String firstName, String lastName,
			String middleName, String squadron, String base, String rank, String email) {
		
		setFirstName(firstName);
		setLastName(lastName);
		setMiddleName(middleName);
		setSquadron(squadron);
		setBase(base);
		setRank(rank);
		setEmail(email);
	}


	public User() {

	}

	public int getId() {
		return userID;
	}

	public void setId(int id) {
		this.userID = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = StringUtil.capitalize(firstName.trim().toLowerCase());
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = StringUtil.capitalize(lastName.trim().toLowerCase());
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		if(!middleName.isEmpty()){
			this.middleName = StringUtil.capitalize(middleName.trim().toLowerCase());
		}else{
			this.middleName = "";
		}
	}

	public String getSquadron() {
		return squadron;
	}

	public void setSquadron(String squadron) {
		this.squadron = squadron.trim();
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public int getRankID() {
		return rankID;
	}

	public void setRankID(int rankID) {
		this.rankID = rankID;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim().toLowerCase();
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/*get and setIdHash are only used during user creation. 
	After that the field is left blank in db and is therefore, 
	blank here*/
	public String getIdHash() {
		return idHash;
	}

	public void setIdHash(String idHash) {
		this.idHash = idHash;
	}

	public String getFullName(){
		String name;
		if(middleName==null||middleName.trim().equals("")){
			name= firstName+" "+lastName;
		}else{
			name = firstName+" "+middleName+" "+lastName;
		}		
		return StringUtil.capitalize(name);
	}
	
	public String getDisplayName(){
		return getRank()+" "+getFullName();
	}
	
	public String toString(){
		String str="";
		str+=getId()+" - ";
		str+=getRank()+" - ";
		str+=getFullName()+" - ";
		str+=getSquadron()+" - ";
		str+=getBase()+" - ";
		str+=getEmail();
		return str;
	}
}
