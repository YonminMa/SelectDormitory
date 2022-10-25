package com.pku.dormitory.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_room")
public class Room {

    @Id
    private int id;

    private String name;

    private int capacity;

    private int rest;

    private int gender;

    private int available;

    private int building_id;

    public Room() {
    }

    public Room(String name, int capacity, int rest, int gender, int available, int building_id) {
        this.name = name;
        this.capacity = capacity;
        this.rest = rest;
        this.gender = gender;
        this.available = available;
        this.building_id = building_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", rest=" + rest +
                ", gender=" + gender +
                ", available=" + available +
                ", building_id=" + building_id +
                '}';
    }
}
