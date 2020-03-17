package com.techjs.yourbookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@NamedQueries({
	@NamedQuery(name = "authenticate_admin", query = "FROM Admin WHERE adminId=:id AND password=:pass")
})
public class Admin extends User {
	
	@Column(name = "ADMIN_ID",unique = true, length = 25)
	private String adminId;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
}
