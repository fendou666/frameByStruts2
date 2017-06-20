package com.chinasofti.eecuser.model.javabean;
import java.util.Date;
public class UserInfo {
//field(21)
  private int id;
  private String name;
  private String nickname;
  private String sex;
  private Date birthDate;
  private int age;
  private String constellatory;
  private int managerId;
  private int groupId;
  private int roleId;
  private int classId;
  private long telephone;
  private String email;
  private Date registrationDate;
  private Date lastLoginTime;
  private int isDelete;
  private String headImg;
  private String roleName;
  private String groupName;
  private int priviUpperId;
  private int priviValue;
  //constructor
  public UserInfo() {
		super();
	}
  //constructor3 
	public UserInfo(int id,String name,int roleId) {
		this.id = id;
		this.name = name;
		this.roleId = roleId;
	}
   //constructor4
	public UserInfo(String name,String sex,int groupId,int roleId) {
		this.sex = sex;
		this.name = name;
		this.roleId = roleId;
		this.groupId = groupId;
	}
  //constructor5 --session保持
	public UserInfo(int id,String name,int roleId,int groupId,int classId) {
		this.id = id;
		this.name = name;
		this.roleId = roleId;//角色id
		this.groupId = groupId;//小组id
		this.classId = classId;//班级id
	}
	//constructor8
	public UserInfo(int classId, int id, String name, String sex,
			int age,  String email, long telephone, String roleName){
		this.classId = classId;
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.email = email;
		this.telephone = telephone;
		this.roleName  = roleName;
	}
	
	public UserInfo(int classId, int id, String name, String sex,
			int age,  String email, long telephone, int roleId){
		this.classId = classId;
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.email = email;
		this.telephone = telephone;
		this.roleId  = roleId;
	}
	
	
	//constructor12
	public UserInfo(int id, String name, String nickname, String sex,
			Date birthDate, int age,int roleId, int classId, long telephone, String email,
			Date registrationDate,String headImg){
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.birthDate = birthDate;
		this.age = age;
		this.roleId = roleId;
		this.classId = classId;
		this.telephone = telephone;
		this.email = email;
		this.registrationDate = registrationDate;
		this.headImg = headImg;
	}
//Access
	public UserInfo(int id, String name, String nickname, String sex,
			Date birthDate, int age, String constellatory, int managerId,
			int groupId, int roleId, int classId, long telephone, String email,
			Date registrationDate, Date lastLoginTime, int isDelete,
			String headImg) {
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.birthDate = birthDate;
		this.age = age;
		this.constellatory = constellatory;
		this.managerId = managerId;
		this.groupId = groupId;
		this.roleId = roleId;
		this.classId = classId;
		this.telephone = telephone;
		this.email = email;
		this.registrationDate = registrationDate;
		this.lastLoginTime = lastLoginTime;
		this.isDelete = isDelete;
		this.headImg = headImg;
		}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public long getTelephone() {
		return telephone;
	}
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConstellatory() {
		return constellatory;
	}
	public void setConstellatory(String constellatory) {
		this.constellatory = constellatory;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getPriviUpperId() {
		return priviUpperId;
	}
	public void setPriviUpperId(int priviUpperId) {
		this.priviUpperId = priviUpperId;
	}
	public int getPriviValue() {
		return priviValue;
	}
	public void setPriviValue(int priviValue) {
		this.priviValue = priviValue;
	}
	
}