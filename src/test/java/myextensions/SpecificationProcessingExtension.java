package myextensions;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.listener.SpecificationProcessingEvent;
import org.concordion.api.listener.SpecificationProcessingListener;

/**
 * Trigger BeforeSpecification and AfterSpecification methods on test.
 * 
 * @author sumnera
 */
public class SpecificationProcessingExtension implements ConcordionExtension, SpecificationProcessingListener {
	private final Object testFixture;
	private static final boolean PARENT_FIRST = true;
	private static final boolean CHILD_FIRST = false;

	public SpecificationProcessingExtension(Object testFixture) {
		this.testFixture = testFixture;
	}

	@Override
	public void addTo(ConcordionExtender concordionExtender) {
		concordionExtender.withSpecificationProcessingListener(this);
    }

	@Override
	public void beforeProcessingSpecification(SpecificationProcessingEvent event) {
		triggerMethod(PARENT_FIRST, BeforeSpecification.class);
	}

	@Override
	public void afterProcessingSpecification(SpecificationProcessingEvent event) {
		triggerMethod(CHILD_FIRST, AfterSpecification.class);
	}
	
	private void triggerMethod(boolean parentFirst, Class<? extends Annotation> annotation) {
		List<Class<?>> classes = getClassHierarchy(parentFirst);
		List<String> called = new ArrayList<String>();
		
		for (Class<?> clazz : classes) {
			for (Method method : clazz.getDeclaredMethods()) {
				if (called.contains(method.getName())) {
					throw new RuntimeException("The method '" + method.getName() + "' cannot be declared in multiple classes when annotated with Before/AfterSpecification");
				}
				
				if (method.isAnnotationPresent(annotation)) {
					called.add(method.getName());
					
					try {
						method.invoke(testFixture);
					} catch (IllegalAccessException | IllegalArgumentException e) {
						throw new RuntimeException("Unable to invoke method '" + method.getName() + "' on " + testFixture.getClass().getName(), e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException("Exception occured in method '" + method.getName() + "' on " + testFixture.getClass().getName() + ": " + e.getCause().getMessage(), e.getCause());
					}
				}
			}
		}
	}
	/**
     * Returns the fixture class and all of its superclasses, excluding java.lang.Object,
     * ordered from the most super class to the fixture class.
     */
    private List<Class<?>> getClassHierarchy(boolean parentFirst) {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        Class<?> current = testFixture.getClass();
        while (current != null && current != Object.class) {
            classes.add(current);
            current = current.getSuperclass();
        }
        
        if (parentFirst) {
        	Collections.reverse(classes);
        }
        
        return classes;
    } 
}