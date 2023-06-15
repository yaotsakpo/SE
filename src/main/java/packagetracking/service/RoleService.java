package packagetracking.service;

import packagetracking.model.Role;

import java.util.List;

public interface RoleService {

    public abstract List<Role> getAllRoles();

    public abstract Role getRoleByName(String Name);

}
