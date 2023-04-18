package com.admin4j.framework.lock.aspect;


import com.admin4j.framework.lock.LockInfo;
import com.admin4j.framework.lock.annotation.DistributedLock;
import com.admin4j.framework.lock.annotation.Idempotent;
import com.admin4j.framework.lock.exception.IdempotentException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;

/**
 * 分布式锁解析器
 * https://github.com/redisson/redisson/wiki/8.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%92%8C%E5%90%8C%E6%AD%A5%E5%99%A8
 *
 * @author andanyang
 * @since 2020/12/22 11:06
 */
@Slf4j
@Aspect
public class IdempotentAspect extends AbstractDLockHandler {

    /**
     * 切面环绕通知
     *
     * @param joinPoint  ProceedingJoinPoint
     * @param idempotent idempotent
     * @return Object
     */
    @Around("@annotation(idempotent)")
    public Object around(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DistributedLock distributedLock = AnnotatedElementUtils.getMergedAnnotation(method, DistributedLock.class);

        //获取锁信息
        LockInfo<Object> lockInfo = new LockInfo<>();
        lockInfo.setLockModel(idempotent.lockModel());
        lockInfo.setLockKey(getDistributedLockKey(joinPoint, distributedLock));
        lockInfo.setTryLock(idempotent.tryLock());
        lockInfo.setLeaseTime(idempotent.leaseTime());
        lockInfo.setWaitTimeOutSeconds(idempotent.waitTimeOutSeconds());
        lockInfo.setTenant(idempotent.tenant());
        lockInfo.setUser(true);
        lockInfo.setExecutor(idempotent.executor());

        return super.around(joinPoint, lockInfo);
    }

    @Override
    protected void lockFailure() {
        throw new IdempotentException("failed to acquire Idempotent");
    }
}
