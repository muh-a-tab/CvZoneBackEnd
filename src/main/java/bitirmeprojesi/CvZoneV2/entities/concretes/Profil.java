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
import javax.persistence.OneToOne;
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
@Table(name="profil")
@TypeDef(
	    name = "list-array",
	    typeClass = ListArrayType.class
	)
public class Profil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profil_id")
	private int profilId;
	
	
	@NotNull
	@Column(name = "kullanici_ismi",length = 100)
	private String kullaniciIsmi;
	
	
	@Column(name = "unvan",length = 100)
	private String unvan;
	
	@NotNull
	@Column(name = "takip_edilen")
	private int takipEdilen;
	
	@NotNull
	@Column(name = "takip_eden")
	private int takipEden;
	
	
	@Column(
			name = "sosyal_medya",
			columnDefinition = "text"
			)
	private String sosyalMedya;
	
	@Column(
			name = "onerilenler",
			columnDefinition = "text"
			)
	private String onerilenler;
	
	@Column(name = "hakkinda")
	private String hakkinda;
	
	
	@Column(
			name = "tecrubeler",
			columnDefinition = "text"
			)
	private String tecrubeler;
	
	
	@Column(
			name = "egitim_gecmisi",
			columnDefinition = "text"
			)
	private String egitimGecmisi;
	
	@Column(name = "konum")
	private String konum;
	
	
	@Column(
			name = "yetenekler",
			columnDefinition = "text"
			)
	private String yetenekler;
	
	
	@Column(
			name = "kaydedilenler",
			columnDefinition = "text"
			)
	private String kaydedilenler;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Kullanici kullanici;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date(System.currentTimeMillis());
	
	
	
}
