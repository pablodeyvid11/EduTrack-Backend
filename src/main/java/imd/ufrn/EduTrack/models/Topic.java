package imd.ufrn.EduTrack.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "topic")
public class Topic extends AbstractEntity {
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

	@Column(name = "is_subtopic")
	@NotNull
	private Boolean isSubtopic;

	@OneToOne
	@JoinColumn(name = "previous")
	@JsonIgnore
	private Topic previous;

	@OneToOne
	@JoinColumn(name = "next")
	@JsonIgnore
	private Topic next;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "Topic_has_Subtopic", joinColumns = @JoinColumn(name = "topic_id"), inverseJoinColumns = @JoinColumn(name = "subtopic_id"))
	private List<Topic> topics;

	@OneToMany(mappedBy = "topic")
	private List<Content> contents;

	public Topic() {
	}

	public Topic(Long id,
			@NotNull @NotBlank(message = "O nome não pode estar em branco") @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres") String name,
			@NotNull @NotBlank(message = "A descrição não pode estar em branco") String description,
			@NotNull Boolean isSubtopic, Topic previous, Topic next, List<Topic> topics, List<Content> contents) {
		super(id);
		this.name = name;
		this.description = description;
		this.isSubtopic = isSubtopic;
		this.previous = previous;
		this.next = next;
		this.topics = topics;
		this.contents = contents;
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

	public Boolean getIsSubtopic() {
		return isSubtopic;
	}

	public void setIsSubtopic(Boolean isSubtopic) {
		this.isSubtopic = isSubtopic;
	}

	public Topic getPrevious() {
		return previous;
	}

	public void setPrevious(Topic previous) {
		this.previous = previous;
	}

	public Topic getNext() {
		return next;
	}

	public void setNext(Topic next) {
		this.next = next;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

}
