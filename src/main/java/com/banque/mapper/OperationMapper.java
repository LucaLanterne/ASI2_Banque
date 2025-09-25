package com.banque.mapper;

import com.banque.dataTransfertObjects.OperationDto;
import com.banque.model.Operation;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class OperationMapper {

    public OperationDto toDto (Operation operation) {
        String libelle = operation.getLibelle();
        float montant = operation.getMontant();
        Date dateOperation = operation.getDateOperation();
        String type = operation.getType();
        return new OperationDto(libelle, montant, dateOperation, type);
    }

    public List<OperationDto> toDtoList (List<Operation> operations) {
        List<OperationDto> dtos = new ArrayList<>();
        for (Operation operation : operations) {
            dtos.add(toDto(operation));
        }
        return dtos;
    }

    public Operation toEntity (OperationDto operationDto) {
        return new Operation(operationDto.getDateOperation(), operationDto.getMontant(), operationDto.getType(),  operationDto.getLibelle());
    }
}
