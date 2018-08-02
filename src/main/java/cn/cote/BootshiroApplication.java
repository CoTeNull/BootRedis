package cn.cote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.cote.dao")
public class BootshiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootshiroApplication.class, args);
	}
}
