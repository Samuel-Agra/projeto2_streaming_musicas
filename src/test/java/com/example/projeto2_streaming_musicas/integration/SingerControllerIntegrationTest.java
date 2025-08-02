package com.example.projeto2_streaming_musicas.integration;

import com.example.projeto2_streaming_musicas.model.Singer;
import com.example.projeto2_streaming_musicas.repository.SingerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DisplayName("Test class for Controller with integration")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:mysql:8.1:///mytestdb"
})
@Transactional
@Sql(scripts = "classpath:/sql/create-test-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:/sql/delete-test-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SingerControllerIntegrationTest {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Samuel", password = "54321", authorities = {"ROLE_ADMIN"})
    @DisplayName("Given an empty Repository, when /singers/add, then must have that singer")
    void emptyRepository_whenAdd_mustHaveThatSinger() throws Exception {
        //Given an empty Repository

        //When /singer/add
        mockMvc.perform(post("/singers/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "James")
                .param("lastName", "Hetfield")
                .param("birthDate", "1963-08-03")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/singers"));
        //Then must have that singer
        Singer singer = singerRepository.findAll().getFirst();
        assertThat(singer.getFirstName())
                .as("Should have first name igual to James")
                .isEqualTo("James");
        assertThat(singer.getLastName())
                .as("Should have last name igual to Hetfield")
                .isEqualTo("Hetfield");
        assertThat(singer.getBirthDate())
                .as("Should have birth date igual to 1963-08-03")
                .isEqualTo(LocalDate.of(1963, 8, 3));
    }

    @Test
    @WithMockUser(username = "Samuel", password = "54321", authorities = {"ROLE_ADMIN"})
    @DisplayName("Given a Repository with 1, when /singers/delete/{id}, then must be empty")
    void givenARepositoryWith1_whenSingerEdit_thenShouldBeEmpty() throws Exception {
        //Given a Repository with 1
        Singer singer = new Singer("James","Hetfield", LocalDate.of(1963, 8, 3));
        singerRepository.save(singer);

        //when /singers/edit/{id}
        mockMvc.perform(get("/singers/delete/{id}", singer.getId())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/singers"));
        //then must be empty
        assertThat(singerRepository.findById(singer.getId()))
                .as("findAll must return no Singer")
                .isEmpty();

    }
}
