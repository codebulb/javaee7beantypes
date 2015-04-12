package ch.codebulb.javaee7beans;

import javax.annotation.Resource;
import javax.enterprise.inject.Alternative;
import javax.transaction.Status;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

 // we must use JSR 299 annotation Alternative, otherwise it's considered a CDI bean.
@Alternative
public abstract class TransactionCheckedBean {
    @Resource
    private TransactionSynchronizationRegistry tsr;
    
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean isTransactional() {
        return tsr.getTransactionStatus() == Status.STATUS_ACTIVE;
    }
}
