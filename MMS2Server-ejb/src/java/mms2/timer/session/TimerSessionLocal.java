/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.timer.session;

import javax.ejb.Local;

/**
 *
 * @author PhiLong
 */
@Local
public interface TimerSessionLocal {

    public void checkAndSendInvoice();
    
}
