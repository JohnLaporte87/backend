package survey.backend.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "poe")
@Getter
@Setter

public class Poe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="title", nullable = false, length = 150)
    private String title;

    @Column(name="begin_date", nullable = false)
    private Date beginDate;

    @Column(name="end_date", nullable = false)
    private Date endDate;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private PoeType type;

    @OneToMany
    @JoinColumn(name ="poe_id")
    private Set<Trainee> trainees;


}
