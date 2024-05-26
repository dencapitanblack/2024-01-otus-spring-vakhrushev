package ru.cap.home.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cap.home.models.User;

public interface UserRepository extends CrudRepository<User, String> {

}
