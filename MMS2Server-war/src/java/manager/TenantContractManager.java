/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import mms.leasing.entity.TenantContractEntity;
import mms2.leasing.session.TenantContractManagerSessionLocal;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PhiLong
 */
public class TenantContractManager {
    TenantContractManagerSessionLocal tenantContractManagerLocal;

    public TenantContractManager(TenantContractManagerSessionLocal tenantContractManagerLocal) {
        this.tenantContractManagerLocal = tenantContractManagerLocal;
    }
    
    public TenantContractEntity addTenantContract(String startDate, String endDate, String depositString, String rateString){
        Timestamp startDateString  =Timestamp.valueOf(startDate+" 00:00:00");
        Timestamp endDateString = Timestamp.valueOf(endDate+" 00:00:00");
        Double deposit = Double.valueOf(depositString);
        Double rate = Double.valueOf(rateString);
        return tenantContractManagerLocal.createTenantContract(startDateString, endDateString, deposit, rate);
    }
}
