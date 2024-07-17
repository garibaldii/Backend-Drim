package com.projeto.drim.infraestructure.entity.Sonho;

import com.projeto.drim.infraestructure.entity.Sonho.enums.Emocoes;
import com.projeto.drim.infraestructure.entity.Sonho.enums.TipoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "sonhos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SonhoEntity {

    @Id
    private int id;

    private String nome;
    private String descricao;
    private List<Enum<TipoEnum>> tipo;
    private List<Enum<Emocoes>> emocoes;
    private boolean ehRecorrente;





}
