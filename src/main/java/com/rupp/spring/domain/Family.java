package com.rupp.spring.domain;

/**
 * map to table families
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */

public class Family extends AbstractEntity {
	
	public static final String TABLE = "families";
	
	private Long father;
	private Long mother;
	private Long child;
	private String note;
	
	public Family() {}
	public Family(Long id, Long father, Long mother, Long child, String note) {
		super.setId(id);
		setFather(father);
		setMother(mother);
		setChild(child);
		setNote(note);
	}
	
	public Long getFather() {
		return father;
	}
	public void setFather(Long father) {
		this.father = father;
	}
	public Long getMother() {
		return mother;
	}
	public void setMother(Long mother) {
		this.mother = mother;
	}
	public Long getChild() {
		return child;
	}
	public void setChild(Long child) {
		this.child = child;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
