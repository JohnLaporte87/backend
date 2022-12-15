package survey.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.repository.TraineeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class TraineeService implements survey.backend.service.TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;

    //private FakeTraineeRepository repository = new FakeTraineeRepository();
    @Override
    public Iterable<Trainee> findAll() {
        return this.traineeRepository.findAll();
    }

    @Override
    public Optional<Trainee> findById(int id) {
        return this.traineeRepository.findById((long) id);

    }

    @Override
    public boolean delete(int id) {
        Optional<Trainee> oTrainee = this.traineeRepository.findById((long) id);
        if (oTrainee.isPresent()) {
            this.traineeRepository.delete(oTrainee.get());
            return true;
        }
        return false;
    }


    @Override
    public Iterable<Trainee> search(String lastName, String firstName) {

        if(lastName == null){return this.traineeRepository.findByFirstName(firstName);}
        if(firstName == null){return this.traineeRepository.findByLastName(lastName);}

        return this.traineeRepository.byLastNameAndFirstName(lastName, firstName);
    }

    @Override
    public Trainee add(TraineeDto traineeDto) {
        return this.traineeRepository.save(traineeDto.toTrainee());
        //return this.traineeRepository.save(traineeDto);

//        return TraineeDto.builder()
//                .id(88)
//                .lastName("Obvioussss")
//                .firstName("Captainnnnn")
//                .birthDate(LocalDate.of(1988, 8, 8))
//                .build();
    }

    @Override
    public Optional<Trainee> update(TraineeDto traineeDto) {
        Trainee trainee = traineeDto.toTrainee();
        Optional<Trainee> oTrainee = this.traineeRepository.findById(trainee.getId());
        if(oTrainee.isPresent()){
            this.traineeRepository.save(trainee);
            return Optional.of(trainee);
        }
        return Optional.empty();
    }
}


