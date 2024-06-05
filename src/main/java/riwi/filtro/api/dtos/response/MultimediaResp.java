package riwi.filtro.api.dtos.response;

import java.time.LocalDateTime;

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
public class MultimediaResp {
    private Long id;    
    private Type type;
    private String url;
    private LocalDateTime created_at;
    private Boolean active;
    private LessonToMultimediaResp lesson;
}
