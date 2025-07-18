package org.backend.model.clients;

import org.backend.model.clients.DTO.ClientRequestDto;
import org.backend.model.clients.DTO.ClientResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
  @Mapping(target = "bonusPoints", source = "bonusPoints")
  @Mapping(target = "createdAt", ignore = true)
  ClientResponseDto toDTO(Client entity);
  Client toEntity(ClientRequestDto dto);
}