package todo1.sysiems.store.invoice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_invoice")
    private String numberInvoice;

    private String description;


    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    private String state;

    public Invoice(){
        items = new ArrayList<>();
    }

}

