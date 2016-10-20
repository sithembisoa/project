package com.off.asithembiso.rands.domain;

/**
 * Created by asithembiso on 2016/09/01.
 */



import java.io.Serializable;
import java.util.Date;

/**
 * Created by asithembiso on 2016/08/26.
 */

public class Events implements Serializable {

    private Long id;

    private Date eventDate;
    private String eventTitle;
    private Double price;

    public Events(){}

    public Events(Builder builder){
        this.id = builder.id;
        this.eventDate = builder.eventDate;
        this.eventTitle = builder.eventTitle;
        this.price = builder.price;
    }

    public Long getId(){return this.id;}
    public Date getDate(){return this.eventDate;}
    public String getEventTitle(){return this.eventTitle;}
    public Double getPrice(){return price;}

    public static class Builder{
        private Long id;
        private Date eventDate;
        private String eventTitle;
        private Double price;

        public Builder id(Long id){
            this.id=id;
            return  this;
        }

        public Builder eventDate(Date eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Builder eventTitle(String eventTitle){
            this.eventTitle = eventTitle;
            return this;

        }

        public Builder price(Double price){
            this.price = price;
            return this;
        }

        public Builder copy(Events builder){
            this.id = builder.id;
            this.eventDate = builder.eventDate;
            this.eventTitle = builder.eventTitle;
            this.price = builder.price;
            return this;
        }

        public Events build(){ return new Events(this);}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Events events = (Events) o;

        return id == events.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

