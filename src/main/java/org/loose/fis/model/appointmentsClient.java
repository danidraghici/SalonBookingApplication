package org.loose.fis.model;

import java.sql.Date;

public class appointmentsClient {

    private int id;
    private String salon;
    private String serviciu;
    private Date data;
    private String ora;

    public appointmentsClient(int id, String salon, String serviciu, Date data, String ora) {
        this.id = id;
        this.salon = salon;
        this.serviciu = serviciu;
        this.data = data;
        this.ora = ora;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getServiciu() {
        return serviciu;
    }

    public void setServiciu(String serviciu) {
        this.serviciu = serviciu;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
