package al.WeWebTask.Model.Repository;

import al.WeWebTask.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
