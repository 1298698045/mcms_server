
package net.mingsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;


@SpringBootApplication(scanBasePackages = {"net.mingsoft"})
@MapperScan(basePackages={"**.dao","com.baomidou.**.mapper"})
@ServletComponentScan(basePackages = {"net.mingsoft"})
public class MSApplication {
	public static void main(String[] args) {
		SpringApplication.run(MSApplication.class, args);
	}

}