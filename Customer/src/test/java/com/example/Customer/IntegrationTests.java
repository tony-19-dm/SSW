package com.example.Customer;

import com.example.Customer.model.Customer;
import com.example.Customer.model.Item;
import com.example.Customer.model.Order;
import com.example.Customer.model.OrderDetail;
import com.example.Customer.model.paymentType.Cash;
import com.example.Customer.model.paymentType.Check;
import com.example.Customer.model.paymentType.Credit;
import com.example.Customer.model.status.PaymentStatus;
import com.example.Customer.model.value.Address;
import com.example.Customer.model.value.measurments.Quantity;
import com.example.Customer.repository.CustomerRepository;
import com.example.Customer.repository.ItemRepository;
import com.example.Customer.repository.OrderRepository;
import com.example.Customer.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Testcontainers
public class IntegrationTests {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void SetProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private OrderService orderSearchService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        itemRepository.deleteAll();

        Item item1 = new Item();
        item1.setDescription("Test 1");
        itemRepository.save(item1);

        Item item2 = new Item();
        item2.setDescription("Test 2");
        itemRepository.save(item2);

        Customer customer1 = new Customer();
        customer1.setName("Tony Dm");
        Address address1 = new Address();
        address1.setCity("Novosibirsk");
        address1.setStreet("123 Broadway");
        address1.setZipcode("10001");
        customer1.setAddress(address1);
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Second Customer");
        Address address2 = new Address();
        address2.setCity("Moscow");
        address2.setStreet("456 Hollywood Blvd");
        address2.setZipcode("90001");
        customer2.setAddress(address2);
        customerRepository.save(customer2);

        Order order1 = new Order();
        order1.setCustomer(customer1);
        order1.setDate(LocalDate.from(LocalDateTime.now().minusDays(5)));
        order1.setStatus("SHIPPED");

        OrderDetail detail1 = new OrderDetail();
        detail1.setItem(item1);
        detail1.setQuantity(new Quantity(2, "piece", "pc"));
        detail1.setStatus("TAXABLE");
        order1.addOrderDetail(detail1);

        Cash cashPayment = new Cash();
        cashPayment.setAmount(100.0f);
        cashPayment.setCashAmount(100.0f);
        cashPayment.setStatus(PaymentStatus.COMPLETED);
        cashPayment.setOrder(order1);
        order1.setPayment(cashPayment);

        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setCustomer(customer2);
        order2.setDate(LocalDate.from(LocalDateTime.now().minusDays(2)));
        order2.setStatus("PROCESSING");

        OrderDetail detail2 = new OrderDetail();
        detail2.setItem(item2);
        detail2.setQuantity(new Quantity(1, "piece", "pc"));
        detail2.setStatus("TAX_EXEMPT");
        order2.addOrderDetail(detail2);

        Credit creditPayment = new Credit();
        creditPayment.setAmount(200.0f);
        creditPayment.setNumber("1234-5678-9012-3456");
        creditPayment.setType("VISA");
        creditPayment.setExpDate(LocalDateTime.now().plusYears(2));
        creditPayment.setStatus(PaymentStatus.PENDING);
        creditPayment.setOrder(order2);
        order2.setPayment(creditPayment);

        orderRepository.save(order2);

        Order order3 = new Order();
        order3.setCustomer(customer1);
        order3.setDate(LocalDate.from(LocalDateTime.now().minusDays(1)));
        order3.setStatus("CANCELLED");

        OrderDetail detail3 = new OrderDetail();
        detail3.setItem(item1);
        detail3.setQuantity(new Quantity(3, "piece", "pc"));
        detail3.setStatus("TAXABLE");
        order3.addOrderDetail(detail3);

        Check checkPayment = new Check();
        checkPayment.setAmount(150.0f);
        checkPayment.setName("Tony Dm");
        checkPayment.setBankID("T-BANK");
        checkPayment.setStatus(PaymentStatus.FAILED);
        checkPayment.setOrder(order3);
        order3.setPayment(checkPayment);

        orderRepository.save(order3);
    }

    @Test
    void shouldFindOrdersByCustomerName() {
        List<Order> orders = orderSearchService.searchOrders(
                "Tony", null, null, null,
                null, null, null, null, null);

        assertThat(orders).hasSize(2);
        assertThat(orders.get(0).getCustomer().getName()).isEqualTo("Tony Dm");
        assertThat(orders.get(1).getCustomer().getName()).isEqualTo("Tony Dm");
    }

    @Test
    void shouldFindOrdersByAddress() {
        List<Order> orders = orderSearchService.searchOrders(
                null, "Novosibirsk", null, null,
                null, null, null, null, null);

        assertThat(orders).hasSize(2);
        assertThat(orders.get(0).getCustomer().getAddress().getCity()).isEqualTo("Novosibirsk");
        assertThat(orders.get(1).getCustomer().getAddress().getCity()).isEqualTo("Novosibirsk");
    }

    @Test
    void shouldFindOrdersByDateRange() {
        LocalDateTime fromDate = LocalDateTime.now().minusDays(3);
        LocalDateTime toDate = LocalDateTime.now();

        List<Order> orders = orderSearchService.searchOrders(
                null, null, null, null,
                fromDate, toDate, null, null, null);

        assertThat(orders).hasSize(2);
    }

    @Test
    void shouldFindOrdersByPaymentType() {
        List<Order> orders = orderSearchService.searchOrders(
                null, null, null, null,
                null, null, "cash", null, null);

        assertThat(orders).hasSize(1);
        assertThat(orders.getFirst().getPayment()).isInstanceOf(Cash.class);
    }

    @Test
    void shouldFindOrdersByOrderStatus() {
        List<Order> orders = orderSearchService.searchOrders(
                null, null, null, null,
                null, null, null, "SHIPPED", null);

        assertThat(orders).hasSize(1);
        assertThat(orders.getFirst().getStatus()).isEqualTo("SHIPPED");
    }

    @Test
    void shouldFindOrdersByPaymentStatus() {
        List<Order> orders = orderSearchService.searchOrders(
                null, null, null, null,
                null, null, null, null, "PENDING");

        assertThat(orders).hasSize(1);
        assertThat(orders.getFirst().getPayment().getStatus()).isEqualTo(PaymentStatus.PENDING);
    }

    @Test
    void shouldFindOrdersWithMultipleCriteria() {
        List<Order> orders = orderSearchService.searchOrders(
                "Tony", "Novosibirsk", null, null,
                null, null, "check", null, null);

        assertThat(orders).hasSize(1);
        assertThat(orders.getFirst().getCustomer().getName()).isEqualTo("Tony Dm");
        assertThat(orders.getFirst().getPayment()).isInstanceOf(Check.class);
    }
}
