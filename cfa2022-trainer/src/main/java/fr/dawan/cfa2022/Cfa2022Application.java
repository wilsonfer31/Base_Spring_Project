package fr.dawan.cfa2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.dawan.cfa2022.interceptors.TokenInterceptor;

@SpringBootApplication
//on peut configurer d'autres packages pour qu'ils soient scannés
//@ComponentScan(basePackages = {"fr.dawan","com.example.cf1"})
public class Cfa2022Application {

	@Autowired
	private TokenInterceptor tokenInterceptor;
	
	public static void main(String[] args) {
		SpringApplication.run(Cfa2022Application.class, args);
	}
	
	@Bean
	public WebMvcConfigurer myMvcConfigurer() {
		return new WebMvcConfigurer() {
			//AJOUT D'UN FILTRE
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
			//	registry.addInterceptor(tokenInterceptor);
			}

			// CROS ORIGIN
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				  .allowedOrigins("*")
				  .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS");

			}
			
		};
	}

}
