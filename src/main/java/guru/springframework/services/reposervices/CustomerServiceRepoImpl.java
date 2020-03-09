package guru.springframework.services.reposervices;

import guru.springframework.commands.CustomerForm;
import guru.springframework.conventers.CustomerFormToCustomer;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class CustomerServiceRepoImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerFormToCustomer customerFormToCustomer;

    @Override
    public List<?> listAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return customerRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        Customer customer = getById(id);
        userRepository.delete(customer.getUser());
        customerRepository.delete(customer);
    }

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {

        Customer newCustomer = customerFormToCustomer.convert(customerForm);

        if (newCustomer.getUser().getId() != null) {
            Customer existingCustomer = getById(newCustomer.getId());
            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }
        return saveOrUpdate(newCustomer);
    }
}
