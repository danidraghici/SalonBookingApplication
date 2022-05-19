package org.loose.fis.model;

import java.sql.Date;

public class appointmentsSalon {

    private int id;
    private String client;
    private String serviciu;
    private Date data;
    private String ora;

    public appointmentsSalon(int id, String client, String serviciu, Date data, String ora) {
        this.id = id;
        this.client = client;
        this.serviciu = serviciu;
        this.data = data;
        this.ora = ora;
    }

    public String getClient() {
        return client;
    }

    public void setSalon(String client) {
        this.client = client;
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
