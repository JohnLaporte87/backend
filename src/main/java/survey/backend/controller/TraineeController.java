package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("api/trainee")
public class TraineeController {

    @Autowired // DI (dependency Injection)
    private TraineeService traineeService;

    /**
     *list of trainees
     * route: /api/trainee/
     * @return list of trainees
     */
    @GetMapping
    public Set<TraineeDto> list(){
        return traineeService.findAll();
//        return Set.of(
//                TraineeDto.builder()
//                        .id(1)
//                        .lastName("Doe")
//                        .firstName("John")
//                        .birthDate(LocalDate.of(1961, 10, 23))
//                        .build(),
//                TraineeDto.builder()
//                        .id(2)
//                        .lastName("Dupont")
//                        .firstName("Robert")
//                        .birthDate(LocalDate.of(1985, 3, 22))
//                        .build(),
//                TraineeDto.builder()
//                        .id(26)
//                        .lastName("Martin")
//                        .firstName("Claire")
//                        .birthDate(LocalDate.of(1988, 5, 26))
//                        .build(),
//                TraineeDto.builder()
//                        .id(8)
//                        .lastName("Doe")
//                        .firstName("Jane")
//                        .birthDate(LocalDate.of(1945, 7, 1))
//                        .email("janedoe@gmail.com")
//                        .build()
//        );
    }



    /**
     * a trainee by its id
     * route: /api/trainee/{id}
     * @param id
     * @return a trainee
     */

    @GetMapping("{id}")
    public TraineeDto one(@PathVariable("id") int id){
        Optional<TraineeDto> optTraineeDto = traineeService.findById(id);
        if(optTraineeDto.isPresent()) {
            return optTraineeDto.get();
        } else {
            throw new IllegalArgumentException("Trainee with id" + id + "not found");
        }

        // return Optional.empty();

//        return Optional.of(TraineeDto.builder()
//                .id(id)
//                .lastName("Doe")
//                .firstName("John")
//                .birthDate(LocalDate.of(1900, 7, 1))
//                .build());
    }

    /**
     * search trainees with criteria
     * route: /api/trainee/search?fn=some_firstname&ln=some_lastname
     * @param firstname
     * @param lastname
     * @return trainees corresponding
     */

    @GetMapping("search")
    public Set<TraineeDto> search(
            @RequestParam(name="fn", required = false) String firstname,
            @RequestParam(name="ln", required = false) String lastname
    ){
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastName("Doe")
                        .firstName("John")
                        .build(),
                TraineeDto.builder()
                        .id(2)
                        .lastName("Dupont")
                        .firstName("Robert")
                        .build(),
                TraineeDto.builder()
                        .id(8)
                        .lastName("Doe")
                        .firstName("Jane")
                        .build()
        );

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto add(@RequestBody TraineeDto traineeDto){
        return traineeService.add(traineeDto);

//        traineeDto.setId(54321);
//        return traineeDto;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        //TODO: delete this object if exist
        //delete();
    }

    //@PutMapping
    //public TraineeDto update(){
    //   //TODO: update
    //}

}
