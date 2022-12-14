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
@Table(name="medya")
public class Medya {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medya_id")
	private int medyaId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Kullanici kullanici;
	
	@Column(name= "belge",columnDefinition = "text")
	private String belge;
	
	@Column(name= "belge_adi")
	private String belgeAdi;
	
	
}
