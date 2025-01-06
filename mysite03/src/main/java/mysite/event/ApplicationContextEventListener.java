package mysite.event;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import mysite.service.SiteService;
import mysite.vo.SiteVo;

public class ApplicationContextEventListener {
	@Autowired
	private ApplicationContext applicationContext;

	@EventListener({ContextRefreshedEvent.class})
	public void handlerContextRefreshedEvent() {
		SiteService siteService = applicationContext.getBean(SiteService.class);
		SiteVo siteVo = siteService.getSite(1L);
		
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("title", siteVo.getTitle());
		propertyValues.add("profile", siteVo.getProfile());
		propertyValues.add("welcome", siteVo.getWelcome());
		propertyValues.add("description", siteVo.getDescription());
		
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(SiteVo.class);
		beanDefinition.setPropertyValues(propertyValues);
		
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
		registry.registerBeanDefinition("site", beanDefinition);
	}
}
