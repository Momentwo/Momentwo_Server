package cord.eoeo.momentwo.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /profiles/ 경로로 들어오는 요청을 로컬 파일 경로로 매핑
        registry.addResourceHandler("/profiles/**")
                .addResourceLocations("file:///C:/Users/qkqkt/Desktop/momentwo/profiles/");

        // /images/ 경로로 들어오는 요청을 로컬 파일 경로로 매핑
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/Users/qkqkt/Desktop/momentwo/images/");
    }
}
