package ch.codebulb.javaee7beans.ejb;

import ch.codebulb.javaee7beans.cdi.CdiReferencedBean;
import ch.codebulb.javaee7beans.common.CommonReferencedBean;
import ch.codebulb.javaee7beans.guice.GuiceReferencedBean;
import ch.codebulb.javaee7beans.jsf.JsfReferencedBean;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.transaction.Status;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class EjbBean {
    @EJB
    private EjbReferencedBean ejbReferencedBean;

    public EjbReferencedBean getEjbReferencedBean() {
        return ejbReferencedBean;
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
    
    @Resource
    private TransactionSynchronizationRegistry tsr;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean isTransactional() {
        return tsr.getTransactionStatus() == Status.STATUS_ACTIVE;
    }
}
