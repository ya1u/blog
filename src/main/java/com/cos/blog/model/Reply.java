package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity	// Users 클래스가 자동으로 DB에 테이블이 생성이 된다.
@SequenceGenerator(
			name = "USER_SEQ_GENERATOR3"
			, sequenceName = "USER_SEQ3"
			, initialValue = 1
			, allocationSize = 1
			
		)
public class Reply {
	@Id	//Primary Key
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR3")
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "boardsId")
	private Boards boards;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users users;
	
	@CreationTimestamp
	private Timestamp createDate;
}