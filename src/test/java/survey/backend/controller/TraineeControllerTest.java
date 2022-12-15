package survey.backend.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(TraineeController.class)
class TraineeControllerTest {

    final static String BASE_URL = "/api/trainee";

    //component to call TraineeController with HTTP requests
    @Autowired
    MockMvc mockMvc;
    //un faux client HTTP

    @MockBean
    TraineeService traineeService;
    //un faux TraineeService

    @Test
    void testGetByIdOk() throws Exception {
        //prepare
        int id = 123;
        var traineeDto= TraineeDto.builder()
                .id(id)
                .lastName("Doe")
                .firstName("John")
                .build();
        // CTRL ALT i pour auto indenter

        BDDMockito.given(traineeService.findById(id))
                .willReturn(Optional.of(traineeDto));

        //when
        mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL + "/" + id)
                        .accept(MediaType.APPLICATION_JSON)
        )

        //then / verify HTTP communication : status, type json, dans le body l'id,
                .andDo(MockMvcResultHandlers.print())   //log request and response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
        // ATL ENTREE SUR PRINT, STATUS, CONTENT, GET, GIVEN,
        //then / verify Mock service has been called
        BDDMockito.then(traineeService)
                .should()
                .findById(id);

    }

    @Test
    void testGetByIdKoNotFound() throws Exception {
        //prepare
        int id = 0;

        // CTRL ALT i pour auto indenter

        BDDMockito.given(traineeService.findById(id))
                .willReturn(Optional.empty());

        //when
        mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_URL + "/" + id)
                                .accept(MediaType.APPLICATION_JSON)
                )

                //then / verify HTTP communication : status, type json, dans le body
                .andDo(MockMvcResultHandlers.print())   //log request and response
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Trainee with id " + id + " not found"));
        // ATL ENTREE SUR PRINT, STATUS, CONTENT, GET, GIVEN,
        //then / verify Mock service has been called
        BDDMockito.then(traineeService)
                .should()
                .findById(id);


    }

}