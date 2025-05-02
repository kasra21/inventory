package com.task.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "it_config")
public class ITConfig extends Task {

	//@ManyToOne //could use m-1 if we want to keep it weak
    @OneToOne(optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer difficulty;

    public ITConfig() {}

    public ITConfig(Item item, Integer difficulty) {
        this.item = item;
        this.difficulty = difficulty;
    }

    //Getters and Setters
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
}

