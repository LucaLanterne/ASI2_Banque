package com.banque.mapper;

import com.banque.dataTransfertObjects.OperationDto;
import com.banque.model.Operation;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper permettant de convertir entre {@link Operation} (entité)
 * et {@link OperationDto} (DTO).
 */
@Component
public class OperationMapper {

    /**
     * Convertit une entité {@link Operation} en {@link OperationDto}.
     *
     * @param operation entité opération
     * @return DTO correspondant
     */
    public OperationDto toDto(Operation operation) {
        String libelle = operation.getLibelle();
        float montant = operation.getMontant();
        Date dateOperation = operation.getDateOperation();
        String type = operation.getType();
        return new OperationDto(libelle, montant, dateOperation, type);
    }

    /**
     * Convertit une liste d'entités {@link Operation} en liste de {@link OperationDto}.
     *
     * @param operations liste d'entités
     * @return liste de DTO correspondants
     */
    public List<OperationDto> toDtoList(List<Operation> operations) {
        List<OperationDto> dtos = new ArrayList<>();
        for (Operation operation : operations) {
            dtos.add(toDto(operation));
        }
        return dtos;
    }

    /**
     * Convertit un {@link OperationDto} en entité {@link Operation}.
     *
     * @param operationDto DTO opération
     * @return entité correspondante
     */
    public Operation toEntity(OperationDto operationDto) {
        return new Operation(
                operationDto.getDateOperation(),
                operationDto.getMontant(),
                operationDto.getType(),
                operationDto.getLibelle()
        );
    }
}
