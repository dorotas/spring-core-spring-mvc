package guru.springframework.conventers;

import guru.springframework.commands.CustomerForm;
import guru.springframework.domain.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {

    @Override
    public CustomerForm convert(Customer customer) {

        CustomerForm customerForm = new CustomerForm();

        customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setEmail(customer.getEmail());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setPhoneNumber(customer.getPhoneNumber());
        if (customer.getUser() != null) {
            customerForm.setUserId(customer.getUser().getId());
            customerForm.setUserName(customer.getUser().getUsername());
            customerForm.setUserVersion(customer.getUser().getVersion());
        }

        return customerForm;
    }
}
