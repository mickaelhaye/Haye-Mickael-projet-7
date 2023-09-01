package com.nnk.springboot.domain;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * this class is the entity User.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "users")
public class User implements UserDetails {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/**
	 * username
	 */
	@Column(name = "username")
	private String username;

	/**
	 * password
	 */
	@Column(name = "password")
	private String password;

	/**
	 * fullname
	 */
	@Column(name = "fullname")
	private String fullname;

	/**
	 * role
	 */
	@Column(name = "role")
	private String role = "ROLE_USER";

	/**
	 * this method is to recover the role of the user.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}

	/**
	 * this method is to recover the username of the user.
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * this method is to recover if the account is expired
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * this method is to recover if the account is locked
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * this method is to recover if the credential is expired
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * this method is to recover if the user is enabled
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
