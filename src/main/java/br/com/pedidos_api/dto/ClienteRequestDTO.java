package br.com.pedidos_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Records são uma forma concisa de criar classes imutáveis em Java. Eles são ideais para representar dados simples, como DTOs (Data Transfer Objects), onde o foco está em armazenar e transferir dados sem a necessidade de lógica adicional.
public record ClienteRequestDTO(

        @NotBlank(message = "O nome do cliente é obrigatório e não pode ser vazio")
        @Size(max = 50, message = "O nome não pode ter mais que 50 caracteres")
        String nome,

        @NotBlank(message = "O email do cliente é obrigatório")
        @Email(message = "O email do cliente deve ser válido")
        String email,

        @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter apenas números e ter 10 ou 11 dígitos")
        String telefone,

        @NotBlank(message = "O CPF do cliente é obrigatório")
        @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
        String cpf
) {}