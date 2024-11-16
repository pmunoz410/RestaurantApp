package pm.project.restaurante_app.entity;

import jakarta.persistence.*;

@Entity
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int cantidad;
    @Column
    private Double precio;
    @ManyToOne
    private Comida comida;
    @ManyToOne
    private Pedido pedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Comida getComida() {
        return comida;
    }

    public void setPlatillo(Comida comida) {
        this.comida = comida;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNombre(){
        return this.comida!= null ? this.comida.getNombre() : "----";
    }

    public Double getSubTotal(){
        return this.cantidad * this.precio;
    }
}