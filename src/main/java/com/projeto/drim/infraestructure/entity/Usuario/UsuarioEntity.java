package com.projeto.drim.infraestructure.entity.Usuario;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "usuarios") // Mapeia a classe para a coleção "usuarios" no MongoDB
@Getter // Gera getters para todos os campos
@Setter // Gera setters para todos os campos
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com um argumento para cada campo
@Builder // Adiciona o padrão Builder para construir objetos da classe de forma fluente
public class UsuarioEntity {

    @Id
    private String id;

    @NotBlank(message = "O campo nome é obrigatório")
    @NotNull
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s~]+$", message = "O campo nome só pode ter letras, espaços e caracteres acentuados.")
    @Size(min = 3, max = 50, message = "O campo nome precisa ter entre 3 e 50 caractéres.")
    @Indexed(unique = true)
    private String nome;


    @NotBlank(message = "O campo e-mail é obrigatório")
    @Email(message = "O endereço do e-mail deve ser válido")
    @Indexed(unique = true)
    private String email;


    @NotBlank(message = "O campo senha é obrigatório")
    @Size(min = 8, message = "O campo senha precisa ter no mínimo 8 caracteres.")
    private String senha;

    @NotBlank(message = "O campo número de contato é obrigatório")
    private String numeroContato;


}
