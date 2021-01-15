package ua.cn.stu.tpps.buyfly.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import ua.cn.stu.tpps.buyfly.util.ResourceMessage;

import javax.persistence.PersistenceException;

import static ua.cn.stu.tpps.buyfly.values.ResourceStringsDictionary.DAO_EXCEPTION_THROWN;

@Aspect
public class DaoExceptionHandlerAspect {

    private static final Logger logger = LogManager.getLogger("BuyFly");

    private static final String BUNDLE_EXCEPTION_MESSAGES = "errormessages";

    @Around("execution(* ua.cn.stu.tpps.buyfly.dao.implJpa..*.*(..))")
    public Object exceptionHandlerWithReturnType(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (PersistenceException ex) {
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getSignature().getClass().getName();
            Object[] arguments = joinPoint.getArgs();
            String argumentsString = "";

            for (Object arg : arguments) {
                argumentsString += ", " + arg;
            }

            logger.error(ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, DAO_EXCEPTION_THROWN, className, methodName, argumentsString), ex);

            throw ex;
        }
    }

}
