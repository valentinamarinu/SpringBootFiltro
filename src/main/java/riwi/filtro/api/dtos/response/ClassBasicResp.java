package riwi.filtro.api.dtos.response;

import java.time.LocalDateTime;

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
public class ClassBasicResp {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private Boolean active;
}
