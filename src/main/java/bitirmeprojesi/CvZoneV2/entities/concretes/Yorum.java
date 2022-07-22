package bitirmeprojesi.CvZoneV2.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="yorum")
public class Yorum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "yorum_id")
	private int yorumId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Kullanici kullanici;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Post post;
	
	@NotNull
	@Column(name = "yorum",columnDefinition="text")
	private String yorum;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date(System.currentTimeMillis());
	
}
