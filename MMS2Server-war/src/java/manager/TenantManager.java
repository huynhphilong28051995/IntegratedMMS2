/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import mms.leasing.entity.TenantContractEntity;
import mms.leasing.entity.UnitEntity;
import mms.leasing.entity.TenantEntity;
import mms2.leasing.session.TenantManagerSessionLocal;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PhiLong
 */
public class TenantManager {
    TenantManagerSessionLocal tenantManagerSessionLocal;

    public TenantManager(TenantManagerSessionLocal tenantManagerSessionLocal) {
        this.tenantManagerSessionLocal = tenantManagerSessionLocal;
    }
    public TenantEntity addTenant(HttpServletRequest request){
        String mallName= (String)request.getSession().getAttribute("mallName");
        
        String tenantName= request.getParameter("tenantName");
        String businessType = request.getParameter("businessType");
        String description = request.getParameter("tenantDescription");
        String address = request.getParameter("tenantAddress");
        String email  = request.getParameter("tenantEmail");
        String tel= request.getParameter("tenantTel");
        return tenantManagerSessionLocal.createTenant(mallName,tenantName, 
                businessType,description,address,email,tel);
    }
    public void linkTenantToUnit(TenantEntity tenant, UnitEntity unit){
        tenantManagerSessionLocal.linkTenantToUnit(tenant, unit);
    }
    public void linkTenantToContract(TenantEntity tenant, TenantContractEntity contract){
        tenantManagerSessionLocal.linkTenantToContract(tenant, contract);
    }
    public ArrayList<TenantEntity> getAllTenants(HttpServletRequest request){
        String mallName = (String)request.getSession().getAttribute("mallName");
       return tenantManagerSessionLocal.getAllTenants(mallName); 
    }
    public TenantEntity getTenantById(HttpServletRequest request){
        Long tenantId  = Long.parseLong(request.getParameter("tenantId"));
        return tenantManagerSessionLocal.getTenantById(tenantId);
    }
    public void createTenantAndContractForApprovedLongTermApplication(Long applicationId){
        tenantManagerSessionLocal.createTenantAndContractForApprovedLongTermApplication(applicationId);
    }
}
