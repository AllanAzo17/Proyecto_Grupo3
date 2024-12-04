/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "favorito")
public class Favorito implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_favorito")
    private Long idFavorito;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "fecha_agregado")
    private LocalDateTime fechaAgregado;
   

    public Favorito() {
    }
    
    
    public Favorito(Long idFavorito, Producto producto, LocalDateTime fechaAgregado) {
        this.idFavorito = idFavorito;
        this.producto = producto;
        this.fechaAgregado = fechaAgregado;
    }
    
    

}
