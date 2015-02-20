package com.dao;

import java.io.Serializable;

import com.model.Dog;

public class DogDAO extends GenericDAO<Dog> implements Serializable{
	private static final long serialVersionUID = 1L;

	public DogDAO() {
		super(Dog.class);
	}

	public void delete(Dog dog) {
		super.delete(dog.getId(), Dog.class);
	}
}
