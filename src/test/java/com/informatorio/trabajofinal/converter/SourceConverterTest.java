package com.informatorio.trabajofinal.converter;

import com.informatorio.trabajofinal.domain.Source;
import com.informatorio.trabajofinal.dto.SourceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class SourceConverterTest {

    private SourceConverter sourceConverter;
    SourceDTO sourceDTO;

    @BeforeEach
    void setUp() {
        this.sourceConverter = new SourceConverter();

        sourceDTO = new SourceDTO(3,
                "Diario Cara de Roca",
                "abarajame en la bañera nena",
                LocalDate.parse("1800-05-05"));
        }

    @Test
    @Description(value = "Poniendo cualquier code en el constructor del SourceDTO, se convierte a un Source con el code correcto")
    void source_converter_de_DTO_a_Entity_devuelve_un_Entity_con_code_correcto() {

        Source source = sourceConverter.toEntity(sourceDTO);

        assertEquals("diario-cara-de-roca", source.getCode());
    }

    @Test
    @Description(value = "Poniendo cualquier fecha en el constructor del SourceDTO, se crea un Source con la fecha Actual")
    void source_converter_de_DTO_a_Entity_devuelve_un_Entity_con_fecha_correcta() {

        Source source = sourceConverter.toEntity(sourceDTO);

        assertEquals(LocalDate.now(), source.getCreatedAt());
    }

    @Test
    void source_converter_de_DTO_a_Entity_devuelve_un_Entity() {

        Source source = sourceConverter.toEntity(sourceDTO);

        assertInstanceOf(Source.class, source);
    }

    @Test
    void source_converter_de_Entiy_a_DTO_devuelve_un_DTO_y_con_mismos_datos_en_sus_atributos() {

        Source source = sourceConverter.toEntity(sourceDTO);
        SourceDTO sourceDTO2 = sourceConverter.toDto(source);

        assertInstanceOf(SourceDTO.class, sourceDTO2);
        assertEquals(sourceDTO2.getName(), source.getName());
        assertEquals(sourceDTO2.getCode(), source.getCode());
        assertEquals(sourceDTO2.getCreatedAt(), source.getCreatedAt());
    }

}
