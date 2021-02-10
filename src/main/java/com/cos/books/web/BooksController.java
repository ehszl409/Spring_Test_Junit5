package com.cos.books.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.cos.books.domain.Books;
import com.cos.books.domain.BooksRepository;
import com.cos.books.domain.CommonRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BooksController {

	private final BooksRepository booksRepository;

	// 무엇을 리턴할지 모른다면 <?>를 하면 편하다.
	@CrossOrigin
	@PostMapping("/books")
	public ResponseEntity<?> save(@RequestBody Books books) {
		//<>를 비워도 되는 이유
		//마치 List<Books> books = new ArrayList<>(); 와 같은 이치이다. 
		
		return new ResponseEntity<>(booksRepository.save(books), HttpStatus.CREATED); // 201
	}

	// 외부 자바스트립트 요청을 허락하는 것
	// 컨트롤러 진입 직전에 동작한다.
	// 나중에는 security 라이브러리를 적용한다. security는 CORS 정책을 가지고 있다.
	// security는 필터와 같이 동작하므로 @crossOrigin을 사용하는 방법을 달리 해야한다.
	@CrossOrigin 
	@GetMapping("/books")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(booksRepository.findAll(), HttpStatus.OK); // 201
	}
	
	@CrossOrigin
	@GetMapping("/books/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return new ResponseEntity<>(booksRepository.findById(id), HttpStatus.OK);
	}
	
//	@CrossOrigin
//	@PutMapping("/books/{id}")
//	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Books book){
//		return new ResponseEntity<>(booksService.수정하기(id, book), HttpStatus.OK);
//	}
	
	@CrossOrigin
	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		booksRepository.deleteById(id);
		CommonRespDto dto = new CommonRespDto();
		dto.setStatus("ok");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
}
