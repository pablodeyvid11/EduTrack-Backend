package imd.ufrn.EduTrack.services;

import java.io.Serializable;
import java.util.List;

import imd.ufrn.EduTrack.models.AbstractEntity;
import imd.ufrn.EduTrack.repositories.GenericRepository;

public abstract class GenericService<T extends AbstractEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract GenericRepository<T> getRepository();

	public T insert(T obj) {
		return getRepository().save(obj);
	}

	public T findById(Long id) {
		return getRepository().findById(id).orElse(null);
	}

	public List<T> findAll() {
		return getRepository().findAll();
	}

	public T update(T obj) {
		return getRepository().save(obj);
	}

	public void delete(Long id) {
		getRepository().deleteById(id);
	}
}
