package net.avantica.xinef.dapp.model;

public class UbigeoModel {
    private String codDpto;
    private String codProv;
    private String codDist;
    private String descripcion;

    public UbigeoModel() {
    }

    public UbigeoModel(String codDpto, String codProv, String codDist, String descripcion) {
        this.codDpto = codDpto;
        this.codProv = codProv;
        this.codDist = codDist;
        this.descripcion = descripcion;
    }

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

    @Override
    public String toString() {
        return descripcion;
    }
}
