package riwi.filtro.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonReq {
    @NotBlank(message = "El título de la lección es requerido.")
    @Size(max = 255, message = "El título de la lección no debe exceder los 255 caracteres.")
    private String title;

    @NotBlank(message = "El contenido de la lección es requerido.")
    private String content;

    @NotNull(message = "El estado de la lección es requerido.")
    private Boolean active;

    @NotNull(message = "El id de la clase es requerido.")
    private Long class_id;
}
