package ch.codebulb.javaee7beans.common;

import javax.annotation.ManagedBean;
import javax.enterprise.inject.Alternative;

@ManagedBean("commonReferencedBean")
 // we must use JSR 299 annotation Alternative, otherwise it's considered a CDI bean.
@Alternative
public class CommonReferencedBean {
}
