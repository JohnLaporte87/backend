package survey.backend.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDtoTest {

    @Test
    void testDefaultConstructor(){
        var traineeDto = new TraineeDto();
        //TraineeDto traineeDto = new TraineeDto(); ancienne version de java
        assertAll(
            () -> assertNull(traineeDto.getId(), "id"),
            () -> assertNull(traineeDto.getFirstName(), "firstname"),
            () -> assertNull(traineeDto.getLastName(), "lastname"),
            () -> assertNull(traineeDto.getPhoneNumber(), "email"),
            () -> assertNull(traineeDto.getEmail(), "phone number"),
            () -> assertNull(traineeDto.getBirthDate(), "birthdate"));
    }

    // TODO: builder

    // TODO: all args constructor

    void testAllArgsConstructor(){
        int id = 123;
        String lastname = "Doe";
        String firstname = "John";
        String email = "joe.doe@example.org";
        String phoneNumber = "0707070707";
        var birthdate = LocalDate.of(1952, 2, 29);
        var traineeDto = new TraineeDto(id, lastname, firstname, email, phoneNumber, birthdate);
        //then
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "id"),
                () -> assertEquals(lastname, traineeDto.getLastName(), "lastname"),
                () -> assertEquals(firstname, traineeDto.getFirstName(), "firstname"),
                () -> assertEquals(email, traineeDto.getEmail(), "email"),
                () -> assertEquals(phoneNumber, traineeDto.getPhoneNumber(), "phoneNumber"),
                () -> assertEquals(birthdate, traineeDto.getBirthDate(), "birthdate")
        );
    }


    // NB: It will be important to test special case like
    // - simple attribute with default value
    // - collection initialized to empty collection
    @Test
    void testBuilderPartial() {
        // given
        int id = 123;
        String lastname = "Doe";
        String firstname = "John";
        // when
        var traineeDto = TraineeDto.builder()
                .id(id)
                .lastName(lastname)
                .firstName(firstname)
                .build();
        // then
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "id"),
                () -> assertEquals(lastname, traineeDto.getLastName(), "lastname"),
                () -> assertEquals(firstname, traineeDto.getFirstName(), "firstname"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertNull(traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthDate(), "birthdate")
        );
    }



}