package com.fr.technicaltestoffer.repository;

import com.fr.technicaltestoffer.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {

}
