package com.revature.BootPie;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.revature.BootPie.models.Pie;
import com.revature.BootPie.services.PieService;




@SpringBootApplication
public class BootPieApplication {

	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(BootPieApplication.class, args);}

		@Bean
		public CommandLineRunner inspectorBean(ApplicationContext applicationContext) {
			return args -> {
					System.out.printf("Inspecting beans provided by Spring Boot in %s...", appName).println();

					String[] beans = applicationContext.getBeanDefinitionNames();

					Arrays.sort(beans);

					for(String bean: beans) {
						if(bean.contains("pie") || bean.contains("inspector"))
							System.out.println(bean);
					}
					System.out.println("Ending our inspection...");
			};
		}

		@Bean
		public CommandLineRunner piePickerBean(PieService pieService) {
			return args -> {
				Pie randomPie = pieService.getRandomPie();

				System.out.printf("The pie selected for you is %s with calories equal to %d with %d slices left!", randomPie.getPieName(),
				randomPie.getCalories(),
				randomPie.getSlicesAvailable()).println(); 
				
				
			};
		}
	}
