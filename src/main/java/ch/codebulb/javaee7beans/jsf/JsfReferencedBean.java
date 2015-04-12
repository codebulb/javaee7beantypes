package ch.codebulb.javaee7beans.jsf;

import javax.enterprise.inject.Alternative;
import javax.faces.bean.ManagedBean;

@ManagedBean
 // we must use JSR 299 annotation Alternative, otherwise it's considered a CDI bean.
@Alternative
public class JsfReferencedBean {
}
