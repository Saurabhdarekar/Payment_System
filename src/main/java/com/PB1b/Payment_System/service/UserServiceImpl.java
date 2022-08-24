package com.barclays.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.barclays.dto.AccountTransactionDTO;
import com.barclays.dto.BillsDTO;
import com.barclays.dto.RegisteredBillersDTO;
import com.barclays.dto.UserDTO;
import com.barclays.entity.Accounts;
import com.barclays.entity.Accounts_Transaction;
import com.barclays.entity.Bills;
import com.barclays.entity.EmailDetails;
import com.barclays.entity.RegisteredBillers;
import com.barclays.entity.User;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.AccountTransactionRepository;
import com.barclays.repository.AccountsRepository;
import com.barclays.repository.BillsRepository;
import com.barclays.repository.RegisteredBillersRepository;
import com.barclays.repository.UserRespository;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private Environment environment;

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private RegisteredBillersRepository registeredBillersRepository;

    @Autowired
    private BillsRepository billsRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private EmailService emailService;


    @Override
    public void listall(HttpServletResponse response) throws IOException,PaymentsException {

        Iterable<Accounts_Transaction> transactions = accountTransactionRepository.findAll();
        List<Accounts_Transaction> trans = new ArrayList<>();

        transactions.forEach(tr -> {
            Accounts_Transaction at = new Accounts_Transaction();
            at.setAmount(tr.getAmount());
            at.setBill_ref_num(tr.getBill_ref_num());
            at.setDate(tr.getDate());
            at.setDescription(tr.getDescription());
            at.setSequence_id(tr.getSequence_id());
            at.setTransaction_type(at.getTransaction_type());

            trans.add(at);
        });
        if (trans.isEmpty())
            throw new PaymentsException("Service.BILLER_NOT_FOUND");



        //...................

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        response.reset();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Transaction Reference Number", "Amount", "Bill Reference Number", "Date", "Description"," SequenceId","Transaction Type"};
        String[] nameMapping = {"trans_ref_num", "amount", "bill_ref_num", "date","description", "sequence_id","transaction_type"};

        csvWriter.writeHeader(csvHeader);

        for (Accounts_Transaction t : trans) {
            csvWriter.write(t, nameMapping);
        }

        csvWriter.close();


    }







}
