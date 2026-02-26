package com.challenge.tenx.entity;
import java.time.Instant;
import java.util.UUID;

import com.challenge.tenx.entity.enums.MaterialCaixa;
import com.challenge.tenx.entity.enums.TipoMovimento;
import com.challenge.tenx.entity.enums.TipoVidro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "relogio", indexes = {
    @Index(name = "idx_relogio_marca", columnList = "marca"),
    @Index(name = "idx_relogio_criado_em", columnList = "criado_Em"),
    @Index(name = "idx_relogio_preco", columnList = "precoEmCentavos")
})
public class Relogio {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "marca", nullable = false, length = 100)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    @Column(name = "referencia", nullable = false, length = 100)
    private String referencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoMovimento tipoMovimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MaterialCaixa materialCaixa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoVidro tipoVidro;

    @Column(nullable = false)
    private int resistenciaAguaM;

    @Column(nullable = false)
    private int diametroMm;

    @Column(nullable = false)
    private int lugToLugMm;

    @Column(nullable = false)
    private int espessuraMm;

    @Column(nullable = false)
    private int larguraLugMm;

    @Column(nullable = false)
    private long precoEmCentavos;

    @Column(nullable = false, length = 600)
    private String urlImagem;

    @Column(nullable = false)
    private Instant criadoEm;

    @PrePersist
    void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (criadoEm == null) criadoEm = Instant.now();
        normalizar();
    }

    @PreUpdate
    void preUpdate() {
        normalizar();
    }

    private void normalizar() {
        if (marca != null) marca = marca.trim();
        if (modelo != null) modelo = modelo.trim();
        if (referencia != null) referencia = referencia.trim();
        if (urlImagem != null) urlImagem = urlImagem.trim();
    }
}
