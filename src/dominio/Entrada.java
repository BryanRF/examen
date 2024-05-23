package dominio;

import java.util.Date;

public class Entrada {
    // Atributos
    private int id;
    private int eventoId;
    private double pago;
    private String dni;
    private Date createdAt;
    private Date updatedAt;

    // Constructores
    public Entrada() {
    }

    public Entrada(int id, int eventoId, double pago, String dni, Date createdAt, Date updatedAt) {
        this.id = id;
        this.eventoId = eventoId;
        this.pago = pago;
        this.dni = dni;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
