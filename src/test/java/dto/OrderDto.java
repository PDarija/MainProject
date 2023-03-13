package dto;

import javax.xml.stream.events.Comment;

public class OrderDto {

    private String customerName;
    private String customerPhone;
    private String comment;

    public OrderDto (String customerName, String customerPhone, String comment){
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.comment = comment;

    }

    public OrderDto(){

    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
