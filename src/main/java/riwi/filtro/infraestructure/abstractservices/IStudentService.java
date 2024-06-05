package riwi.filtro.infraestructure.abstractservices;

import riwi.filtro.api.dtos.request.StudentReq;
import riwi.filtro.api.dtos.response.StudentResp;

public interface IStudentService extends CrudService<StudentReq, StudentResp, Long> {
    
}
