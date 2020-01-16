package com.example.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Customer implements Serializable {

	private static final long serialVersionUID = 917606022899856201L;

    @Id
	private String id;
    private String name;
	private String email;
	private String document;
	
}
