package com.example.projeto2_streaming_musicas.repository;

import com.example.projeto2_streaming_musicas.model.Singer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test class for singerRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:mysql:8.1:///mytestdb"
})
@Sql(scripts = "classpath:/sql/create-test-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:/sql/delete-test-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SingerRepositoryUsingSpringTestContainersSupportTest {
    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private TestEntityManager entityManager;

    //@Test
    //@DisplayName()

    @Test
    @DisplayName("Given singers, when List<Singer> findAll(), return all that singers")
    void testing_ListSingerFindAll_mustReturnAllSingers() {
        //Given singers
        Singer singer = new Singer("James","Hetfield", LocalDate.of(1963, 8, 3));
        entityManager.persist(singer);
        entityManager.flush();

        //when List<Singer> findAll()
        List<Singer> singers = singerRepository.findAll();

        //return all singers
        assertThat(singers)
                .as("findAll returns the list of Singer")
                .hasSize(1);
        assertThat(singers)
                .as("findAll returns the list of Singer")
                .extracting("firstName")
                .containsExactly("James");
    }

    @Test
    @DisplayName("Given an empty repository, when addSinger, return a singer")
    void testing_SingerAddSinger_MustReturnSinger() {
        //Given an empty repository
        Singer singer = new Singer("James","Hetfield", LocalDate.of(1963, 8, 3));

        //When addSinger
        singerRepository.save(singer);

        //Then must have that singer
        List<Singer> singers = singerRepository.findAll();
        entityManager
                .getEntityManager()
                .createQuery("SELECT s FROM Singer s", Singer.class)
                .getResultList();
        assertThat(singers)
                .hasSize(1);
        assertThat(singers.getFirst().getFirstName())
                .isEqualTo("James");
        assertThat(singers.getFirst().getLastName())
                .isEqualTo("Hetfield");
        assertThat(singers.getFirst().getBirthDate())
                .isEqualTo(LocalDate.of(1963, 8, 3));
    }

    @Test
    @DisplayName("Given a repository with singer, when addSinger, return all singers")
    void testing_SingerAddSingerWithSinger_MustReturnSinger() {
        //Given an empty repository
        Singer singer = new Singer("Axl","Rose", LocalDate.of(1962, 2, 6));
        entityManager.persist(singer);
        entityManager.flush();

        //When addSinger
        Singer singer1 = new Singer("James","Hetfield", LocalDate.of(1963, 8, 3));
        singerRepository.save(singer1);

        //Then must have that singer
        List<Singer> singers = singerRepository.findAll();
        entityManager
                .getEntityManager()
                .createQuery("SELECT s FROM Singer s", Singer.class)
                .getResultList();
        assertThat(singers)
                .hasSize(2);
        assertThat(singers.getLast().getFirstName())
                .isEqualTo("James");
        assertThat(singers.getLast().getLastName())
                .isEqualTo("Hetfield");
        assertThat(singers.getLast().getBirthDate())
                .isEqualTo(LocalDate.of(1963, 8, 3));
    }

    @Test
    @DisplayName("Given a singer with repository of 1, when DeleteSinger, then must return empty")
    void testing_deleteSinger_MustReturnNoSinger() {

    }
}
