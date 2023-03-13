package com.epg.user.manager.models.repositories;

import com.epg.user.manager.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    public User findByUserName(String userName);
}
