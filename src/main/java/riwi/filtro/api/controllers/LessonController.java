package riwi.filtro.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import riwi.filtro.api.dtos.request.LessonReq;
import riwi.filtro.api.dtos.response.LessonResp;
import riwi.filtro.infraestructure.services.LessonService;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
public class LessonController {
    @Autowired
    private final LessonService service;

    @ApiResponse(responseCode = "400", description = "Cuando el id no es válido.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "Obtiene una lección por el id específico.", description = "Debes enviar el id de la lección que deseas obtener.")
    @GetMapping(path = "/{id}/multimedia")
    public ResponseEntity<LessonResp> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.get(id));
    }

    @Operation(summary = "Crea un lección y su repectiva multimedia.", description = "Debes ingresar el título, el contenido, el estado, la clase y la multimedia correspondiente a la lección que deseas crear.")
    @PostMapping
    public ResponseEntity<LessonResp> create(@Validated @RequestBody LessonReq request){
        return ResponseEntity.ok(this.service.create(request));
    }

    @Operation(summary = "Deshabilita una lección específica.", description = "Debes ingresar el id de la lección específica que deseas deshabilitar")
    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<LessonResp> disable(
        @PathVariable Long id) {
    return ResponseEntity.ok(this.service.disable(id));
    }
}
