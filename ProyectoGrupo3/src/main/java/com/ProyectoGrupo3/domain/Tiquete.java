/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "tiquete")
public class Tiquete implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tiquete")
    private Long idTiquete;

    @Column(name = "descripcion_problema")
    private String descripcionProblema;
    private String descripcionSolucion;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Tiquete() {
    }

    public Tiquete(Long idTiquete, String descripcionProblema, String descripcionSolucion, String estado) {
        this.idTiquete = idTiquete;
        this.descripcionProblema = descripcionProblema;
        this.descripcionSolucion = descripcionSolucion;
        this.estado = estado;
    }

}
