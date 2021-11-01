package org.launchcode.codingevents.data;


import org.launchcode.codingevents.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
//    this method takes a username and retuurns given user with that username
    User findByUsername(String username);

}