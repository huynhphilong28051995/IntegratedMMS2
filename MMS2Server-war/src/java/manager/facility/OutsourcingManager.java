/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.facility;

import javax.servlet.http.HttpServletRequest;
import mms.facility.entity.OutsourcingEntity;
import mms.facility.session.OutsourcingManagerSessionLocal;

/**
 *
 * @author linjiao_Zoe
 */
public class OutsourcingManager {
    OutsourcingManagerSessionLocal outsourcingManagerSessionLocal;
    
    public OutsourcingManager(OutsourcingManagerSessionLocal outsourcingManagerSessionLocal) {
        this.outsourcingManagerSessionLocal = outsourcingManagerSessionLocal;
    }
    
    public OutsourcingEntity editOutsourcingStatus(HttpServletRequest request){
        Long outsourcingId = (Long)request.getSession().getAttribute("outsourcingId");
        request.removeAttribute("outsourcingId");
        String status = request.getParameter("outsourcingStatus");
        return outsourcingManagerSessionLocal.updateStatus(outsourcingId, status);
    }
    
    public void deleteOutsourcing(HttpServletRequest request) {
        Long outsourcingId = Long.parseLong(request.getParameter("outsourcingId"));
        outsourcingManagerSessionLocal.deleteOutsourcing(outsourcingId);
    }
}
