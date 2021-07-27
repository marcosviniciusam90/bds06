package com.devsuperior.movieflix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieMinDTO implements Serializable {

    private Long id;
    private String title;
    private String subTitle;
    private Integer year;
    private String imgUrl;
}
