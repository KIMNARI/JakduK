package com.jakduk.common;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author <a href="mailto:phjang1983@daum.net">Jang,Pyohwan</a>
 * @company  : http://jakduk.com
 * @date     : 2014. 7. 27.
 * @desc     :
 */
public class FilterModifiy implements BeanDefinitionRegistryPostProcessor {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		//logger.debug(Arrays.toString(registry.getBeanDefinitionNames()));
	}

}
