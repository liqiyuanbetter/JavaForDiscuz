package com.oldbutcher.javafordiscuz.qingtui.entity;

public class Assosiation {
	private String mobile;
	private int id;

	@Override
	public String toString() {
		return "Assosiation [mobile=" + mobile + ", id=" + id + "]";
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
