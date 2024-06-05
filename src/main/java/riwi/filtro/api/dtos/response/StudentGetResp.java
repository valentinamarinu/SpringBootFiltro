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
public class StudentGetResp {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private Boolean active;
    private ClassToStudentResp clase;
}
