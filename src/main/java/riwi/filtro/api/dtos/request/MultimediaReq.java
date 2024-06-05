package riwi.filtro.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import riwi.filtro.utils.enums.Type;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaReq {
    @NotNull(message = "El tipo de la multimedia es requerido.")
    private Type type;

    @NotBlank(message = "La URL de la multimedia es requerida.")
    private String url;

    @NotNull(message = "El estado de la multimedia es requerido.")
    private Boolean active;

    @NotNull(message = "El id de la lección es requerido.")
    private Long lesson_id;
}
