package com.mylocarson.reservationapp.models.tables;

public class Table {
    private int id;
    private String created_at;
    private String deleted_at;
    private String updated_at;
    private String description;
    private String is_available;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", created_at='" + created_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", description='" + description + '\'' +
                ", is_availale='" + is_available + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getIs_available() {
        return is_available;
    }

    public void setIs_available(String is_available) {
        this.is_available = is_available;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
