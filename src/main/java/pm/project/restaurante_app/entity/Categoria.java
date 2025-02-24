package pm.project.restaurante_app.entity;

import jakarta.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String nombre;

    @Column
    private boolean vigencia;

    @OneToOne
    private DocumentoAlmacenado documentoAlmacenado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public DocumentoAlmacenado getDocumentoAlmacenado() {
        return documentoAlmacenado;
    }

    public void setDocumentoAlmacenado(DocumentoAlmacenado documentoAlmacenado) {
        this.documentoAlmacenado = documentoAlmacenado;
    }
}