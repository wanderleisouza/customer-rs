package com.example.security;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomPrincipal implements Serializable {

	private static final long serialVersionUID = 8060043001670022416L;
	
	private String username;
	private String email;

}
