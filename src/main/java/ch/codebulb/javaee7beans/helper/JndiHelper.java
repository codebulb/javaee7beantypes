package ch.codebulb.javaee7beans.helper;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Utility class for EJBs. There's a {@link #lookup(Class)} method which allows you to lookup the 
 * current instance of a given EJB class from the JNDI context. This utility class assumes that 
 * EJBs are deployed in the WAR as you would do in Java EE 6 Web Profile. For EARs, you'd need to
 * alter the <code>EJB_CONTEXT</code> to add the EJB module name or to add another lookup() method.
 * 
 * based on: http://balusc.blogspot.ch/2011/09/communication-in-jsf-20.html#GettingAnEJBInFacesConverterAndFacesValidator
 */
public final class JndiHelper<T> {

    // Constants ----------------------------------------------------------------------------------

    private static final String EJB_CONTEXT;
    private static final BeanManager BEAN_MANAGER;

    static {
        try {
            EJB_CONTEXT = "java:app/" + new InitialContext().lookup("java:app/AppName") + "/";
            BEAN_MANAGER = CDI.current().getBeanManager();
        } catch (NamingException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    // Constructors -------------------------------------------------------------------------------

    private JndiHelper() {
        // Utility class, so hide default constructor.
    }

    // Helpers ------------------------------------------------------------------------------------

    /**
     * Lookup the current instance of the given EJB class from the JNDI context. If the given class
     * implements a local or remote interface, you must assign the return type to that interface to
     * prevent ClassCastException. No-interface EJB lookups can just be assigned to own type. E.g.
     * <li><code>IfaceEJB ifaceEJB = EJB.lookup(ConcreteEJB.class);</code>
     * <li><code>NoIfaceEJB noIfaceEJB = EJB.lookup(NoIfaceEJB.class);</code>
     * @param <T> The EJB type.
     * @param ejbClass The EJB class.
     * @return The instance of the given EJB class from the JNDI context.
     * @throws IllegalArgumentException If the given EJB class cannot be found in the JNDI context.
     */
    public static <T> T lookupWithJNDI(String ejbClassName) throws IllegalArgumentException {
        String jndiName = EJB_CONTEXT + ejbClassName;

        try {
            // Do not use ejbClass.cast(). It will fail on local/remote interfaces.
            return (T) new InitialContext().lookup(jndiName);
        } catch (NamingException e) {
            throw new IllegalArgumentException(
                String.format("Cannot find EJB class %s in JNDI %s", ejbClassName, jndiName), e);
        }
    }
    
    public static <T> T lookupWithBeanManager(Class beanClass) throws IllegalArgumentException {
        try {
            Bean<T> bean = (Bean<T>)BEAN_MANAGER.getBeans(beanClass).toArray()[0];
            CreationalContext<T> ctx = BEAN_MANAGER.createCreationalContext(bean);
            return (T) BEAN_MANAGER.getReference(bean, beanClass, ctx);
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException(
                String.format("Cannot find bean class %s in BeanManager", beanClass), ex);
        }
    }
    
    public static <T> T lookupWithBeanManager(String beanName) throws IllegalArgumentException {
        try {
            return (T) BEAN_MANAGER.getBeans(beanName).toArray()[0];
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException(
                String.format("Cannot find bean named %s in BeanManager", beanName), ex);
        }
    }

}