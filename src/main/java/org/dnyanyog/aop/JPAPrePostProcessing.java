package org.dnyanyog.aop;

import java.sql.Date;
import java.text.SimpleDateFormat;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class JPAPrePostProcessing {

  @Before("execution(* org.dnyanyog.repo.*.save(..))")
  public void beforeExecution(JoinPoint joinPoint) {
    long currentBeforeTimeMillis = System.currentTimeMillis();
    Date date = new Date(currentBeforeTimeMillis);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String formatedDate = sdf.format(date);
    System.out.println("Before saving: " + formatedDate);
  }

  @After("execution(* org.dnyanyog.repo.*.save(..))")
  public void afterExecution(JoinPoint joinPoint) {
    long currentAfterTimeMillis = System.currentTimeMillis();
    Date date = new Date(currentAfterTimeMillis);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String formatedDate = sdf.format(date);
    System.out.println("After saving: " + formatedDate);
  }
}
