package ch.codebulb.javaee7beans.helper;

import ch.codebulb.javaee7beans.cdi.CdiBean;
import ch.codebulb.javaee7beans.common.CommonBean;
import ch.codebulb.javaee7beans.ejb.EjbBean;
import ch.codebulb.javaee7beans.guice.GuiceBean;
import ch.codebulb.javaee7beans.jsf.JsfBean;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class JndiHelperBean {

    public Object getBean(String ejbClassName) {
       try {
            return JndiHelper.lookupWithJNDI(ejbClassName);
        } catch (IllegalArgumentException ex) {
            return null;
        } 
    }
    
    public Object getBeanWithBeanManager(String ejbClassName) {
        try {
            switch (ejbClassName) {
                case "commonBean":
                    return JndiHelper.lookupWithBeanManager(CommonBean.class);
                case "guiceBean":
                    return JndiHelper.lookupWithBeanManager(GuiceBean.class);
                case "cdiBean":
                    return JndiHelper.lookupWithBeanManager(CdiBean.class);
                case "jsfBean":
                    return JndiHelper.lookupWithBeanManager(JsfBean.class);
                case "ejbBean":
                    return JndiHelper.lookupWithBeanManager(EjbBean.class);
            }
        } catch (IllegalArgumentException ex) {
            return null;
        }
        
        throw new IllegalArgumentException(ejbClassName + " is not a valid bean class name.");
    }
    
    public Object getNamedBeanWithBeanManager(String ejbClassName) {
       try {
            return JndiHelper.lookupWithBeanManager(ejbClassName);
        } catch (IllegalArgumentException ex) {
            return null;
        } 
    }
}
