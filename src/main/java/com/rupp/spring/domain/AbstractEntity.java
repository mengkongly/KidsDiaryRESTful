package com.rupp.spring.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */

public abstract class AbstractEntity {
	public final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
	
	private Long id;
	private Date createdAt;
	private Date deletedAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
}
