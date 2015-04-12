package ch.codebulb.javaee7beans.ejb;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

@Stateless
 // we must use JSR 299 annotation Alternative, otherwise it's considered a CDI bean.
@Alternative
public class EjbReferencedBean {
}
