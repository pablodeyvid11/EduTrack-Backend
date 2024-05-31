package imd.ufrn.EduTrack.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "content")
public class Content extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "topic", nullable = false)
	private Topic topic;

	@Column(name = "name", nullable = false)
	@NotNull
	@NotBlank(message = "O nome não pode estar em branco")
	@Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "link")
	private String link;

	public Content() {
	}

	public Content(Long id, Topic topic,
			@NotNull @NotBlank(message = "O nome não pode estar em branco") @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres") String name,
			String description, String link) {
		super(id);
		this.topic = topic;
		this.name = name;
		this.description = description;
		this.link = link;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(topic);
		return result + super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		return Objects.equals(topic, other.topic) && super.equals(obj);
	}

}
