package br.com.projectstages_mvc.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.projectstages_mvc.controller.IndexController;
import br.com.projectstages_mvc.dao.AmigosDao;
import br.com.projectstages_mvc.dao.CadastroDao;
import br.com.projectstages_mvc.dao.ChatDao;
import br.com.projectstages_mvc.dao.NotificacaoAmizadeDao;
import br.com.projectstages_mvc.dao.ParticipantesDao;
import br.com.projectstages_mvc.dao.ProjetoDao;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={IndexController.class,CadastroDao.class,ProjetoDao.class,AmigosDao.class,NotificacaoAmizadeDao.class,ParticipantesDao.class, ChatDao.class,FileSaver.class})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public	InternalResourceViewResolver internalResourceViewResolver()	{
		InternalResourceViewResolver resolver =	new	InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");	
		return	resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/Bootstrap/**").addResourceLocations("/Bootstrap/");
		registry.addResourceHandler("/CSS/**").addResourceLocations("/CSS/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
		registry.addResourceHandler("/JS/**").addResourceLocations("/JS/");
		registry.addResourceHandler("/uploads/**").addResourceLocations("/uploads/");
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}

	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
}
