package io.github.denkoch.mycosts.config;

import io.github.denkoch.mycosts.repository.CategoryRepository;
import io.github.denkoch.mycosts.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BusinessConfig {

    @Bean
    @Scope("singleton")
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryService(categoryRepository);
    }

    @Bean
    @Scope("singleton")
    public CategoryRepository categoryRepository() {
        return new CategoryRepository();
    }


}
