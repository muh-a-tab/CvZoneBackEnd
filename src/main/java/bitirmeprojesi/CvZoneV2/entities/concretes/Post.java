package bitirmeprojesi.CvZoneV2.entities.concretes;


import java.util.Date;
import java.util.List;

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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.array.ListArrayType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="post")
@TypeDef(
	    name = "list-array",
	    typeClass = ListArrayType.class
	)
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int postId;
	
	@Column(name = "baslik")
	private String baslik;
	
	@NotNull
	@Column(name = "konum")
	private String konum;
	
	@NotNull
	@Column(name = "calisma_turu")
	private String calismaTuru;
	
	@NotNull
	@Column(name = "icerik")
	private String icerik;
	
	
	@Type(type = "list-array")
	@Column(
			name = "etiketler",
			columnDefinition = "text[]"
			)
	private List<String> etiketler;
	
	@Column(name = "begeniler")
	private int begeniler;
	
	@Column(name = "yorumlar")
	private int yorumlar;
	
	@Column(name = "goruntulenme")
	private int goruntulenme;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Kullanici kullanici;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date(System.currentTimeMillis());

	
}
