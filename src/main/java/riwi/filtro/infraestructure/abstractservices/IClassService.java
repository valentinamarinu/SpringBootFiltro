package riwi.filtro.infraestructure.abstractservices;

import riwi.filtro.api.dtos.request.ClassReq;
import riwi.filtro.api.dtos.response.ClassGetResp;

public interface IClassService extends CrudService<ClassReq, ClassGetResp, Long> {
    
}
