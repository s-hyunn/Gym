package com.example.Gym.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "QNA_TB")
@Data
public class QnaEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qna_seq")
	@SequenceGenerator(name = "qna_seq", sequenceName = "QNA_SEQ", allocationSize = 1)
	private Long qno;


    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    private String title;

    @Lob
    private String content;

    private String status; // 대기 / 완료

    @Lob
    private String answer;
}
