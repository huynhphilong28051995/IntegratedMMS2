/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.timer.session;

import java.sql.Timestamp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PhiLong
 */
@Stateless
public class TimerSession implements TimerSessionLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void checkAndSendInvoice() {
        System.err.println("TESTING "+(new Timestamp(System.currentTimeMillis())).toString());
    }
}
