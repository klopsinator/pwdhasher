package pwdhasher.events.util;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Andreas Werner
 */
@Component
public class EventBusSubscriberProcessor implements BeanPostProcessor {

    @Autowired
    private EventBus eventBus;

    @Override
    public Object postProcessAfterInitialization(Object beanObject, String beanObjectName) throws BeansException {
        return beanObject;
    }

    @Override
    public Object postProcessBeforeInitialization(Object beanObject, String beanObjectName) throws BeansException {

        Method[] methods = beanObject.getClass().getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();

            for (Annotation methodAnnotation : annotations) {
                if (methodAnnotation.annotationType().equals(Subscribe.class)) {
                    eventBus.register(beanObject);
                    return beanObject;
                }
            }
        }

        return beanObject;
    }
}
