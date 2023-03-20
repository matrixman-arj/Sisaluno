package br.mil.eb.decex.sisaluno.config;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.google.common.cache.CacheBuilder;

import br.mil.eb.decex.sisaluno.controller.UsuariosController;
import br.mil.eb.decex.sisaluno.converter.AnoConverter;
import br.mil.eb.decex.sisaluno.converter.CidadeConverter;
import br.mil.eb.decex.sisaluno.converter.EstadoConverter;
import br.mil.eb.decex.sisaluno.converter.GrupoConverter;
import br.mil.eb.decex.sisaluno.converter.NporConverter;
import br.mil.eb.decex.sisaluno.converter.OrganizacaoMilitarConverter;
import br.mil.eb.decex.sisaluno.converter.PermissaoConverter;
import br.mil.eb.decex.sisaluno.converter.RegiaoConverter;
import br.mil.eb.decex.sisaluno.converter.RegiaoMilitarConverter;
import br.mil.eb.decex.sisaluno.converter.SituacaoConverter;
import br.mil.eb.decex.sisaluno.converter.UeteConverter;
import br.mil.eb.decex.sisaluno.converter.UsuarioConverter;
import br.mil.eb.decex.sisaluno.converter.UsuarioFilterConverter;
import br.mil.eb.decex.sisaluno.converter.UsuarioSistemaConverter;
import br.mil.eb.decex.sisaluno.thymeleaf.SisalunoDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackageClasses = { UsuariosController.class })
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableCaching
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		//resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public TemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
		
		engine.addDialect(new LayoutDialect());
		engine.addDialect(new SisalunoDialect());
		engine.addDialect(new DataAttributeDialect());
		engine.addDialect(new SpringSecurityDialect());
		return engine;
	}
	
	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {		
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		
		conversionService.addConverter(new OrganizacaoMilitarConverter());
		conversionService.addConverter(new NporConverter());
		conversionService.addConverter(new UeteConverter());
		conversionService.addConverter(new GrupoConverter());		
		conversionService.addConverter(new UsuarioSistemaConverter());
		conversionService.addConverter(new UsuarioConverter());		
		conversionService.addConverter(new PermissaoConverter());
		conversionService.addConverter(new EstadoConverter());
		conversionService.addConverter(new CidadeConverter());
		conversionService.addConverter(new RegiaoConverter());
		conversionService.addConverter(new RegiaoMilitarConverter());
		conversionService.addConverter(new UsuarioFilterConverter());
		conversionService.addConverter(new AnoConverter());
		conversionService.addConverter(new SituacaoConverter());
		
		

		// API de Datas do Java 8
		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		dateTimeFormatter.registerFormatters(conversionService);
		
		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
		conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);
		
		NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
		conversionService.addFormatterForFieldType(Integer.class, integerFormatter);
		
		return conversionService;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
				.maximumSize(3)
				.expireAfterAccess(20, TimeUnit.SECONDS);
		
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;
	}
	
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("classpath/messages");
		bundle.setDefaultEncoding("UTF-8"); //http://www.utf8-chartable.de/
		return bundle;
	}

}
