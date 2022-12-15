package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.error.BadRequestError;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.TraineeService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


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
    public Iterable<Trainee> getAll(){
        return traineeService.findAll();
    }
//    @GetMapping
//    public Set<TraineeDto> list(){
//        return traineeService.findAll();
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
//    }

    /**
     * a trainee by its id
     * route: /api/trainee/{id}
     * @param id
     * @return a trainee
     */
    @GetMapping("{id}")
    public Trainee getById(@PathVariable("id") int id){
        Optional<Trainee> optTrainee = traineeService.findById(id);
        if(optTrainee.isPresent()) {
            return optTrainee.get();
        } else {
            throw NoDataFoundError.withId("Trainee", id);
        }

        // return Optional.empty();

//        return TraineeDto.builder()
//                .id(id)
//                .lastName("Doe")
//                .firstName("John")
//                .birthDate(LocalDate.of(1900, 7, 1))
//                .build();
    }



    /**
     * search trainees with criteria
     * route: /api/trainee/search?fn=some_firstname&ln=some_lastname
     * @param firstName (optional)
     * @param lastName (optional)
     * @return trainees corresponding
     */
    @GetMapping("search")
    public Iterable<Trainee> search(
            @RequestParam(name="fn", required = false) String firstName,
            @RequestParam(name="ln", required = false) String lastName
    ){

        int size = 0;
         if(firstName == null && lastName == null) {
            throw BadRequestError.withNoArgs("search with no args not permitted"); // 400 Bad Request
        } else {


             Iterable<Trainee> iTrainees = traineeService.search(lastName, firstName); // Service results

             //Get elements number
             if (iTrainees instanceof Collection) {
                 size = ((Collection<Trainee>) iTrainees).size();
             }

                     if(size == 0) {
                         throw NoDataFoundError.noResults("Trainee search", lastName + " " + firstName);
                     }

            return iTrainees;
        }












//        return Set.of(
//                TraineeDto.builder()
//                        .id(1)
//                        .lastName("Doe")
//                        .firstName("John")
//                        .build(),
//                TraineeDto.builder()
//                        .id(2)
//                        .lastName("Dupont")
//                        .firstName("Robert")
//                        .build(),
//                TraineeDto.builder()
//                        .id(8)
//                        .lastName("Doe")
//                        .firstName("Jane")
//                        .build()
//        );

    }

    /**
     * add new trainee with data in json body
     * route: POST /api/trainee
     * @param traineeDto
     * @return trainee added/completed
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee add(@Valid @RequestBody TraineeDto traineeDto){

        //TODO : traineeDTO must be valid
        return traineeService.add(traineeDto);
    }

    /**
     * delete trainee with its id
     * route: DELETE api/trainee/{id}
     * @param id
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        if (!traineeService.delete(id)) {
            throw NoDataFoundError.withId("Trainee", id);
        }
    }



    /**
     * update trainee with data in json body
     * route: PUT /api/trainee
     * @param traineeDto
     * @return
     */
    @PutMapping
    public Trainee update(@Valid @RequestBody TraineeDto traineeDto) {
        return traineeService.update(traineeDto)
                .orElseThrow(() -> NoDataFoundError.withId("Trainee", Math.toIntExact(traineeDto.getId())));
    }


}
