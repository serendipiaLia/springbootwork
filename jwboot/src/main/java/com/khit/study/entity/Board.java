package com.khit.study.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Board {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동 순번
	@Id //기본키(설정 안하면 오류)
	private int id;
	
	@Column(length=400, nullable=false)
	private String title;
	
	@Column(length=30, nullable=false) //nullable == not null
	private String writer;
	
	@Column(length=4000, nullable=false)
	private String content;

	@CreationTimestamp //현재 날짜와 시간 자동생성
	private Timestamp createdDate;
}