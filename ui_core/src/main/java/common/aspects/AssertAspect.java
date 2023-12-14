package common.aspects;

import common.utils.AssertMsg;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.testng.Assert;

import java.util.Objects;
import java.util.UUID;

import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;
/* Данный класс для использования Assert-ов. Используется кастомный AssertMsg
* Для того чтобы не получать AssertionError при debugging mode, при валидации ассертов, когда параметром передается String.
* в остальных случаях используется библиотека ассертов.
* Данный код также помогает перевызывать ассерты в случае их не срабатывания.
* */
@Slf4j
@Aspect
public class AssertAspect {
    private static AllureLifecycle lifecycle;

    @Pointcut("call(public void org.testng.Assert.*(..)) && !within(org.testng.Assert)")
    public void asserts() {}

    @Around("asserts()")
    public void step(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Object[] newArgs = processArgs(joinPoint);
        final String uuid = UUID.randomUUID().toString();
        if (!(newArgs == null)) {
            final StepResult result = new StepResult().withName(String.valueOf(newArgs[newArgs.length - 1]));
            startStep(uuid, result);
            try {
                joinPoint.proceed(newArgs);
                updateStepPassed(uuid);
               log.info("Passed: " + newArgs[newArgs.length - 1].toString());
            } catch (Throwable e) {
               log.error("Failed: " + newArgs[newArgs.length - 1].toString());
                updateStepFail(uuid, e);
                throw e;
            } finally {
                stopStep(uuid);
            }
        }
    }

    private Object[] processArgs(ProceedingJoinPoint pjp) {
        final MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        final Class[] paramTypes = methodSignature.getParameterTypes();
        Object[] newArgs = pjp.getArgs();
        final String prefix = AssertMsg.ASSERT_MESSAGE_PREFIX;
        switch (methodSignature.getMethod().getName()) {
            case "assertNotEquals":
                if (pjp.getArgs().length == 3 && paramTypes[2].equals(String.class))
                    newArgs[newArgs.length - 1] = prefix + AssertMsg.notEquals(pjp.getArgs()[0], pjp.getArgs()[1], pjp.getArgs()[2]);
                if (pjp.getArgs().length == 2) {
                    Assert.assertNotEquals(newArgs[0], newArgs[1], null);
                    return null;
                }
                break;
            case "assertEquals":
                if (pjp.getArgs().length == 3 && paramTypes[2].equals(String.class))
                    newArgs[newArgs.length - 1] = prefix + AssertMsg.objectsEqual(pjp.getArgs()[0], pjp.getArgs()[1], pjp.getArgs()[2]);
                if (pjp.getArgs().length == 2) {
                    Assert.assertEquals(newArgs[0], newArgs[1], null);
                    return null;
                }
                break;
            case "assertTrue":
                if (pjp.getArgs().length == 2)
                    newArgs[newArgs.length - 1] = prefix + pjp.getArgs()[1];
                if (pjp.getArgs().length == 1) {
                    Assert.assertTrue((boolean) newArgs[0], AssertMsg.assertTrue(pjp.getArgs()[0]));
                    return null;
                }
                break;
            case "assertFalse":
                if (pjp.getArgs().length == 2)
                    newArgs[newArgs.length - 1] = prefix + pjp.getArgs()[1];
                if (pjp.getArgs().length == 1) {
                    Assert.assertFalse((boolean) newArgs[0], AssertMsg.assertFalse(pjp.getArgs()[0]));
                    return null;
                }
                break;
            case "fail":
                if (pjp.getArgs().length == 1)
                    newArgs[newArgs.length - 1] = prefix + pjp.getArgs()[0];
                break;
        }
        return newArgs;
    }

    private void startStep(String uuid, StepResult result) {
        getLifecycle().startStep(uuid, result);
    }

    private void stopStep(String uuid) {
        getLifecycle().stopStep(uuid);
    }

    private void updateStepPassed(String uuid) {
        getLifecycle().updateStep(uuid, s -> s.withStatus(Status.PASSED));
    }

    private void updateStepFail(String uuid, Throwable e) {
        getLifecycle().updateStep(uuid, s -> s
                .withStatus(getStatus(e).orElse(Status.BROKEN))
                .withStatusDetails(getStatusDetails(e).orElse(null)));
    }

    private static AllureLifecycle getLifecycle() {
        if (Objects.isNull(lifecycle)) {
            lifecycle = Allure.getLifecycle();
        }
        return lifecycle;
    }
}
