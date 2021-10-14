package todo1.sysiems.store.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo1.sysiems.store.invoice.entity.Invoice;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice> findByCustomerId(Long customerId );
    public Invoice findByNumberInvoice(String numberInvoice);
}
