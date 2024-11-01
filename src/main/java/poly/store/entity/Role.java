package poly.store.entity;

import java.io.Serializable;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Roles")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String role;
	private String description;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<RoleDetail> roleDetails;
	public Role() {
		super();
	}
	public Role(String role, String description, List<RoleDetail> roleDetails) {
		super();
		this.role = role;
		this.description = description;
		this.roleDetails = roleDetails;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<RoleDetail> getRoleDetails() {
		return roleDetails;
	}
	public void setRoleDetails(List<RoleDetail> roleDetails) {
		this.roleDetails = roleDetails;
	}
}
