package riwi.filtro.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import riwi.filtro.api.dtos.request.StudentReq;
import riwi.filtro.api.dtos.response.StudentBasicResp;
import riwi.filtro.api.dtos.response.StudentGetResp;
import riwi.filtro.infraestructure.services.StudentService;

@RestController
@RequestMapping(path = "/students")
@AllArgsConstructor
public class StudentController {
    @Autowired
    private final StudentService service;

    @Operation(summary = "Lista todas los estudiantes por nombre de forma paginada siempre y cuando el estudiante este activa.", description = "Debes enviar la página, el tamaño de la página, y el nombre por el que quieres buscar el estudiante.")
    @GetMapping
    public ResponseEntity<Page<StudentBasicResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam (defaultValue = "") String name) {
        return ResponseEntity.ok(this.service.getAll(page - 1, size, name));
    }

    @ApiResponse(responseCode = "400", description = "Cuando el id no es válido.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "Lista un estudiante por el id específico.", description = "Debes enviar el id del estudiante que deseas listar.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentGetResp> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.get(id));
    }

    @Operation(summary = "Crea un estudiante.", description = "Debes ingresar el nombre, la descripción y el estado de la clase.")
    @PostMapping
    public ResponseEntity<StudentBasicResp> create(@Validated @RequestBody StudentReq request){
        return ResponseEntity.ok(this.service.create(request));
    }

    @Operation(summary = "Actualiza un estudiante.", description = "Debes ingresar el nombre, el email y el estado que deseas actualizar del estudiante.")
    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentBasicResp> update(
            @PathVariable Long id,
            @Validated @RequestBody StudentReq request) {
        return ResponseEntity.ok(this.service.update(request, id));
    }

    @Operation(summary = "Desabilita un estudiante específico.", description = "Debes ingresar el id del estudiante que deseas deshabilitar")
    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<StudentBasicResp> disable(
        @PathVariable Long id) {
    return ResponseEntity.ok(this.service.disable(id));
    }
}
