package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class CommonPointcuts {
    	
	private static final Logger logger = LoggerFactory.getLogger(CommonPointcuts.class);
			
	//Config
    @Pointcut("execution(* com.example.config..*(..))")
    public void pointcutConfig() {
    }

    @Before("pointcutConfig()")
    public void beforeConfig(JoinPoint joinPoint) {    	
    	logger.info("config start");
    }

    @After("pointcutConfig()")
    public void afterConfig(JoinPoint joinPoint) {
    	logger.info("config end");
    }
    
    //Dao
    @Pointcut("execution(* com.example.dao..*(..))")
    public void pointcutDao() {
    }

    @Before("pointcutDao()")
    public void beforeDao(JoinPoint joinPoint) {
    	logger.info("Dao start");
    }

    @After("pointcutDao()")
    public void afterDao(JoinPoint joinPoint) {
    	logger.info("Dao end");
    }    
    
	//Entity
    @Pointcut("execution(* com.example.entity..*(..))")
    public void pointcutEntity() {	
    }
	
    @Before("pointcutEntity()")
    public void beforeEntity(JoinPoint joinPoint) {
    	logger.info("Entity start");
    }

    @After("pointcutEntity()")
    public void afterEntity(JoinPoint joinPoint) {
    	logger.info("Entity end");
    }   	
/*	
    @AfterReturning(pointcut="execution(* com.example.entity.UserProfile.*(..))", returning="retVal")
    public void afterReturningAdvice(JoinPoint jp, Object retVal){
    	log.info("[afterReturningAdvice] Method Signature: "  + jp.getSignature());  
    	log.info("[afterReturningAdvice] Returning: " + retVal.toString() );
    }
*/    
    
    
    //userDetailRepository
    @Pointcut("execution(* com.example.dao.UserDetailRepository..*(..))")
    public void pointcutDetailRepository() {
    }

    @Before("pointcutDetailRepository()")
    public void beforeDetailRepository(JoinPoint joinPoint) {
    	logger.info("DetailRepository start");
    }

    @After("pointcutDetailRepository()")
    public void afterDetailRepository(JoinPoint joinPoint) {
    	logger.info("DetailRepository end");
    }        
    
    //Service
    @Pointcut("execution(* com.example.service..*(..))")
    public void pointcutService() {
    }    
    
    @Before("pointcutService()")
    public void beforeService(JoinPoint joinPoint) {
    	logger.info("service start");
    }

    @After("pointcutService()")
    public void afterService(JoinPoint joinPoint) {
    	logger.info("service end");
    }    
    
    //Controller
    @Pointcut("execution(* com.example.controller..*(..))")
    public void pointcutController() {
    }

    @Before("pointcutController()")
    public void beforeController(JoinPoint joinPoint) {
    	//logger.info(System.getProperty("user.dir"));    	
    	logger.info("Controller start");
    }

    @After("pointcutController()")
    public void afterController(JoinPoint joinPoint) {
    	logger.info("Controller end");
    } 
	

    /**
     * A join point is in the service layer if the method is defined
     * in a type in the com.xyz.myapp.service package or any sub-package
     * under that.
     */
    @Pointcut("within(com.example.service..*)")
    public void inServiceLayer() {
    	logger.info("inServiceLayer");
    }

    /**
     * A join point is in the data access layer if the method is defined
     * in a type in the com.xyz.myapp.dao package or any sub-package
     * under that.
     */
    @Pointcut("within(com.example.dao..*)")
    public void inDataAccessLayer() {
    	logger.info("inDataAccessLayer");
    }

    /**
     * A business service is the execution of any method defined on a service
     * interface. This definition assumes that interfaces are placed in the
     * "service" package, and that implementation types are in sub-packages.
     *
     * If you group service interfaces by functional area (for example,
     * in packages com.xyz.myapp.abc.service and com.xyz.myapp.def.service) then
     * the pointcut expression "execution(* com.xyz.myapp..service.*.*(..))"
     * could be used instead.
     *
     * Alternatively, you can write the expression using the 'bean'
     * PCD, like so "bean(*Service)". (This assumes that you have
     * named your Spring service beans in a consistent fashion.)
     */
    @Pointcut("execution(* com.example..service.*.*(..))")
    public void businessService() {
    	logger.info("businessService");
    }

    /**
     * A data access operation is the execution of any method defined on a
     * dao interface. This definition assumes that interfaces are placed in the
     * "dao" package, and that implementation types are in sub-packages.
     */
    @Pointcut("execution(* com.example.dao.*.*(..))")
    public void dataAccessOperation() {
    	logger.info("dataAccessOperation");
    }    
    
    
}
