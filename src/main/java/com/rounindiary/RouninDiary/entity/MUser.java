package com.rounindiary.RouninDiary.entity;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="user")
public class MUser {

	@Id
	@Column(name = "id")
	String id;

	@Column(name = "name")
	String name;

	@Column(name = "password")
	String password;

	@Column(name = "role")
	String role;

	@OneToMany
	@JoinColumn(insertable=false, updatable=false, name="id")
	List<Diary> diarys;


}
