package riwi.filtro.api.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

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
public class LessonResp {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created_at;
    private Boolean active;
    private List<MultimediaBasicResp> multimedias;
    private ClassToLessonResp clase;
}
