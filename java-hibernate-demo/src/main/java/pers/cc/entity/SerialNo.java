package pers.cc.entity;

import javax.persistence.*;

@Entity
@Table(name = "SerialNo")
public class SerialNo {

    @Id
    private Integer id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "serialNo")
    private Integer serialNo; //流水号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return "SerialNo{" +
                "id=" + id +
                ", version=" + version +
                ", serialNo=" + serialNo +
                '}';
    }
}
