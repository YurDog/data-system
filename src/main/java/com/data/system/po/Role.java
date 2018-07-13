package com.data.system.po;

import java.util.Date;

public class Role {
	private int id;
	private String userName;
	private int age;
	private double money;
	private Date birthday;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", userName=" + userName + ", age=" + age + ", money=" + money + ", birthday=" + birthday + "]";
	}
}
