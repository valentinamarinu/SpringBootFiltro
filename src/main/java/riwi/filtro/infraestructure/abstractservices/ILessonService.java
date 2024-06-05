package riwi.filtro.infraestructure.abstractservices;

import riwi.filtro.api.dtos.request.LessonReq;
import riwi.filtro.api.dtos.response.LessonResp;

public interface ILessonService extends CrudService<LessonReq, LessonResp, Long> {
    
}
