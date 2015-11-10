/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import mms.facility.entity.OutsourcingEntity;
import mms.tenant.entity.RentInvoiceEntity;
import mms.tenant.entity.RentRenewalRequestEntity;
import mms.tenant.session.TenantManagerBeanLocal;
import mms2.leasing.entity.TenantContractEntity;
import mms2.leasing.entity.TenantEntity;
import mms2.leasing.entity.UnitEntity;
import mms2.pos.entity.SaleEntity;

/**
 *
 * @author AdminNUS
 */
public class TenantManager {

    private TenantManagerBeanLocal tenantManagerBeanLocal;

    public TenantManager(TenantManagerBeanLocal tenantManagerBeanLocal) {
        this.tenantManagerBeanLocal = tenantManagerBeanLocal;
    }

    public boolean verifyLogin(HttpServletRequest request) {
        String email = request.getParameter("email");
        request.getSession().setAttribute("email", email);
        String password = request.getParameter("password");
        return tenantManagerBeanLocal.verifyLogin(email, password);
    }
    
    public TenantEntity getTenant(HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("email");
        return tenantManagerBeanLocal.getTenant(email);
    }
    
    public TenantContractEntity getContract(Long contractid) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManagerBeanLocal.getContract(contractid);
    }
    
    public ArrayList<OutsourcingEntity> getOutsourceRequest(Long tenantid) {
        return tenantManagerBeanLocal.getOutsourceRequest(tenantid);
    }
    
    public ArrayList<RentRenewalRequestEntity> getRenewalList(Long tenantid) {
        return tenantManagerBeanLocal.getRenewalList(tenantid);
    }
    
     public ArrayList<SaleEntity> getSaleList(Long tenantid) {
        return tenantManagerBeanLocal.getSaleList(tenantid);
    }
    
     public ArrayList<RentInvoiceEntity> getInvoices(Long tenantid) {
        return tenantManagerBeanLocal.getInvoices(tenantid);
    }
    
    public ArrayList<UnitEntity> getUnits(Long tenantid) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManagerBeanLocal.getUnits(tenantid);
    }
    
    public String submitOutsourcingRequest(HttpServletRequest request) {
        TenantEntity tenant = (TenantEntity) request.getSession().getAttribute("tenant");
        Long tenantID = tenant.getId();
        String tenantEmail = tenant.getEmail();
        String mallName = tenant.getMallName();
        ArrayList<UnitEntity> units = new ArrayList(tenant.getUnits());
        String unitNumber = "";
        for(int i=0; i<units.size(); i++){
            UnitEntity unit = units.get(i);
            unitNumber = unitNumber + unit.getLocationCode()+" ";
        }
        String contractorName = request.getParameter("contractorName");
        String description = request.getParameter("description");
        int numStaff = Integer.parseInt(request.getParameter("numStaff"));
        Timestamp outsourcingDate = new Timestamp(System.currentTimeMillis());
        Timestamp oServicingStartDate = Timestamp.valueOf(request.getParameter("oServicingStartDate")+" 00:00:00");
        Timestamp oServicingEndDate = Timestamp.valueOf(request.getParameter("oServicingEndDate")+" 00:00:00");
        String docFileLink = request.getParameter("docFileLink");
        return tenantManagerBeanLocal.submitOutsourcingRequest(tenantID, tenantEmail, mallName, unitNumber, contractorName, description, numStaff, outsourcingDate, oServicingStartDate, oServicingEndDate, docFileLink);
    }
    
    public String submitRentRenewalRequest(HttpServletRequest request) {
        TenantEntity tenant = (TenantEntity) request.getSession().getAttribute("tenant");
        Long tenantID = tenant.getId();
        String name = tenant.getName();
        String mall = tenant.getMallName();
        ArrayList<UnitEntity> units = new ArrayList(tenant.getUnits());
        String unitNumber = "";
        for(int i=0; i<units.size(); i++){
            UnitEntity unit = units.get(i);
            unitNumber = unitNumber + unit.getLocationCode()+" ";
        }
        String description = request.getParameter("description");
        return tenantManagerBeanLocal.submitRentRenewalRequest(tenantID, name, mall, unitNumber, description);
    }

}
