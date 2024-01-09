package com.geekshirt.ordenservice.utils;

import com.geekshirt.ordenservice.dto.EmployeeDto;
import com.geekshirt.ordenservice.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDto convertEntityToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void configureModelMapper() {
        // Configuración personalizada para mapear la propiedad homeAddress.city a homeCity
        modelMapper.addMappings(new PropertyMap<Employee, EmployeeDto>() {
            @Override
            protected void configure() {
                map().setHomeCity(source.getHomeAddress().getCity());
            }
        });
    }
}

