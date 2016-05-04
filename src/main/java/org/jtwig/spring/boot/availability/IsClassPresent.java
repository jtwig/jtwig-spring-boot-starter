package org.jtwig.spring.boot.availability;

import org.springframework.util.ClassUtils;

public class IsClassPresent {
    public boolean isPresent(ClassLoader loader, String qualifiedName) {
        return ClassUtils.isPresent(qualifiedName, loader);
    }
}
