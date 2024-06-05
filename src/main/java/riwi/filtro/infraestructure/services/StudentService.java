package riwi.filtro.infraestructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import riwi.filtro.api.dtos.request.StudentReq;
import riwi.filtro.api.dtos.response.ClassToStudentResp;
import riwi.filtro.api.dtos.response.LessonToClassResp;
import riwi.filtro.api.dtos.response.MultimediaBasicResp;
import riwi.filtro.api.dtos.response.StudentBasicResp;
import riwi.filtro.api.dtos.response.StudentGetResp;
import riwi.filtro.domain.entities.ClassEntity;
import riwi.filtro.domain.entities.LessonEntity;
import riwi.filtro.domain.entities.MultimediaEntity;
import riwi.filtro.domain.entities.StudentEntity;
import riwi.filtro.domain.repositories.ClassRepository;
import riwi.filtro.domain.repositories.StudentRepository;
import riwi.filtro.utils.exceptions.BadRequestException;
import riwi.filtro.utils.messages.ErrorMessages;

@Service
@AllArgsConstructor
public class StudentService {
    @Autowired
    private final StudentRepository repository;

    @Autowired
    private final ClassRepository classRepository;

    public Page<StudentBasicResp> getAll(int page, int size, String name) {
        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.repository.findByNameAndActive(name, pagination, true).map(student -> this.entityBasicToResponse(student));
    }

    public StudentGetResp get(Long id) {
        return this.entityGetToResponse(this.find(id));
    }

    public StudentBasicResp create(StudentReq request) {
        StudentEntity student = requestToEntity(request);

        student.setClase(findClass(request.getClass_id()));

        return this.entityBasicToResponse(this.repository.save(student));
    }

    public StudentBasicResp update(StudentReq request, Long id) {
        StudentEntity student = this.find(id);

        StudentEntity studentUpdate = this.requestToEntity(request);

        studentUpdate.setId(id);
        studentUpdate.setCreated_at(student.getCreated_at());
        student.setActive(false);

        return this.entityBasicToResponse(this.repository.save(studentUpdate));
    }

    public StudentBasicResp disable(Long id) {
        StudentEntity student = this.find(id);

    
        student.setActive(false);

        return this.entityBasicToResponse(this.repository.save(student));
    }

    private StudentBasicResp entityBasicToResponse(StudentEntity entity) {
        return StudentBasicResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .created_at(entity.getCreated_at())
                .active(entity.getActive())
                .build();
    }

    private StudentGetResp entityGetToResponse(StudentEntity entity) {
        List<LessonToClassResp> lessons = 
            entity.getClase().getLessons()
            .stream()
            .map(this::lessonEntityToResponse)
            .collect(Collectors.toList());

        ClassToStudentResp clase =  ClassToStudentResp.builder()
                                    .id(entity.getClase().getId())
                                    .name(entity.getClase().getName())
                                    .description(entity.getClase().getDescription())
                                    .created_at(entity.getClase().getCreated_at())
                                    .active(entity.getClase().getActive())
                                    .lessons(lessons)
                                    .build();

        return StudentGetResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .created_at(entity.getCreated_at())
                .active(entity.getActive())
                .clase(clase)
                .build();
    }

    private LessonToClassResp lessonEntityToResponse(LessonEntity entity) {
        List<MultimediaBasicResp> multimedias = 
            entity.getMultimedias()
            .stream()
            .map(this::multimediaEntityToResponse)
            .collect(Collectors.toList());        

        return LessonToClassResp.builder()
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

    private StudentEntity requestToEntity(StudentReq request) {
        return StudentEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .active(request.getActive())
                .clase(findClass(request.getClass_id()))
                .build();
    }

    private StudentEntity find(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Student")));
    }

    private ClassEntity findClass(Long id) {
        return this.classRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Class")));
    }
}
