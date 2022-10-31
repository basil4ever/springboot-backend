package net.javaguides.springbootbackend.services;

import net.javaguides.springbootbackend.dto.InvoiceReportDTO;
import net.javaguides.springbootbackend.model.Invoice;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {
    public List<InvoiceReportDTO> sumInvoiceByEmployee();
}
