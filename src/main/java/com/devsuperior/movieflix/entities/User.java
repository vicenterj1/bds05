package com.devsuperior.movieflix.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




@Entity
@Table(name = "tb_user")
public class User implements   UserDetails, Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	
	// Associação de muitos pra muitos e
	// e o set garante a não repetição
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user")
	private List<Review> reviews = new ArrayList<>();

	public User() {
	}

	public User(Long id, String name, String email, String password) {	
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	//implementando os métodos básicos do UserDetails 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// converter o tipo role para Authority
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		// porque o username é o email
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// não estamos preocupados por enquanto
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// não estamos preocupados por enquanto
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// não estamos preocupados por enquanto
		return true;
	}

	@Override
	public boolean isEnabled() {
		// não estamos preocupados por enquanto
		return true;
	}
	
	public boolean hasRole(String roleName) {
		Boolean foundRole = false;
		for (Role role : roles) {
			if (role.getAuthority().equals(roleName)) {
				foundRole = true;
			}
		}
		return foundRole;
	}

	public List<Review> getReviews() {
		return reviews;
	}
}
