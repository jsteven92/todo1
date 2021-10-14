package todo1.sysiems.store.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo1.sysiems.store.invoice.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
