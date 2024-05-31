package imd.ufrn.EduTrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import imd.ufrn.EduTrack.models.AbstractEntity;

public interface GenericRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {

}
