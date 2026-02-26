package com.challenge.tenx.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.tenx.dto.AtualizarRelogioRequest;
import com.challenge.tenx.dto.CriarRelogioRequest;
import com.challenge.tenx.dto.PaginaRelogiosDto;
import com.challenge.tenx.dto.RelogioDto;
import com.challenge.tenx.service.RelogioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/relogios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // em produção, restrinja
public class RelogioController {

    private final RelogioService servico;

    @GetMapping
    public PaginaRelogiosDto listar(
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "12") int porPagina,
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String tipoMovimento,
            @RequestParam(required = false) String materialCaixa,
            @RequestParam(required = false) String tipoVidro,
            @RequestParam(required = false) Integer resistenciaMin,
            @RequestParam(required = false) Integer resistenciaMax,
            @RequestParam(required = false) Long precoMin,
            @RequestParam(required = false) Long precoMax,
            @RequestParam(required = false) Integer diametroMin,
            @RequestParam(required = false) Integer diametroMax,
            @RequestParam(required = false) String ordenar
    ) {
        return servico.listar(
                pagina, porPagina, busca, marca, tipoMovimento, materialCaixa, tipoVidro,
                resistenciaMin, resistenciaMax, precoMin, precoMax, diametroMin, diametroMax, ordenar
        );
    }

    @GetMapping("/{id}")
    public RelogioDto buscar(@PathVariable UUID id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    public RelogioDto criar(@Valid @RequestBody CriarRelogioRequest req) {
        return servico.criar(req);
    }

    @PutMapping("/{id}")
    public RelogioDto atualizar(@PathVariable UUID id, @Valid @RequestBody AtualizarRelogioRequest req) {
        return servico.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable UUID id) {
        servico.remover(id);
    }
}
