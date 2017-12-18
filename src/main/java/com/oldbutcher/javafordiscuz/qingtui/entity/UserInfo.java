package com.oldbutcher.javafordiscuz.qingtui.entity;

import java.util.Arrays;

public class UserInfo {
	// 姓名
	private String name;
	// 邮箱
	private String mail;
	// 头像
	private String avatar;
	// 个人描述
	private String comment;
	// 是否为访客
	private String guest;
	// userId
	private String user_id;
	// 电话
	private String mobile;
	// 所属组织机构
	private String[] org_list;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String[] getOrg_list() {
		return org_list;
	}

	public void setOrg_list(String[] org_list) {
		this.org_list = org_list;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", mail=" + mail + ", avatar=" + avatar + ", comment=" + comment + ", guest="
				+ guest + ", user_id=" + user_id + ", mobile=" + mobile + ", org_list=" + Arrays.toString(org_list)
				+ "]";
	}

}
