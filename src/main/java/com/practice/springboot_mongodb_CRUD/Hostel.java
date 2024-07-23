package com.practice.springboot_mongodb_CRUD;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="hostel")
public class Hostel 
{
	int id;
	String name;
	int roomno;
	int rent;
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
	public int getRoomno() {
		return roomno;
	}
	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	
	
	
}
