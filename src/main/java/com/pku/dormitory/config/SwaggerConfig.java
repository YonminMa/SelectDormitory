package com.pku.dormitory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Yonmin
 * @date 2022/11/24 18:45
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 配置swagger2相关的bean
     * 访问 ip:port/swagger-ui.html
     */
    @Bean
    public Docket creatRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 配置swagger扫描的api包
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 此处是Api文档显示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("演示项目API")
                .description("")
                .version("1.0")
                .build();
    }

}
