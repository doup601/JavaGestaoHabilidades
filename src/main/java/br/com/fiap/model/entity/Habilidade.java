package br.com.fiap.model.entity;

import br.com.fiap.model.dto.HabilidadeDTO;
import jakarta.persistence.*;

@Entity(name = "Habilidade")
@Table(name = "dddj_habilidades")
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String habilidade;     // nome → habilidade
    private String categoria;
    private String nivelAtual;
    private String meta;
    private String funcionario;
    private String status;         // aprendendo / concluído

    public Habilidade() {}

    public Habilidade(HabilidadeDTO dto) {
        this.habilidade = dto.habilidade();
        this.categoria = dto.categoria();
        this.nivelAtual = dto.nivelAtual();
        this.meta = dto.meta();
        this.funcionario = dto.funcionario();
        this.status = dto.status();
    }

    public Long getCodigo() { return codigo; }
    public void setCodigo(Long codigo) { this.codigo = codigo; }

    public String getHabilidade() { return habilidade; }
    public void setHabilidade(String habilidade) { this.habilidade = habilidade; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getNivelAtual() { return nivelAtual; }
    public void setNivelAtual(String nivelAtual) { this.nivelAtual = nivelAtual; }

    public String getMeta() { return meta; }
    public void setMeta(String meta) { this.meta = meta; }

    public String getFuncionario() { return funcionario; }
    public void setFuncionario(String funcionario) { this.funcionario = funcionario; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}
