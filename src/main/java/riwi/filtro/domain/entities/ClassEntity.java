package riwi.filtro.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "class")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private String description;

    @Builder.Default
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "clase", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<StudentEntity> students;

    @OneToMany(mappedBy = "clase", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<LessonEntity> lessons;
}
