package imd.ufrn.EduTrack.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "track")
public class Track extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	@NotNull
	@NotBlank(message = "O nome não pode estar em branco")
	@Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
	private String name;

	@Column(name = "description", nullable = false)
	@NotNull
	@NotBlank(message = "A descrição não pode estar em branco")
	private String description;

	@Column(name = "creation_date", nullable = false)
	@NotNull
	private LocalDateTime creationDate;

	@Column(name = "modification_date", nullable = false)
	@NotNull
	private LocalDateTime modificationDate;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "owner", nullable = false)
	private User owner;

	@ManyToMany
	@JoinTable(name = "Track_has_Topic", joinColumns = @JoinColumn(name = "track_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
	private List<Topic> topics;

	public Track() {
	}

	public Track(Long id,
			@NotNull @NotBlank(message = "O nome não pode estar em branco") @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres") String name,
			@NotNull @NotBlank(message = "A descrição não pode estar em branco") String description,
			@NotNull LocalDateTime creationDate, @NotNull LocalDateTime modificationDate, @NotNull User owner,
			List<Topic> topics) {
		super(id);
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.owner = owner;
		this.topics = topics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDateTime modificationDate) {
		this.modificationDate = modificationDate;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

}
