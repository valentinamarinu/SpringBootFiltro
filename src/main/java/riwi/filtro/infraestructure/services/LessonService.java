package riwi.filtro.infraestructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import riwi.filtro.api.dtos.request.LessonReq;
import riwi.filtro.api.dtos.request.MultimediaReq;
import riwi.filtro.api.dtos.response.LessonResp;
import riwi.filtro.api.dtos.response.MultimediaBasicResp;
import riwi.filtro.domain.entities.ClassEntity;
import riwi.filtro.domain.entities.LessonEntity;
import riwi.filtro.domain.entities.MultimediaEntity;
import riwi.filtro.domain.repositories.ClassRepository;
import riwi.filtro.domain.repositories.LessonRepository;
import riwi.filtro.domain.repositories.MultimediaRepository;
import riwi.filtro.utils.exceptions.BadRequestException;
import riwi.filtro.utils.messages.ErrorMessages;

@Service
@AllArgsConstructor
public class LessonService {
    @Autowired
    private final LessonRepository repository;

    @Autowired
    private final ClassRepository classRepository;

    @Autowired
    private final MultimediaRepository multimediaRepository;

    public LessonResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    public LessonResp create(LessonReq request) {
        LessonEntity lesson = requestToEntity(request);
        this.repository.save(lesson);
    
        if(request.getMultimedias() != null) {
            request.getMultimedias().stream().forEach(multimedia -> {
                MultimediaEntity multimediaNew = 
                MultimediaEntity.builder()
            .type(multimedia.getType())
            .url(multimedia.getUrl())
            .active(multimedia.getActive())
            .lesson(lesson)
            .build();
    
            this.multimediaRepository.save(multimediaNew);
            });
        } else {
            lesson.setMultimedias(new ArrayList<>());
        }
    
        return this.entityToResponse(lesson);
    }

    public LessonResp disable(Long id) {
        LessonEntity lesson = this.find(id);

        lesson.setActive(false);

        return this.entityToResponse(this.repository.save(lesson));
    }

    private LessonResp entityToResponse(LessonEntity entity) {
        List<MultimediaBasicResp> multimedias = 
            entity.getMultimedias()
            .stream()
            .map(this::multimediaEntityToResponse)
            .collect(Collectors.toList());        

        return LessonResp.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .created_at(entity.getCreated_at())
                .active(entity.getActive())
                .multimedias(multimedias)
                .build();
    }

    private MultimediaBasicResp multimediaEntityToResponse(MultimediaEntity entity) {
        return MultimediaBasicResp.builder()
                .id(entity.getId())
                .type(entity.getType())
                .url(entity.getUrl())
                .created_at(entity.getCreated_at())
                .active(entity.getActive())
                .build();
    }

    private LessonEntity requestToEntity(LessonReq request) {
        List<MultimediaEntity> multimedias = 
            request.getMultimedias()
            .stream()
            .map(this::multimediaRequestToEntity)
            .collect(Collectors.toList()); 

        return LessonEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .active(request.getActive())
                .clase(findClass(request.getClass_id()))
                .multimedias(multimedias)
                .build();
    }

    private MultimediaEntity multimediaRequestToEntity(MultimediaReq request) {
        return MultimediaEntity.builder()
                .type(request.getType())
                .url(request.getUrl())
                .active(request.getActive())
                .lesson(find(request.getLesson_id()))
                .build();
    }

    private LessonEntity find(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Lesson")));
    }

    private ClassEntity findClass(Long id) {
        return this.classRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Class")));
    }
}
