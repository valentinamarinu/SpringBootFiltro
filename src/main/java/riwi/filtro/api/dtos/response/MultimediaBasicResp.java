package riwi.filtro.api.dtos.response;

import java.time.LocalDateTime;

import riwi.filtro.utils.enums.Type;

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
public class MultimediaBasicResp {
    private Long id;    
    private Type type;
    private String url;
    private LocalDateTime created_at;
    private Boolean active;
}
