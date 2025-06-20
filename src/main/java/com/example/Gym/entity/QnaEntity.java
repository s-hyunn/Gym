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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qna_seq_gen")
	@SequenceGenerator(
	    name = "qna_seq_gen",           // JPA 식별용 이름
	    sequenceName = "QNA_SEQ",       // 오라클 실제 시퀀스 이름 (대소문자 주의)
	    allocationSize = 1              // 1씩 증가
	)
	private Long qno;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    private String title;

    @Lob
    private String content;

    private String status;

    @Lob
    private String answer;

    private String field; // 추가: 해당 문의 분야
}

