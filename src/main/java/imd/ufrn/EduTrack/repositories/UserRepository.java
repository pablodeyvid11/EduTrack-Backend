package imd.ufrn.EduTrack.repositories;

import java.util.Optional;

import imd.ufrn.EduTrack.models.User;

public interface UserRepository extends GenericRepository<User> {
	Optional<User> findByUsername(String username);
}
