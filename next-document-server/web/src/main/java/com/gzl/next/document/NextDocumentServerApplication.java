package com.gzl.next.document;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Gaozhilai
 */
@SpringBootApplication
@MapperScan("com.gzl.next.document.mapper")
public class NextDocumentServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextDocumentServerApplication.class, args);
	}

}
