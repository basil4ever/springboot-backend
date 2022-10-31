package net.javaguides.springbootbackend.services;

import net.javaguides.springbootbackend.dto.InvoiceReportDTO;
import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.model.Invoice;
import net.javaguides.springbootbackend.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceReportDTO> sumInvoiceByEmployee() {

        List<InvoiceReportDTO> invoiceReportDTOS = new ArrayList<>();
        List<Invoice> dbInvoices = invoiceRepository.findAll();

        dbInvoices.forEach(invoice -> {
            Employee currEmp = invoice.getEmployee();
            int ifAlreadyExist = findIndexIfAlreadyAdded(invoiceReportDTOS, currEmp.getId());
            if (ifAlreadyExist == -1) {
                InvoiceReportDTO newInvoiceDto = new InvoiceReportDTO();
                newInvoiceDto.setEmployee_id(invoice.getEmployee().getId());
                newInvoiceDto.setEmployee_name(currEmp.getFirstName() +" "+ currEmp.getLastName());
                newInvoiceDto.setInvoice_sum(invoice.getAmount());

                invoiceReportDTOS.add(newInvoiceDto);
            } else {
                InvoiceReportDTO alreadyAdded = invoiceReportDTOS.get(ifAlreadyExist);
                alreadyAdded.setInvoice_sum(alreadyAdded.getInvoice_sum().add(invoice.getAmount()));
            }
        });
        return invoiceReportDTOS;
    }

    private int findIndexIfAlreadyAdded(List<InvoiceReportDTO> reports, Long id) {
        for (int i = 0; i < reports.size(); i++) {
            if (reports.get(i).getEmployee_id() == id) {
                return i;
            }
        }
        return -1;
    }
}
