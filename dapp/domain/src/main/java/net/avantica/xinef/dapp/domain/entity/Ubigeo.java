package net.avantica.xinef.dapp.domain.entity;

public class Ubigeo {
    private String codDpto;
    private String codProv;
    private String codDist;
    private String descripcion;

    public String getCodDpto() {
        return codDpto;
    }

    public void setCodDpto(String codDpto) {
        this.codDpto = codDpto;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public void setCodDist(String codDist) {
        this.codDist = codDist;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodProv() {
        return codProv;
    }

    public String getCodDist() {
        return codDist;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
