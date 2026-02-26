package com.challenge.tenx.dto;
import java.util.List;
public record PaginaRelogiosDto(
    List<RelogioDto> itens,
    Long total
) {

}
