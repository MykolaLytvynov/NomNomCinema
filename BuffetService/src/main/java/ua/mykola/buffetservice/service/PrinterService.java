package ua.mykola.buffetservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.mykola.buffetservice.formatter.ReceiptFormatter;
import ua.mykola.commons.model.Order;


@Service
public class PrinterService {
    private static final Logger LOG = LoggerFactory.getLogger(PrinterService.class);
    private final ReceiptFormatter receiptFormatter;

    public PrinterService(ReceiptFormatter receiptFormatter) {
        this.receiptFormatter = receiptFormatter;
    }

    public void printOrder(Order order) {
        LOG.info("Printing order details...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String receipt = receiptFormatter.format(order);
        LOG.info("Order printed successfully:{}", receipt);
    }
}
