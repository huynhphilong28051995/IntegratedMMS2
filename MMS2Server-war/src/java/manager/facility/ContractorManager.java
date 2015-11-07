/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.facility;

import javax.servlet.http.HttpServletRequest;
import mms.facility.entity.ContractorEntity;
import mms.facility.session.ContractorManagerSessionLocal;

/**
 *
 * @author linjiao_Zoe
 */
public class ContractorManager {

    ContractorManagerSessionLocal contractorManagerSessionLocal;

    public ContractorManager(ContractorManagerSessionLocal contractorManagerSessionLocal) {
        this.contractorManagerSessionLocal = contractorManagerSessionLocal;
    }

    public ContractorEntity createNewContractor(HttpServletRequest request) {
        String contractorName = request.getParameter("contractorName");
        String companyName = request.getParameter("companyName");
        String serviceType = request.getParameter("serviceType");
        String contractorTel = request.getParameter("contractorTel");
        String contractorEmail = request.getParameter("contractorEmail");
        String mallName = (String) request.getSession().getAttribute("mallName");
        return contractorManagerSessionLocal.addContractor(contractorName, companyName, serviceType,
                contractorTel, contractorEmail, mallName);
    }

    public ContractorEntity getContractor(HttpServletRequest request) {
        Long contractorId = Long.parseLong(request.getParameter("contractorId"));
        return contractorManagerSessionLocal.getContractor(contractorId);
    }

    public ContractorEntity editContractor(HttpServletRequest request) {
        Long contractorId = (long) request.getSession().getAttribute("contractorId");
        request.removeAttribute("contractorId");
        String contractorName = request.getParameter("contractorName");
        String companyName = request.getParameter("companyName");
        String serviceType = request.getParameter("serviceType");
        String contractorTel = request.getParameter("contractorTel");
        String contractorEmail = request.getParameter("contractorEmail");
        return contractorManagerSessionLocal.editContractor(contractorId, contractorName, companyName, serviceType,
                contractorTel, contractorEmail);
    }

    public void deleteContractor(HttpServletRequest request) {
        Long contractorId = Long.parseLong(request.getParameter("contractorId"));
        contractorManagerSessionLocal.deleteContractor(contractorId);
    }
}
