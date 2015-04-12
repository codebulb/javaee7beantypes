package ch.codebulb.javaee7beans.cdi;

import ch.codebulb.javaee7beans.TransactionCheckedBean;
import ch.codebulb.javaee7beans.common.CommonReferencedBean;
import ch.codebulb.javaee7beans.ejb.EjbReferencedBean;
import ch.codebulb.javaee7beans.guice.GuiceReferencedBean;
import ch.codebulb.javaee7beans.jsf.JsfReferencedBean;

public class CdiBean extends TransactionCheckedBean {
    
    @javax.inject.Inject
    private CdiReferencedBean cdiReferencedBean;

    public CdiReferencedBean getCdiReferencedBean() {
        return cdiReferencedBean;
    }
    
    // FIXME Re-activate this annotation unless running on Wildfly 8.2.0
//    @javax.annotation.Resource
    private CommonReferencedBean commonReferencedBean;

    public CommonReferencedBean getCommonReferencedBean() {
        return commonReferencedBean;
    }
    
    @javax.inject.Inject
    private GuiceReferencedBean guiceReferencedBean;

    public GuiceReferencedBean getGuiceReferencedBean() {
        return guiceReferencedBean;
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
     * Creates a new instance of CdiBean
     */
    public CdiBean() {
    }
}
