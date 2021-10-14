package todo1.sysiems.store.invoice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import todo1.sysiems.store.invoice.entity.Invoice;
import todo1.sysiems.store.invoice.repository.InvoiceRepository;

@DataJpaTest
public class InvoiceRepositryMockTest {
    
    @Autowired
    private InvoiceRepository invoiceRep;

    @Test
    public void whenFindInvoiceByNumberInvoice_thenReturnInvoice(){
        Invoice invoices = invoiceRep.findByNumberInvoice("2021-01");
        
        Assertions.assertThat(invoices.getDescription()).isEqualTo("VENTA");
        Assertions.assertThat(invoices.getItems().size()).isEqualTo(2);
    }
}
