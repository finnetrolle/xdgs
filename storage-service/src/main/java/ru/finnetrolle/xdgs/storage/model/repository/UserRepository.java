package ru.finnetrolle.xdgs.storage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.finnetrolle.xdgs.storage.model.entity.User;

/**
 * Created by finnetrolle on 14.02.2016.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
