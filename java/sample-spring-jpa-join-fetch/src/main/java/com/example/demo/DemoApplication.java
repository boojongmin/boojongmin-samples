package com.example.demo;

import com.example.demo.domain.AEntity;
import com.example.demo.domain.ARepository;
import com.example.demo.domain.BEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
@Transactional
public class DemoApplication {
	private final AService aService;

	@Bean
	CommandLineRunner run() {
		return args -> {

			aService.init();
			aService.hello();

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AService {
	private final ARepository aRepository;
	private final EntityManager em;

	public void hello() {
		var list = aRepository.hello();
		list.forEach(x -> x.getBList());
		System.out.println(list);
	}

	@Transactional
	public void init() {
		AEntity a = AEntity.builder().build();
		var b1 = BEntity.builder().del(false).a(a).build();
		var b2 = BEntity.builder().del(true).a(a).build();
		var b3 = BEntity.builder().del(false).a(a).build();
		a.getBList().add(b1);
		a.getBList().add(b2);
		a.getBList().add(b3);
		aRepository.save(a);

	}
}

