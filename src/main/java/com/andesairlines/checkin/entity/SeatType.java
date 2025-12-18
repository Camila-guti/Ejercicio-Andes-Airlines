package com.andesairlines.checkin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seat_type")
public class SeatType {

    @Id
    @Column(name = "seat_type_id") // Esto conecta con la columna de la BD
    private Long id; 

    private String name;
}