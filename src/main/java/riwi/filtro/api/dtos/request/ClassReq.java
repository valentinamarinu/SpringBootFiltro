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
public class ClassReq {
    @NotBlank(message = "El nombre de la clase es requerido.")
    @Size(max = 255, message = "El nombre de la clase no debe exceder los 255 caracteres.")
    private String name;

    @NotBlank(message = "La descripción la clase es requerida.")
    private String description;

    @NotNull(message = "El estado de la clase es requerido.")
    private Boolean active;
}
