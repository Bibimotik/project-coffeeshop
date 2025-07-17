package org.backend.model.clients;

import org.backend.model.clients.DTO.ClientsRequestDto;
import org.backend.model.clients.DTO.ClientsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientsMapper {
  @Mapping(target = "bonusPoints", source = "bonusPoints")
  @Mapping(target = "createdAt", ignore = true)
  ClientsResponseDto toDTO(Clients entity);
  Clients toEntity(ClientsRequestDto dto);
}