package com.devsuperior.movieflix.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO implements Serializable {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotBlank
    private String text;

    private Long movieId;

    @JsonProperty(access = READ_ONLY)
    private UserDTO user;
}
