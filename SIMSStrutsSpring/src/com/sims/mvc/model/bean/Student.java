package com.sims.mvc.model.bean;

public class Student {
	private String id;	//ѧ��
	private String name;	//����
	private String sex;	//�Ա�
	private int age;	//����
	private String gradFrom;	//��ҵԺУ
	private long tel;	//�绰����
	private String addr;	//��ַ
	private String idCard;	//���֤����
	private String email;	//����
	private String teamID;	//��id
	private String classID;	//�༶id
	private int t_leader;//�Ƿ�Ϊ�鳤
	private int c_monitor;//�Ƿ�Ϊ�೤
	private int c_man_teacher;//�Ƿ�Ϊ������
	private int c_teac_teacher;//�Ƿ�Ϊ������ʦ
	private int normal_manager;//�Ƿ�Ϊһ�����Ա
	
	public Student() {}
	
	public Student(String id, String name, String sex, int age,
			String gradFrom, long tel, String addr, String idCard, String email,
			String teamID, String classID) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.gradFrom = gradFrom;
		this.tel = tel;
		this.addr = addr;
		this.idCard = idCard;
		this.email = email;
		this.teamID = teamID;
		this.classID = classID;
	}

	public Student(String id, String name, String sex, int age,
			String gradFrom, int tel, String addr, String idCard, String email,
			String teamID, String classID, int t_leader, int c_monitor,
			int c_man_teacher, int c_teac_teacher, int normal_manager) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.gradFrom = gradFrom;
		this.tel = tel;
		this.addr = addr;
		this.idCard = idCard;
		this.email = email;
		this.teamID = teamID;
		this.classID = classID;
		this.t_leader = t_leader;
		this.c_monitor = c_monitor;
		this.c_man_teacher = c_man_teacher;
		this.c_teac_teacher = c_teac_teacher;
		this.normal_manager = normal_manager;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGradFrom() {
		return gradFrom;
	}

	public void setGradFrom(String gradFrom) {
		this.gradFrom = gradFrom;
	}

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}



	public int getT_leader() {
		return t_leader;
	}



	public void setT_leader(int t_leader) {
		this.t_leader = t_leader;
	}



	public int getC_monitor() {
		return c_monitor;
	}



	public void setC_monitor(int c_monitor) {
		this.c_monitor = c_monitor;
	}



	public int getC_man_teacher() {
		return c_man_teacher;
	}



	public void setC_man_teacher(int c_man_teacher) {
		this.c_man_teacher = c_man_teacher;
	}



	public int getC_teac_teacher() {
		return c_teac_teacher;
	}



	public void setC_teac_teacher(int c_teac_teacher) {
		this.c_teac_teacher = c_teac_teacher;
	}



	public int getNormal_manager() {
		return normal_manager;
	}



	public void setNormal_manager(int normal_manager) {
		this.normal_manager = normal_manager;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", age=" + age + ", gradFrom=" + gradFrom + ", tel=" + tel
				+ ", addr=" + addr + ", idCard=" + idCard + ", email=" + email
				+ ", teamID=" + teamID + ", classID=" + classID + ", t_leader="
				+ t_leader + ", c_monitor=" + c_monitor + ", c_man_teacher="
				+ c_man_teacher + ", c_teac_teacher=" + c_teac_teacher
				+ ", normal_manager=" + normal_manager + "]";
	}
	
}
