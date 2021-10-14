package todo1.sysiems.store.invoice.entity;

import lombok.Data;
import todo1.sysiems.store.product.entity.Product;

import javax.persistence.*;
import javax.validation.constraints.Positive;
// import java.io.Serializable;

@Data
@Entity
@Table(name = "invoce_items")
public class InvoiceItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "Price should has value longer cero")
    private int quantity;
    private Double  price;

    @Column(name = "product_id")
    private Long productId;


    @Transient
    private Double subTotal;

    @Transient
    private Product product;

    public Double getSubTotal(){
        if (this.price >0  && this.quantity >0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }
    public InvoiceItem(){
        this.quantity=(int) 0;
        this.price=(double) 0;

    }
}
