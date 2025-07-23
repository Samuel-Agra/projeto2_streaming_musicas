package com.example.projeto2_streaming_musicas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "SINGER")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SINGER_ID", nullable = false)
    private Long id;

    @Size(max = 250)
    @NotBlank
    @NotNull
    @Column(name = "FIRST_NAME", nullable = false, length = 250)
    private String firstName;

    @Size(max = 250)
    @NotBlank
    @NotNull
    @Column(name = "LAST_NAME", nullable = false, length = 250)
    private String lastName;

    @Past ( message = "Date must be a past date" )
    @NotNull
    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    public Singer(final String firstName, final String lastName, final LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Singer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}