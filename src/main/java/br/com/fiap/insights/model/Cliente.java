package br.com.fiap.insights.model;

import br.com.fiap.insights.dto.cliente.AtualizarCliente;
import br.com.fiap.insights.dto.cliente.CadastrarCliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cliente_id")
    private Long id;

    @Column(name="cidade")
    private String cidade;

    @Column(name="idade")
    private Integer idade;

    // 'M' (masculino) 'F' (feminino) 'Indefinido'
    @Column(name = "sexo")
    private String sexo;

    @OneToMany(mappedBy = "cliente")
    private List<Opiniao> opiniaoList;

    @OneToMany(mappedBy = "cliente")
    private List<Produto> produtoList;


    public Cliente(CadastrarCliente clienteDto){
        cidade = clienteDto.cidade();
        idade = clienteDto.idade();
        sexo = clienteDto.sexo();
    }

    public void atualizarCliente(AtualizarCliente dto){
        if (dto.cidade() !=null)
            cidade = dto.cidade();
        if (dto.idade() !=null)
            idade = dto.idade();
        if (dto.sexo() !=null)
            sexo = dto.sexo();
    }


}