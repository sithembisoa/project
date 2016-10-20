package com.off.asithembiso.rands.domain;



import java.io.Serializable;


/**
 * Created by asithembiso on 2016/09/02.
 */
public class Booking implements Serializable {

    private Long id;
    private String fullName;
    private String bookingDate;
    private String tableSize;

    public Booking(){}
    public Booking(Builder builder){

    }

    public String getFullName(){return this.fullName;}
    public String getTableSize(){return this.tableSize;}
    public String getBookingDate(){return this.bookingDate;}
    public Long getId(){return this.id;}

    public  static class Builder{
        private Long id;
        private String fullName;
        private String bookingDate;
        private String tableSize;

        public Builder(){}

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder fullName(String name){
            this.fullName = name;
            return this;
        }

        public Builder bookingDate(String date){
            this.bookingDate = date;
            return this;
        }

        public Builder tableSize(String tableSize){
            this.tableSize = tableSize;
            return this;
        }

        public Builder copy(Booking booking){
            this.bookingDate = booking.bookingDate;
            this.id=booking.id;
            this.fullName = booking.fullName;
            this.tableSize = booking.tableSize;
            return this;
        }

        public Booking build()
        {
            return new Booking(this);
        }

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        return id == booking.id;

    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
