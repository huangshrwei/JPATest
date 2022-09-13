package com.example.aop;

import org.springframework.beans.factory.config.BeanPostProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

    // simply return the instantiated bean as-is
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
    	log.info("Bean BeforeInitialization.....");
        return bean; // we could potentially return any object reference here...
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
    	log.info("Bean '" + beanName + "' created : " + bean.toString());
        return bean;
    }
}