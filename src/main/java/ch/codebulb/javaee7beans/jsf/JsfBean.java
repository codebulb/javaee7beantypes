package ch.codebulb.javaee7beans.jsf;

import ch.codebulb.javaee7beans.TransactionCheckedBean;
import ch.codebulb.javaee7beans.cdi.CdiReferencedBean;
import ch.codebulb.javaee7beans.common.CommonReferencedBean;
import ch.codebulb.javaee7beans.ejb.EjbReferencedBean;
import ch.codebulb.javaee7beans.guice.GuiceReferencedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
 // we must use JSR 299 annotation Alternative, otherwise it's considered a CDI bean.
@javax.enterprise.inject.Alternative
public class JsfBean extends TransactionCheckedBean {
    
    @ManagedProperty("#{jsfReferencedBean}")
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
    
    @javax.annotation.Resource
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
    
    @javax.ejb.EJB
    private EjbReferencedBean ejbReferencedBean;

    public EjbReferencedBean getEjbReferencedBean() {
        return ejbReferencedBean;
    }

    /**
     * Creates a new instance of JsfBean
     */
    public JsfBean() {
    }
}
