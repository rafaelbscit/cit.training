package br.com.cit.contacts.repository.util;

import br.com.cit.contacts.repository.exception.RepositoryException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public final class MergeBeanUtils {

    private MergeBeanUtils() {
    }

    public static <M> void merge(M to, M from) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(to.getClass());

            // Iterate over all the attributes
            for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                processDescriptor(to, from, descriptor);
            }
        } catch (Exception e) {
            throw new RepositoryException("Error to merge entities.", e);
        }
    }

    private static <M> void processDescriptor(M to, M from, PropertyDescriptor descriptor) throws IllegalAccessException, InvocationTargetException {
        // Only copy writable attributes
        if (descriptor.getWriteMethod() != null) {
            Object newValue = descriptor.getReadMethod().invoke(from);

            // Only copy values values where the from values is not null
            if (newValue != null) {
                descriptor.getWriteMethod().invoke(to, newValue);
            }

        }
    }
}
