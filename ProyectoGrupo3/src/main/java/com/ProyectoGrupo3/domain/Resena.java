/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="resena")
public class Resena implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Long idResena;
    private int calificacion;
    private String comentario;
    
    private Date fecha;
    
    @ManyToOne
@JoinColumn(name="id_producto")
Producto producto;
   
    public Resena() {
    }
 
    public Resena (Producto producto) {
        this.producto = producto;
    }
    
    
}
