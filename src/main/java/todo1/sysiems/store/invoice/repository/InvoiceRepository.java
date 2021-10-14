package todo1.sysiems.store.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo1.sysiems.store.invoice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public Invoice findByNumberInvoice(String numberInvoice);
}
