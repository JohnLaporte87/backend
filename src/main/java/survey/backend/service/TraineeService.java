package survey.backend.service;

import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public interface TraineeService {


    /**
     * find all trainees
     * @return all trainees
     */
    Iterable<Trainee> findAll();


    /**
     * find trainee with its id
     * @param id
     * @return optional with trainee if found else optional empty
     */
    Optional<Trainee> findById(int id);


    /**
     * search trainees with criteria lastname, firstname ;
     * one criteria can be null, not both
     * @param lastName
     * @param firstName
     * @return trainee set with this lastname (if not null) and this firstname (if not null) ;
     * empty set if no trainee found with these criteria or both criteria are null
     */
    Iterable<Trainee> search(String lastName, String firstName);


    /**
     * add new trainee
     * @param traineeDto
     * @return trainee completed (id, default values)
     */
    Trainee add(TraineeDto traineeDto);


    /**
     * update trainee
     * @param traineeDto
     * @return trainee updated if found, else optional empty
     */
    Optional<Trainee> update(TraineeDto traineeDto);  //il faudra rajouter dans le controller

    /**
     * delete trainee with its id
     * @param id
     * @return true if found and deleted, false if not found
     */
    boolean delete(int id); //on ne renvoit rien



}
