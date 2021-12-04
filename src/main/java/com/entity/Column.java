package com.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Jay Patel
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	String name();

}
