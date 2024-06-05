package riwi.filtro.infraestructure.abstractservices;

import riwi.filtro.api.dtos.request.StudentReq;
import riwi.filtro.api.dtos.response.StudentGetResp;

public interface IStudentService extends CrudService<StudentReq, StudentGetResp, Long> {
    
}
