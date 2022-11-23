package survey.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeTypeModel;
import survey.backend.dto.TraineeDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/poe")
public class PoeController {

    @GetMapping
    public Set<PoeDto> list(){
        return Set.of(
                PoeDto.builder()
                        .id(1)
                        .title("Java FullStack")
                        .beginDate(LocalDate.of(2022, 10, 24))
                        .endDate(LocalDate.of(2023, 01, 27))
                        .poeType(PoeTypeModel.POEI)
                        .build(),
                PoeDto.builder()
                        .id(2)
                        .title("Java Fullstack")
                        .beginDate(LocalDate.of(2022, 11, 2))
                        .endDate(LocalDate.of(2023, 2, 3))
                        .poeType(PoeTypeModel.POEC)
                        .build(),
                PoeDto.builder()
                        .id(3)
                        .title("Consultant DevOps")
                        .beginDate(LocalDate.of(2022, 06, 13))
                        .endDate(LocalDate.of(2022, 9, 16))
                        .poeType(PoeTypeModel.POEC)
                        .build(),
                PoeDto.builder()
                        .id(4)
                        .title("Consultant Cyber Security")
                        .beginDate(LocalDate.of(2021, 9, 13))
                        .endDate(LocalDate.of(2021, 12, 12))
                        .poeType(PoeTypeModel.POEI)
                        .build(),
                PoeDto.builder()
                        .id(5)
                        .title("Consultant SAP")
                        .beginDate(LocalDate.of(2022, 5, 13))
                        .endDate(LocalDate.of(2022, 8, 16))
                        .poeType(PoeTypeModel.POEI)
                        .build(),
                PoeDto.builder()
                        .id(6)
                        .title("Consultant BI")
                        .beginDate(LocalDate.of(2022, 8, 24))
                        .endDate(LocalDate.of(2022, 11, 23))
                        .poeType(PoeTypeModel.POEI)
                        .build()
        );
    }


    @GetMapping("{id}")
    public Optional<PoeDto> one(@PathVariable("id") int id){
        // return Optional.empty();

        return Optional.of(PoeDto.builder()
                .id(id)
                .title("Java FrontEnd BackEnd")
                .beginDate(LocalDate.of(2020, 7, 3))
                .endDate(LocalDate.of(2020, 10, 2))
                .poeType(PoeTypeModel.POEC)
                .build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PoeDto add(@RequestBody PoeDto PoeDto){
        // TODO : add in under layer
        PoeDto.setId(54321);
        return PoeDto;
    }

}
