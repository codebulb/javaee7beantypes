package ch.codebulb.javaee7beans.common;

import ch.codebulb.javaee7beans.TransactionCheckedBean;
import ch.codebulb.javaee7beans.cdi.CdiReferencedBean;
import ch.codebulb.javaee7beans.ejb.EjbReferencedBean;
import ch.codebulb.javaee7beans.guice.GuiceReferencedBean;
import ch.codebulb.javaee7beans.jsf.JsfReferencedBean;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.enterprise.inject.Alternative;


@ManagedBean("commonBean")
 // we must use JSR 299 annotation Alternative, otherwise it's considered a CDI bean.
@Alternative
public class CommonBean extends TransactionCheckedBean {
    
    @Resource
    private CommonReferencedBean commonReferencedBean;

    public CommonReferencedBean getCommonReferencedBean() {
        return commonReferencedBean;
    }
    
    @javax.inject.Inject
    private GuiceReferencedBean guiceReferencedBean;

    public GuiceReferencedBean getGuiceReferencedBean() {
        return guiceReferencedBean;
    }
    
    @javax.inject.Inject
    private CdiReferencedBean cdiReferencedBean;

    public CdiReferencedBean getCdiReferencedBean() {
        return cdiReferencedBean;
    }
    
    @javax.faces.bean.ManagedProperty("#{jsfReferencedBean}")
    private JsfReferencedBean jsfReferencedBean;

    public JsfReferencedBean getJsfReferencedBean() {
        return jsfReferencedBean;
    }
    
    // must provide a setter to inject ManagedProperty.
    // otherwise, 'ManagedBeanPreProcessingException: Unexpected error processing managed bean'
    // is thrown.
    public void setJsfReferencedBean(JsfReferencedBean jsfReferencedBean) {
        this.jsfReferencedBean = jsfReferencedBean;
    }
    
    @javax.ejb.EJB
    private EjbReferencedBean ejbReferencedBean;

    public EjbReferencedBean getEjbReferencedBean() {
        return ejbReferencedBean;
    }

    /**
     * Creates a new instance of CommonBean
     */
    public CommonBean() {
    }
}
