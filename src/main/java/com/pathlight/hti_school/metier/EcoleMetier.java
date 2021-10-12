package com.pathlight.hti_school.metier;
import java.util.Optional;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Role;
import com.pathlight.hti_school.entities.User;

public interface EcoleMetier {
public void AddEcole(Classe classe);

public void AddUser(User user, Long id);

public Role getIdRole(Long id);
public Role getIdRoleUser(Long id);
}
