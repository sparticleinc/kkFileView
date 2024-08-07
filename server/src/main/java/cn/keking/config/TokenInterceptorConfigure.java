package cn.keking.config;

import com.felo.userapi.starter.token.parser.starter.TokenParserComponent;
import com.felo.userapi.starter.token.parser.starter.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * <p>
 * SaTokenConfigure
 * </p>
 *
 * @author Karl
 * @since 2023/8/2 16:19
 */
@Configuration
public class TokenInterceptorConfigure implements WebMvcConfigurer {
    @Resource
    @Lazy
    private TokenParserComponent tokenParserComponent;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/web/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(tokenParserComponent))
                .addPathPatterns("/api/**");

                /*.excludePathPatterns(
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/swagger-ui.html/**")*/
                //.excludePathPatterns(
                //        "/",
                //        "/error",
                //        "/js/**",
                //        "/css/**",
                //        "/**/*.js",
                //        "/**/*.css",
                //        "/**/*.map",
                //        "/**/*.ico",
                //        "/images/*"
                //)
                //.excludePathPatterns(
                //        "/internal/**",
                //
                //        // TODO 临时开放
                //        "/files", "/files/*",
                //        "/preview", "/preview/*", "/onlinePreview",
                //        "/demo/*"
                //);
    }

}
