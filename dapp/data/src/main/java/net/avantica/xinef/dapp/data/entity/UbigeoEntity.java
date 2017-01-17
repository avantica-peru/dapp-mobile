package net.avantica.xinef.dapp.data.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.avantica.xinef.dapp.data.repository.UbigeoDataBase;

@Table(database = UbigeoDataBase.class, name = "ubigeo")
public class UbigeoEntity extends BaseModel {
    @PrimaryKey
    @Column(name = "codDpto")
    private String codDpto;
    @PrimaryKey
    @Column(name = "codProv")
    private String codProv;
    @PrimaryKey
    @Column(name = "codDist")
    private String codDist;
    @Column(name = "descripcion")
    private String descripcion;

    public String getCodDpto() {
        return codDpto;
    }

    public void setCodDpto(String codDpto) {
        this.codDpto = codDpto;
    }

    public String getCodProv() {
        return codProv;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public String getCodDist() {
        return codDist;
    }

    public void setCodDist(String codDist) {
        this.codDist = codDist;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
